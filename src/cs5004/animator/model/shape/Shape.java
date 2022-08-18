package cs5004.animator.model.shape;

import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;

/**
 * This interface represents the shape. It contains the methods that all the
 * shapes should support.
 */
public interface Shape {

  /**
   * Returns the type of the shape.
   * 
   * @return The type of the shape
   */
  ShapeType getType();

  /**
   * Returns the type name of the shape.
   * 
   * @return The type name of the shape
   */
  String getShapeName();

  /**
   * Returns the x and y coordinate position of the shape. For a rectangle, the
   * position is the lower left corner. For an oval, the position is the center.
   * 
   * @return the x and y coordinate position of the shape
   */
  Position getPosition();

  /**
   * Returns the size of the shape.
   * 
   * @return the size of the shape
   */
  Size getSize();

  /**
   * Returns the color of the shape.
   * 
   * @return the color of the shape
   */
  Color getColor();

  /**
   * Get the appear time of the shape.
   * 
   * @return the appear time of the shape
   */
  int getAppearTime();

  /**
   * Get the disappear time of the shape.
   * 
   * @return the disappear time of the shape
   */
  int getDisappearTime();

  /**
   * Creates a copy of the shape.
   * 
   * @return a copy of the shape
   */
  Shape copyShape();

  /**
   * Sets the x and y coordinate position of a shape.
   * 
   * @param newPosition the position of the shape
   */
  void setPosition(Position newPosition);

  /**
   * Sets the size of a shape.
   * 
   * @param newSize the size of the shape
   */
  void setSize(Size newSize);

  /**
   * Sets the color of a shape.
   * 
   * @param newColor the color of the shape
   */
  void setColor(Color newColor);

  /**
   * Sets the appear time of a shape.
   * 
   * @param newAppearTime the appear time of the shape
   */
  void setAppearTime(int newAppearTime);

  /**
   * Sets the disappear time of a shape.
   * 
   * @param newDisappearTime the disappear time of the shape
   */
  void setDisappearTime(int newDisappearTime);

}
