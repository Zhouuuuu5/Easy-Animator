package cs5004.animator.model;

import java.util.List;
import java.util.Map;

import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.util.Canvas;

/**
 * This interface represents the model. It contains the methods that all the
 * model should support.
 * 
 */
public interface Model {

  /**
   * Gets all shapes and stores in a map.
   * 
   * @return the map of all the shapes
   */
  Map<String, Shape> getAllShapes();

  /**
   * Gets all animations in a list. Animations are ordered by their starting time.
   * 
   * @return the list of ordered animations
   */
  List<Animation> getOrderedAnimations();

  /**
   * Gets all animations and stores in a map.
   * 
   * @return the map of all the animations
   */
  Map<String, List<Animation>> getAllAnimations();

  /**
   * Add a new shape to the model.
   * 
   * @param shape the shape to be added
   * @throws IllegalArgumentException thrown when the name of the shape has exist
   */
  void addShape(Shape shape) throws IllegalArgumentException;

  /**
   * Remove a shape from the model.
   * 
   * @param shapeName the name of the shape to be removed
   * @throws IllegalArgumentException thrown when the name of the shape doesn't
   *                                  exist
   */
  void removeShape(String shapeName) throws IllegalArgumentException;

  /**
   * Add a new animation to the model.
   * 
   * @param animation the animation to be added
   * @throws IllegalArgumentException thrown when the shape doesn't exist, the
   *                                  time of animation is not valid, or the
   *                                  animation has time conflict with existing
   *                                  animations
   */
  void addAnimation(Animation animation) throws IllegalArgumentException;

  /**
   * Returns the current canvas.
   * 
   * @return the current canvas
   */
  Canvas getCanvas();

  /**
   * Sets a new canvas.
   * 
   * @param canvas the canvas to be set
   */
  void setCanvas(Canvas canvas);

  /**
   * Returns the list of frames containing shapes with the corresponding state.
   * 
   * @return a list of frames that each frame contains Shapes with the
   *         corresponding state
   */
  List<List<Shape>> getFrameShapes();

}
