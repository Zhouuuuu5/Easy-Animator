package cs5004.animator.model.animation;

import cs5004.animator.model.shape.ShapeType;
import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;

/**
 * This class represents an abstract animation class that implements the
 * animation interface.
 * 
 */
public abstract class AbstractAnimation implements Animation {
  protected String shapeName;
  protected ShapeType shapeType;
  protected AnimationType animationType;
  protected Position fromPosition;
  protected Position toPosition;
  protected Size fromSize;
  protected Size toSize;
  protected Color fromColor;
  protected Color toColor;
  protected int fromTime;
  protected int toTime;

  /**
   * A constructor that creates a new instance of the abstract animation class.
   * 
   * @param shapeName     the name of the shape of the animation
   * @param shapeType     the shape type of the animation
   * @param animationType the type of the animation
   * @param fromTime      the starting time of the animation
   * @param toTime        the ending time of the animation
   * @throws IllegalArgumentException thrown when the parameters are null, or the
   *                                  starting time or ending time is negative, or
   *                                  the ending time is smaller than the starting
   *                                  time
   */
  public AbstractAnimation(String shapeName, ShapeType shapeType, AnimationType animationType,
      int fromTime, int toTime) throws IllegalArgumentException {
    if (shapeName == null || shapeType == null || animationType == null) {
      throw new IllegalArgumentException("Parameters cannot be null.");
    }
    if (fromTime < 0 || toTime < 0) {
      throw new IllegalArgumentException("Time parameters cannot be negative.");
    }
    if (toTime < fromTime) {
      throw new IllegalArgumentException(
          "The ending time cannot be earlier than the starting time.");
    }
    this.shapeName = shapeName;
    this.shapeType = shapeType;
    this.animationType = animationType;
    this.fromTime = fromTime;
    this.toTime = toTime;
  }

  @Override
  public String getShapeName() {
    return this.shapeName;
  }

  @Override
  public ShapeType getShapeType() {
    return this.shapeType;
  }

  @Override
  public AnimationType getAnimationType() {
    return this.animationType;
  }

  @Override
  public int getFromTime() {
    return this.fromTime;
  }

  @Override
  public int getToTime() {
    return this.toTime;
  }

  /**
   * Computes the intermediate state of a shape at any time using linear
   * interpolation.
   * 
   * @param a           initial state of the shape at time a
   * @param b           final state of the shape at time a
   * @param currentTime the current time
   * @return the intermediate state at current time
   * @throws IllegalArgumentException thrown when the current time is not in the
   *                                  interval of fromTime and toTime
   */
  protected double tweening(double a, double b, int currentTime) throws IllegalArgumentException {
    if (currentTime < fromTime || currentTime > toTime) {
      throw new IllegalArgumentException("The time input is not valid.");
    }

    double result = a * (toTime - currentTime) / (toTime - fromTime)
        + b * (currentTime - fromTime) / (toTime - fromTime);
    return result;
  }
}
