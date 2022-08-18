package cs5004.animator.model.util;

/**
 * This class represents the size of a shape. It uses 2 parameters.
 *
 */
public class Size {
  private double param1;
  private double param2;

  /**
   * A constructor that creates a new instance of a size. For a rectangle, two
   * parameters are the width and the height. For an oval, two parameters are the
   * x radius and the y radius.
   * 
   * @param param1 the first parameter
   * @param param2 the second parameter
   * @throws IllegalArgumentException thrown when any parameters is negative
   */
  public Size(double param1, double param2) throws IllegalArgumentException {
    if (param1 < 0 || param2 < 0) {
      throw new IllegalArgumentException("Any parameters should not be negative.");
    }
    this.param1 = param1;
    this.param2 = param2;
  }

  /**
   * Return the first parameter of the size.
   * 
   * @return the first parameter
   */
  public double getParam1() {
    return this.param1;
  }

  /**
   * Return the second parameter of the size.
   * 
   * @return the second parameter
   */
  public double getParam2() {
    return this.param2;
  }
}
