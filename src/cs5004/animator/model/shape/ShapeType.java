package cs5004.animator.model.shape;

/**
 * This enumerator contains all the shapes that an easy animator should support,
 * including rectangle or ellipse.
 *
 */
public enum ShapeType {
  RECTANGLE("rectangle"), OVAL("oval");

  private String type;

  /**
   * A constructor that creates a new instance of a shape type.
   * 
   * @param type the string of the type
   */
  private ShapeType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return this.type;
  }
}
