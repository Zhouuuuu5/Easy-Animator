package cs5004.animator.model.animation;

import cs5004.animator.model.shape.ShapeType;
import cs5004.animator.model.util.Position;

/**
 * This class represents the class of Move.
 *
 */
public class Move extends AbstractAnimation {

  /**
   * A constructor that creates a new instance of a move class.
   * 
   * @param shapeName    the name of the shape of the animation
   * @param shapeType    the type of the shape
   * @param fromTime     the starting time of the animation
   * @param toTime       the ending time of the animation
   * @param fromPosition the starting position of the animation
   * @param toPosition   the ending position of the animation
   */
  public Move(String shapeName, ShapeType shapeType, int fromTime, int toTime,
      Position fromPosition, Position toPosition) {
    super(shapeName, shapeType, AnimationType.MOVE, fromTime, toTime);
    this.fromPosition = fromPosition;
    this.toPosition = toPosition;
  }

  /**
   * Returns the starting position of the animation.
   * 
   * @return the starting position
   */
  public Position getFromPosition() {
    return this.fromPosition;
  }

  /**
   * Returns the ending position of the animation.
   * 
   * @return the ending position
   */
  public Position getToPosition() {
    return this.toPosition;
  }

  /**
   * Returns a string of describing the move of the animation.
   */
  @Override
  public String toString() {
    String description = "Shape " + this.shapeName + " " + this.animationType.toString() + " from "
        + this.fromPosition.toString() + " to " + this.toPosition.toString() + " from t="
        + this.fromTime + " to t=" + this.toTime + "\n";
    return description;
  }

  /**
   * Returns the current position at given current time.
   * 
   * @param currentTime the time to get the current position
   * @return the current position
   */
  public Position currentPosition(int currentTime) {
    double x = tweening(this.fromPosition.getX(), this.toPosition.getX(), currentTime);
    double y = tweening(this.fromPosition.getY(), this.toPosition.getY(), currentTime);
    return new Position(x, y);
  }
}
