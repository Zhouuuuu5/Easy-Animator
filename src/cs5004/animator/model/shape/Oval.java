package cs5004.animator.model.shape;

import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;

/**
 * This class represents an oval shape.
 * 
 */
public class Oval extends AbstractShape {

  /**
   * A constructor that creates a new instance of the oval class.
   * 
   * @param shapeName     the name of the oval 
   * @param position      the position of the oval 
   * @param size          the size of the oval 
   * @param color         the color of the oval 
   * @param appearTime    the appear time of the oval 
   * @param disappearTime the disappear time of the oval 
   */
  public Oval(String shapeName, Position position, Size size, Color color, int appearTime,
      int disappearTime) {
    super(shapeName, ShapeType.OVAL, position, size, color, appearTime, disappearTime);
  }

  @Override
  public Oval copyShape() {
    return new Oval(this.shapeName, this.position, this.size, this.color, this.appearTime,
        this.disappearTime);
  }
  
  /**
   * Returns a string description of an oval.
   * Here is what a description might look like:
   * Name: C
   * Type: oval
   * Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
   * Appears at t=6
   * Disappears at t=100
   */
  @Override
  public String toString() {
    String description = "Name: " + this.shapeName + "\n" 
        + "Type: " + this.type.toString() + "\n" 
        + "Center: " + this.position.toString()
        + String.format(", X radius: %.1f, Y radius: %.1f, ", 
            this.size.getParam1(), this.size.getParam2()) 
        + "Color: " + this.color.toString() + "\n" 
        + "Appears at t=" + this.appearTime + "\n"
        + "Disappears at t=" + this.disappearTime + "\n";
    return description;
  }
}
