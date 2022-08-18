package cs5004.animator.model.util;

/**
 * This class represents the position of a shape. It uses x and y coordinate to
 * represents the position.
 *
 */
public class Position {
  private double x;
  private double y;

  /**
   * A constructor that creates a new instance of a position.
   * 
   * @param x the x coordinate of the position
   * @param y the y coordinate of the position
   */
  public Position(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the x coordinate of the position.
   * 
   * @return the x coordinate
   */
  public double getX() {
    return this.x;
  }

  /**
   * Returns the y coordinate of the position.
   * 
   * @return the y coordinate
   */
  public double getY() {
    return this.y;
  }


  @Override
  public String toString() {
    return String.format("(%.1f,%.1f)", this.x, this.y);
  }
}
