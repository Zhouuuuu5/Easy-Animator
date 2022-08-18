package cs5004.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Rectangle;
import cs5004.animator.model.shape.Shape;

/**
 * This class represents a visual animation panel.
 * 
 */
@SuppressWarnings({ "hiding", "serial" })
public class VisualAnimationPanel<Shape> extends JPanel {
  
  protected List<List<Shape>> frameShapes;
  protected int total = 0;

  /**
   * Constructs an instance of visual animation panel.
   * 
   * @param frameShapes the frame list of the shape
   */
  public VisualAnimationPanel(List<List<Shape>> frameShapes) {
    this.frameShapes = frameShapes;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setBackground(Color.white);
    if (this.total == this.frameShapes.size()) {
      return;
    }
    
    List<Shape> frame = this.frameShapes.get(total++);
    for (Shape frameShape : frame) {
      Graphics2D graphics2D = (Graphics2D) g;
      if (frameShape instanceof Rectangle) {
        Rectangle rectangle = (Rectangle) frameShape;
        graphics2D.setColor(new Color((int) rectangle.getColor().getRed(),
            (int) rectangle.getColor().getGreen(), (int) rectangle.getColor().getBlue()));
        graphics2D.fillRect((int) rectangle.getPosition().getX(),
            (int) rectangle.getPosition().getY(), (int) rectangle.getSize().getParam1(),
            (int) rectangle.getSize().getParam2());
      } else if (frameShape instanceof Oval) {
        Oval oval = (Oval) frameShape;
        graphics2D.setColor(new Color((int) oval.getColor().getRed(),
            (int) oval.getColor().getGreen(), (int) oval.getColor().getBlue()));
        graphics2D.fillOval((int) oval.getPosition().getX(), (int) oval.getPosition().getY(),
            (int) oval.getSize().getParam1(), (int) oval.getSize().getParam2());
      }
    }
  }
}
