package cs5004.animator.model.util;

/**
 * This class represents the color of a shape. It uses RGB color system.
 *
 */
public class Color {
  private double red;
  private double green;
  private double blue;

  /**
   * A constructor that creates a new instance of a Canvas.
   * 
   * @param red   the red code of the color
   * @param green the green code of the color
   * @param blue  the blue code of the color
   * @throws IllegalArgumentException thrown when any code is not between 0 and
   *                                  255
   */
  public Color(double red, double green, double blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("RGB values must be between 0 and 255.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Return the red code of the color.
   * 
   * @return the red code
   */
  public double getRed() {
    return this.red;
  }

  /**
   * Return the green code of the color.
   * 
   * @return the green code
   */
  public double getGreen() {
    return this.green;
  }

  /**
   * Return the blue code of the color.
   * 
   * @return the blue code
   */
  public double getBlue() {
    return this.blue;
  }

  /**
   * Return the string of the color.
   */
  @Override
  public String toString() {
    return String.format("(%.1f,%.1f,%.1f)", this.red, this.green, this.blue);
  }
}
