package cs5004.animator.model.util;

/**
 * This class represents the Canvas. The coordinates at the top left of the
 * canvas represent the position. The width and height represent the size of the
 * canvas.
 *
 */
public class Canvas {
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * A constructor that creates a new instance of a Canvas.
   * 
   * @param x      the x coordinate at the top left of the canvas
   * @param y      the y coordinate at the top left of the canvas
   * @param width  the width of the canvas
   * @param height the height of the canvas
   * @throws IllegalArgumentException thrown when the width or height is negative
   */
  public Canvas(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width or height can not be negative.");
    }
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * Returns the x coordinate at the top left of the canvas.
   * 
   * @return the x coordinate
   */
  public int getX() {
    return this.x;
  }

  /**
   * Returns the y coordinate at the top left of the canvas.
   * 
   * @return the y coordinate
   */
  public int getY() {
    return this.y;
  }

  /**
   * Returns the width of the canvas.
   * 
   * @return the width
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the height of the canvas.
   * 
   * @return the height
   */
  public int getHeight() {
    return this.height;
  }

  @Override
  public String toString() {
    return this.x + " " + this.y + " " + this.width + " " + this.height + "\n";
  }
}
