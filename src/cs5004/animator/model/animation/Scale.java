package cs5004.animator.model.animation;

import cs5004.animator.model.shape.ShapeType;
import cs5004.animator.model.util.Size;

/**
 * This class represents the class of Scale.
 *
 */
public class Scale extends AbstractAnimation {

  /**
   * A constructor that creates a new instance of a scale class.
   * 
   * @param shapeName the name of the shape of the animation
   * @param shapeType the type of the shape
   * @param fromTime  the starting time of the animation
   * @param toTime    the ending time of the animation
   * @param fromSize  the starting size of the animation
   * @param toSize    the ending size of the animation
   */
  public Scale(String shapeName, ShapeType shapeType, int fromTime, int toTime, Size fromSize,
      Size toSize) {
    super(shapeName, shapeType, AnimationType.SCALE, fromTime, toTime);
    this.fromSize = fromSize;
    this.toSize = toSize;
  }

  /**
   * Returns the starting size of the animation.
   * 
   * @return the starting size
   */
  public Size getFromSize() {
    return this.fromSize;
  }

  /**
   * Returns the ending size of the animation.
   * 
   * @return the ending size
   */
  public Size getToSize() {
    return this.toSize;
  }

  /**
   * Returns a string of describing the scale of the animation.
   */
  @Override
  public String toString() {
    String shapeString = "";
    if (this.shapeType == ShapeType.RECTANGLE) {
      shapeString = String.format(" from Width: %.1f, Height: %.1f to Width: %.1f, Height: %.1f",
          this.fromSize.getParam1(), this.fromSize.getParam2(), this.toSize.getParam1(),
          this.toSize.getParam2());
    } else if (this.shapeType == ShapeType.OVAL) {
      shapeString = String.format(
          " from X radius: %.1f, Y radius: %.1f to X radius: %.1f, Y radius: %.1f",
          this.fromSize.getParam1(), this.fromSize.getParam2(), this.toSize.getParam1(),
          this.toSize.getParam2());
    }

    String description = "Shape " + this.shapeName + " " + this.animationType.toString()
        + shapeString + " from t=" + this.fromTime + " to t=" + this.toTime + "\n";
    return description;
  }

  /**
   * Returns the current size at given current time.
   * 
   * @param currentTime the time to get the current size
   * @return the current size
   */
  public Size currentSize(int currentTime) {
    double param1 = tweening(this.fromSize.getParam1(), this.toSize.getParam1(), currentTime);
    double param2 = tweening(this.fromSize.getParam2(), this.toSize.getParam2(), currentTime);
    return new Size(param1, param2);
  }
}