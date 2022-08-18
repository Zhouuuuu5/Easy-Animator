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
import cs5004.animator.view.SVGAnimationView;
import cs5004.animator.view.View;

/**
 * This is the JUnit test for the SVGAnimationView.
 *
 */
public class SVGAnimationViewTest {
  private View svgAnimationView;

  @Before
  public void setUp() {
    Model model = new ModelImpl();

    Shape rectangle = new Rectangle("R", new Position(200, 200), new Size(50, 100),
        new Color(255, 0, 0), 1, 100);
    Shape oval = new Oval("C", new Position(500, 100), new Size(60, 30), new Color(0, 0, 255), 6,
        100);
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

    svgAnimationView = new SVGAnimationView(model.getAllShapes(), 
        model.getAllAnimations(), "", "10");
  }

  /**
   * Test if the display method can work properly.
   */
  @Test
  public void test() {
    String expected = "<svg width=\"1000\" height=\"1000\" viewBox=\"0 0 1000 1000\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n"
        + "\n"
        + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" "
        + "fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n"
        + " <animate attributeName=\"x\" attributeType=\"XML\"\n"
        + "             begin=\"1000ms\" dur=\"4000ms\" fill=\"freeze\" "
        + "from=\"200\" to=\"300\" />\n"
        + " <animate attributeName=\"y\" attributeType=\"XML\"\n"
        + "             begin=\"1000ms\" dur=\"4000ms\" fill=\"freeze\" "
        + "from=\"200\" to=\"300\" />\n"
        + " <animate attributeName=\"width\" attributeType=\"XML\"\n"
        + "             begin=\"5100ms\" dur=\"1900ms\" fill=\"freeze\" "
        + "from=\"50\" to=\"25\" />\n"
        + " <animate attributeName=\"height\" attributeType=\"XML\"\n"
        + "             begin=\"5100ms\" dur=\"1900ms\" fill=\"freeze\" "
        + "from=\"100\" to=\"100\" />\n"
        + " <animate attributeName=\"x\" attributeType=\"XML\"\n"
        + "             begin=\"7000ms\" dur=\"3000ms\" fill=\"freeze\" "
        + "from=\"300\" to=\"200\" />\n"
        + " <animate attributeName=\"y\" attributeType=\"XML\"\n"
        + "             begin=\"7000ms\" dur=\"3000ms\" fill=\"freeze\" "
        + "from=\"300\" to=\"200\" />\n"
        + "</rect>\n"
        + "\n"
        + "<ellipse id=\"C\" cx=\"500\" cy=\"100\" rx=\"30\" ry=\"15\" "
        + "fill=\"rgb(0,0,255)\" visibility=\"visible\" >\n"
        + " <animate attributeName=\"cx\" attributeType=\"XML\"\n"
        + "             begin=\"2000ms\" dur=\"5000ms\" fill=\"freeze\" "
        + "from=\"500\" to=\"500\" />\n"
        + " <animate attributeName=\"cy\" attributeType=\"XML\"\n"
        + "             begin=\"2000ms\" dur=\"5000ms\" fill=\"freeze\" "
        + "from=\"100\" to=\"400\" />\n"
        + "      <animate attributeName=\"fill\" attributeType=\"XML\"\n"
        + "           from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\"\n"
        + "           begin=\"5000ms\" dur=\"3000ms\" fill=\"freeze\" />\n"
        + "</ellipse>\n"
        + "</svg>";
        
    assertEquals(expected, svgAnimationView.toString());
  }

}
