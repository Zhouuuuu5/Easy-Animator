package cs5004.animator.model.shape;

import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;

/**
 * This class represents an abstract class that implements the Shape interface.
 *
 */
public abstract class AbstractShape implements Shape {
  protected String shapeName;
  protected ShapeType type;
  protected Position position;
  protected Size size;
  protected Color color;
  protected int appearTime;
  protected int disappearTime;

  /**
   * A constructor that creates a new instance of the abstract shape class.
   * 
   * @param shapeName     the name of the shape
   * @param type          the type of the shape
   * @param position      the position of the shape
   * @param size          the size of the shape
   * @param color         the color of the shape
   * @param appearTime    the appear time of the shape
   * @param disappearTime the disappear time of the shape
   * @throws IllegalArgumentException thrown when the parameters are null, or the
   *                                  appear time or disappear time is negative,
   *                                  or the disappear time is smaller than the
   *                                  appear time
   */
  public AbstractShape(String shapeName, ShapeType type, Position position, Size size, Color color,
      int appearTime, int disappearTime) throws IllegalArgumentException {
    if (shapeName == null || type == null || position == null || size == null || color == null) {
      throw new IllegalArgumentException("Parameters cannot be null.");
    }
    if (appearTime < 0 || disappearTime < 0) {
      throw new IllegalArgumentException("Time cannot be negative.");
    }
    if (disappearTime < appearTime) {
      throw new IllegalArgumentException("Disappear time cannot be smaller than appear time.");
    }
    this.shapeName = shapeName;
    this.type = type;
    this.position = position;
    this.size = size;
    this.color = color;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

  @Override
  public String getShapeName() {
    return this.shapeName;
  }

  @Override
  public Position getPosition() {
    return this.position;
  }

  @Override
  public Size getSize() {
    return this.size;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public int getAppearTime() {
    return this.appearTime;
  }

  @Override
  public int getDisappearTime() {
    return this.disappearTime;
  }

  @Override
  public void setPosition(Position newPosition) {
    this.position = newPosition;
  }

  @Override
  public void setSize(Size newSize) {
    this.size = newSize;
  }

  @Override
  public void setColor(Color newColor) {
    this.color = newColor;
  }

  @Override
  public void setAppearTime(int newAppearTime) {
    this.appearTime = newAppearTime;
  }

  @Override
  public void setDisappearTime(int newDisappearTime) {
    this.disappearTime = newDisappearTime;
  }

}
