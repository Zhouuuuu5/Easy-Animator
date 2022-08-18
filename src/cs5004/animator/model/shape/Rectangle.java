package cs5004.animator.model.shape;

import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;

/**
 * This class represents an Rectangle shape.
 * 
 */
public class Rectangle extends AbstractShape {

  /**
   * A constructor that creates a new instance of the Rectangle class.
   * 
   * @param shapeName          the name of the Rectangle
   * @param position      the position of the Rectangle
   * @param size          the size of the Rectangle
   * @param color         the color of the Rectangle
   * @param appearTime    the appear time of the Rectangle
   * @param disappearTime the disappear time of the Rectangle
   */
  public Rectangle(String shapeName, Position position, Size size, Color color, int appearTime,
      int disappearTime) {
    super(shapeName, ShapeType.RECTANGLE, position, size, color, appearTime, disappearTime);
  }

  @Override
  public Rectangle copyShape() {
    return new Rectangle(this.shapeName, this.position, this.size, this.color, this.appearTime,
        this.disappearTime);
  }
  
  /**
   * Returns a string description of a Rectangle.
   * Here is what a description might look like:
   * Name: R
   * Type: rectangle
   * Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
   * Appears at t=1
   * Disappears at t=100
   */
  @Override
  public String toString() {
    String description = "Name: " + this.shapeName + "\n" 
        + "Type: " + this.type.toString() + "\n" 
        + "Min corner: " + this.position.toString()
        + String.format(", Width: %.1f, Height: %.1f, ", 
            this.size.getParam1(), this.size.getParam2()) 
        + "Color: " + this.color.toString() + "\n" 
        + "Appears at t=" + this.appearTime + "\n"
        + "Disappears at t=" + this.disappearTime + "\n";
    return description;
  }
}
