package cs5004.animator.model.animation;

import cs5004.animator.model.shape.ShapeType;

/**
 * This interface represents the animation. It contains the methods that all the
 * animations should support.
 */
public interface Animation {
  
  /**
   * Returns the name of the shape.
   * 
   * @return the name of the shape
   */
  String getShapeName();

  /**
   * Returns the shape type of the animation.
   * 
   * @return the shape type
   */
  ShapeType getShapeType();

  /**
   * Returns the animation type of the animation.
   * 
   * @return the animation type
   */
  AnimationType getAnimationType();

  /**
   * Returns the starting time of the animation.
   * 
   * @return the starting time of the animation
   */
  int getFromTime();

  /**
   * Returns the end time of the animation.
   * 
   * @return the end time of the animation
   */
  int getToTime();

}
