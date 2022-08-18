package view;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.animation.ChangeColor;
import cs5004.animator.model.animation.Move;
import cs5004.animator.model.animation.Scale;
import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Rectangle;
import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.shape.ShapeType;
import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;
import cs5004.animator.view.TextualView;
import cs5004.animator.view.View;

/**
 * This is the JUnit test for the textualView.
 *
 */
public class TextualViewTest {
  private View textualView;

  @Before
  public void setUp() {
    Model model = new ModelImpl();

    Shape rectangle = new Rectangle("R", new Position(200, 200), new Size(50, 100),
        new Color(255, 0, 0), 1, 100);
    Shape oval = new Oval("C", new Position(500, 100), new Size(60, 30), new Color(0, 0, 255),
        6, 100);
    Animation animation1 = new Move("R", ShapeType.RECTANGLE, 10, 50, new Position(200, 200),
        new Position(300, 300));
    Animation animation2 = new Move("C", ShapeType.OVAL, 20, 70, new Position(500, 100),
        new Position(500, 400));
    Animation animation3 = new ChangeColor("C", ShapeType.OVAL, 50, 80, new Color(0, 0, 255),
        new Color(0, 255, 0));
    Animation animation4 = new Scale("R", ShapeType.RECTANGLE, 51, 70, new Size(50, 100),
        new Size(25, 100));
    Animation animation5 = new Move("R", ShapeType.RECTANGLE, 70, 100, new Position(300, 300),
        new Position(200, 200));

    model.addShape(rectangle);
    model.addShape(oval);
    model.addAnimation(animation1);
    model.addAnimation(animation2);
    model.addAnimation(animation3);
    model.addAnimation(animation4);
    model.addAnimation(animation5);
    
    textualView = new TextualView(model.getAllShapes(), model.getOrderedAnimations(), "", "10");
  }

  /**
   * Test if the toString method works properly.
   */
  @Test
  public void testToString() {
    String expected = "Create (255.0,0.0,0.0) rectangle R "
        + "with corner at (200.0,200.0), width 50.0 and height 100.0\n"
        + "Create (0.0,0.0,255.0) oval C "
        + "with center at (500.0,100.0), radius 60.0 and 30.0\n"
        + "\n"
        + "R appears at time t=1 and disappears at time t=100\n"
        + "C appears at time t=6 and disappears at time t=100\n"
        + "\n"
        + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
        + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
        + "Shape C changes color from (0.0,0.0,255.0) to (0.0,255.0,0.0) from t=50 to t=80\n"
        + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
        + "from t=51 to t=70\n"
        + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n";
    
    assertEquals(expected, textualView.toString());
  }
}
