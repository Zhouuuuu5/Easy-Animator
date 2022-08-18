package cs5004.animator.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.animation.ChangeColor;
import cs5004.animator.model.animation.Move;
import cs5004.animator.model.animation.Scale;
import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.util.Canvas;
import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;

/**
 * This class implements the interface model.
 *
 */
public class ModelImpl implements Model {

  // shapeName, Shape
  private Map<String, Shape> allShapes;
  private List<Animation> orderedAnimations;
  // shapeName, Animation
  private Map<String, List<Animation>> allAnimations;
  private Canvas canvas;
  private List<List<Shape>> frameShapes;

  /**
   * Constructs a new model instance.
   */
  public ModelImpl() {
    this.allShapes = new LinkedHashMap<>(); // ordered
    this.orderedAnimations = new ArrayList<>();
    this.allAnimations = new LinkedHashMap<>(); // ordered
    this.frameShapes = new ArrayList<>();
  }

  @Override
  public Map<String, Shape> getAllShapes() {
    Map<String, Shape> allShapes = new LinkedHashMap<>();
    for (String shapeName : this.allShapes.keySet()) {
      allShapes.put(shapeName, this.allShapes.get(shapeName));
    }
    return allShapes;
  }

  @Override
  public List<Animation> getOrderedAnimations() {
    return new ArrayList<>(this.orderedAnimations);
  }

  @Override
  public Map<String, List<Animation>> getAllAnimations() {
    Map<String, List<Animation>> allAnimations = new LinkedHashMap<>();
    for (String shapeName : this.allAnimations.keySet()) {
      allAnimations.put(shapeName, this.allAnimations.get(shapeName));
    }
    return allAnimations;
  }

  /**
   * Check if the shape has already existed.
   * 
   * @param name the name of the shape to be checked
   * @return true if the name has already existed, and false otherwise
   */
  private Boolean hasShape(String name) {
    for (String shapeName : this.allShapes.keySet()) {
      if (shapeName.equals(name)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void addShape(Shape shape) throws IllegalArgumentException {
    if (hasShape(shape.getShapeName())) {
      throw new IllegalArgumentException("This shape has already existed.");
    }
    this.allShapes.put(shape.getShapeName(), shape);
  }

  @Override
  public void removeShape(String shapeName) throws IllegalArgumentException {
    if (!hasShape(shapeName)) {
      throw new IllegalArgumentException("This shape doesn't exist.");
    }
    this.allShapes.remove(shapeName);
    this.orderedAnimations.removeIf(animation -> animation.getShapeName().equals(shapeName));
    this.allAnimations.remove(shapeName);
  }

  @Override
  public void addAnimation(Animation animation) throws IllegalArgumentException {
    String shapeName = animation.getShapeName();
    // the shape doesn't exist
    if (!hasShape(shapeName)) {
      throw new IllegalArgumentException("The shape doesn't exist.");
    }
    // animation time is not in the valid interval
    Shape shape = this.allShapes.get(shapeName);
    if (animation.getFromTime() < shape.getAppearTime()
        || animation.getToTime() > shape.getDisappearTime()) {
      throw new IllegalArgumentException("The animation time is invalid.");
    }
    // animation has time conflicts
    if (animationConflict(animation)) {
      throw new IllegalArgumentException(
          "The animation time conflicts with the existing animations.");
    }

    // add to allAnimations
    if (!this.allAnimations.containsKey(shapeName)) {
      this.allAnimations.put(shapeName, new ArrayList<>());
    }
    List<Animation> animationList = this.allAnimations.get(shapeName);
    animationList.add(animation);

    // add to orderedAnimations
    if (this.orderedAnimations.isEmpty()) {
      this.orderedAnimations.add(animation);
    } else {
      int i;
      for (i = 0; i < this.orderedAnimations.size(); i++) {
        Animation currentAnimation = this.orderedAnimations.get(i);
        if (currentAnimation.getFromTime() > animation.getFromTime()) {
          break;
        }
      }
      this.orderedAnimations.add(i, animation);
    }
  }

  /**
   * Check if the new animation has time conflicts with the existing animations.
   * 
   * @param newAnimation the animation to be added
   * @return true if the new animation has time conflict, and false otherwise
   */
  private boolean animationConflict(Animation newAnimation) {
    List<Animation> animationList = this.allAnimations.get(newAnimation.getShapeName());
    if (animationList == null) {
      return false;
    }
    for (Animation animation : animationList) {
      if (newAnimation.getAnimationType() == animation.getAnimationType()) {
        if (newAnimation.getToTime() <= animation.getFromTime()
            || newAnimation.getFromTime() >= animation.getToTime()) {
          continue;
        } else {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public Canvas getCanvas() {
    return this.canvas;
  }

  @Override
  public void setCanvas(Canvas canvas) {
    this.canvas = canvas;
  }

  @Override
  public List<List<Shape>> getFrameShapes() {
    for (int i = 0; i <= this.getEndTime(); i++) {
      this.frameShapes.add(i, new ArrayList<>());
    }
    Collection<Shape> allShapeCollection = this.allShapes.values();
    for (Shape shape : allShapeCollection) {
      List<Shape> frames = toFrame(shape);
      for (int i = 0; i <= shape.getDisappearTime(); i++) {
        this.frameShapes.get(i).add(frames.get(i));
      }
    }
    return this.frameShapes;
  }

  /**
   * Returns a frame list that contains the given shape at each frame.
   * 
   * @param shape the shape to be added to frame
   * @return a frame list with all the shape status
   */
  private List<Shape> toFrame(Shape shape) {
    List<Shape> frameList = new ArrayList<>();
    Shape shapeToFrame = null;
    for (int i = 0; i <= shape.getDisappearTime(); i++) {
      // if first shape
      if (i == shape.getAppearTime()) {
        shapeToFrame = shape.copyShape();
      }
      // if not the first shape, copy the last frame
      if (i > shape.getAppearTime()) {
        shapeToFrame = frameList.get(i - 1).copyShape();
      }
      // update the animation of the shape
      List<Animation> shapeAnimationList = this.allAnimations.get(shape.getShapeName());
      if (shapeAnimationList != null) {
        for (Animation animation : shapeAnimationList) {
          if (animation.getFromTime() <= i && animation.getToTime() >= i) {
            if (animation instanceof Move) {
              Position pos = ((Move) animation).currentPosition(i);
              shapeToFrame.setPosition(pos);
            }
            if (animation instanceof ChangeColor) {
              Color color = ((ChangeColor) animation).currentColor(i);
              shapeToFrame.setColor(color);
            }
            if (animation instanceof Scale) {
              Size size = ((Scale) animation).currentSize(i);
              shapeToFrame.setSize(size);
            }
          }
        }
      }
      frameList.add(i, shapeToFrame);
    }
    return frameList;
  }

  /**
   * Returns the end time of the animation.
   * 
   * @return the end time
   */
  private int getEndTime() {
    int endTime = 0;
    Collection<Shape> allShapeCollection = this.allShapes.values();
    for (Shape shape : allShapeCollection) {
      if (shape.getDisappearTime() >= endTime) {
        endTime = shape.getDisappearTime();
      }
    }
    return endTime;
  }

  @Override
  public String toString() {
    StringBuilder description = new StringBuilder();
    description.append("Shapes:\n");

    Collection<Shape> allShapeCollection = this.allShapes.values();
    for (Shape shape : allShapeCollection) {
      description.append(shape.toString()).append("\n");
    }

    for (Animation animation : this.orderedAnimations) {
      description.append(animation.toString());
    }
    return description.toString();
  }
}