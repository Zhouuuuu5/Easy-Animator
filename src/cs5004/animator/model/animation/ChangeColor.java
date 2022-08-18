package cs5004.animator.model.animation;

import cs5004.animator.model.shape.ShapeType;
import cs5004.animator.model.util.Color;

/**
 * This class represents the class of changing color.
 *
 */
public class ChangeColor extends AbstractAnimation {

  /**
   * A constructor that creates a new instance of a change color class.
   * 
   * @param shapeName the name of the shape
   * @param shapeType the type of the shape
   * @param fromTime  the starting time of the animation
   * @param toTime    the ending time of the animation
   * @param fromColor the starting color of the animation
   * @param toColor   the ending color of the animation
   */
  public ChangeColor(String shapeName, ShapeType shapeType, int fromTime, int toTime,
      Color fromColor, Color toColor) {
    super(shapeName, shapeType, AnimationType.CHANGECOLOR, fromTime, toTime);
    this.fromColor = fromColor;
    this.toColor = toColor;
  }

  /**
   * Returns the starting color of the animation.
   * 
   * @return the starting color
   */
  public Color getFromColor() {
    return this.fromColor;
  }

  /**
   * Returns the ending color of the animation.
   * 
   * @return the ending color
   */
  public Color getToColor() {
    return this.toColor;
  }

  /**
   * Returns a string of describing the changing color of the animation.
   */
  @Override
  public String toString() {
    String description = "Shape " + this.shapeName + " " + this.animationType.toString() + " from "
        + this.fromColor.toString() + " to " + this.toColor.toString() + " from t=" + this.fromTime
        + " to t=" + this.toTime + "\n";
    return description;
  }

  /**
   * Returns the current color at given current time.
   * 
   * @param currentTime the time to get the current color
   * @return the current color
   */
  public Color currentColor(int currentTime) {
    double red = tweening(this.fromColor.getRed(), this.toColor.getRed(), currentTime);
    double green = tweening(this.fromColor.getGreen(), this.toColor.getGreen(), currentTime);
    double blue = tweening(this.fromColor.getBlue(), this.toColor.getBlue(), currentTime);
    return new Color(red, green, blue);
  }
}
