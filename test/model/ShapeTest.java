package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Rectangle;
import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.shape.ShapeType;
import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;

/**
 * This is the JUnit test for the shape interface.
 *
 */
public class ShapeTest {
  private Shape rectangle;
  private Shape oval;

  @Before
  public void setUp() {
    rectangle = new Rectangle("R", new Position(200, 200), new Size(50, 100), 
        new Color(255, 0, 0), 1, 100);
    oval = new Oval("C", new Position(500, 100), new Size(60, 30), new Color(0, 0, 255), 6, 100);
  }
  
  /**
   * Test if the constructor throws exceptions when given invalid inputs.
   */
  @Test
  public void testConstructorInvalid() {
    // appear time or disappear time < 0
    try {
      new Rectangle("R2", new Position(200, 200), new Size(50, 100),
          new Color(255, 0, 0), -10, 100);
      fail("Should throw an exception when given the invalid inputs");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }

    try {
      new Oval("C2", new Position(500, 100), new Size(60, 30), new Color(0, 0, 255), -50,
          -10);
      fail("Should throw an exception when given the invalid inputs");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }

    // disappear time < appear time
    try {
      new Rectangle("R2", new Position(200, 200), new Size(50, 100),
          new Color(255, 0, 0), 100, 50);
      fail("Should throw an exception when given the invalid inputs");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }

    try {
      new Oval("C2", new Position(500, 100), new Size(60, 30), new Color(0, 0, 255), 150,
          50);
      fail("Should throw an exception when given the invalid inputs");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  /**
   * Test if the getType method works properly.
   */
  @Test
  public void testGetType() {
    assertEquals(ShapeType.RECTANGLE, rectangle.getType());
    assertEquals(ShapeType.OVAL, oval.getType());
  }

  /**
   * Test if the getShapeName method works properly.
   */
  @Test
  public void testGetShapeName() {
    assertEquals("R", rectangle.getShapeName());
    assertEquals("C", oval.getShapeName());
  }
  
  /**
   * Test if the getPosition method works properly.
   */
  @Test
  public void testGetPosition() {
    assertEquals(200, rectangle.getPosition().getX(), 0.001);
    assertEquals(200, rectangle.getPosition().getY(), 0.001);
    assertEquals(500, oval.getPosition().getX(), 0.001);
    assertEquals(100, oval.getPosition().getY(), 0.001);
  }

  /**
   * Test if the getSize method works properly.
   */
  @Test
  public void testGetSize() {
    assertEquals(50, rectangle.getSize().getParam1(), 0.001);
    assertEquals(100, rectangle.getSize().getParam2(), 0.001);
    assertEquals(60, oval.getSize().getParam1(), 0.001);
    assertEquals(30, oval.getSize().getParam2(), 0.001);
  }

  /**
   * Test if the getColor method works properly.
   */
  @Test
  public void testGetColor() {
    assertEquals(255, rectangle.getColor().getRed(), 0.001);
    assertEquals(0, rectangle.getColor().getGreen(), 0.001);
    assertEquals(0, rectangle.getColor().getBlue(), 0.001);
    assertEquals(0, oval.getColor().getRed(), 0.001);
    assertEquals(0, oval.getColor().getGreen(), 0.001);
    assertEquals(255, oval.getColor().getBlue(), 0.001);
  }

  /**
   * Test if the getAppearTime method works properly.
   */
  @Test
  public void testGetAppearTime() {
    assertEquals(1, rectangle.getAppearTime());
    assertEquals(6, oval.getAppearTime());
  }

  /**
   * Test if the getDisappearTime method works properly.
   */
  @Test
  public void testGetDisappearTime() {
    assertEquals(100, rectangle.getDisappearTime());
    assertEquals(100, oval.getDisappearTime());
  }

  /**
   * Test if the setPosition method works properly.
   */
  @Test
  public void testSetPosition() {
    rectangle.setPosition(new Position(300, 300));
    assertEquals(300, rectangle.getPosition().getX(), 0.001);
    assertEquals(300, rectangle.getPosition().getY(), 0.001);

    oval.setPosition(new Position(100, 50));
    assertEquals(100, oval.getPosition().getX(), 0.001);
    assertEquals(50, oval.getPosition().getY(), 0.001);
  }

  /**
   * Test if the setSize method works properly.
   */
  @Test
  public void testSetSize() {
    rectangle.setSize(new Size(100, 125));
    assertEquals(100, rectangle.getSize().getParam1(), 0.001);
    assertEquals(125, rectangle.getSize().getParam2(), 0.001);

    oval.setSize(new Size(30, 15));
    assertEquals(30, oval.getSize().getParam1(), 0.001);
    assertEquals(15, oval.getSize().getParam2(), 0.001);
  }
  
  /**
   * Test if the setColor method works properly.
   */
  @Test
  public void testSetColor() {
    rectangle.setColor(new Color(255, 255, 255));
    assertEquals(255, rectangle.getColor().getRed(), 0.001);
    assertEquals(255, rectangle.getColor().getGreen(), 0.001);
    assertEquals(255, rectangle.getColor().getBlue(), 0.001);

    oval.setColor(new Color(125, 125, 0));
    assertEquals(125, oval.getColor().getRed(), 0.001);
    assertEquals(125, oval.getColor().getGreen(), 0.001);
    assertEquals(0, oval.getColor().getBlue(), 0.001);
  }

  /**
   * Test if the setAppearTime method works properly.
   */
  @Test
  public void testSetAppearTime() {
    rectangle.setAppearTime(10);
    assertEquals(10, rectangle.getAppearTime());

    oval.setAppearTime(5);
    assertEquals(5, oval.getAppearTime());
  }

  /**
   * Test if the setDisappearTime method works properly.
   */
  @Test
  public void testSetDisappearTime() {
    rectangle.setDisappearTime(120);
    assertEquals(120, rectangle.getDisappearTime());

    oval.setDisappearTime(50);
    assertEquals(50, oval.getDisappearTime());
  }

  /**
   * Test if the copyShape method works properly.
   */
  @Test
  public void testCopyShape() {
    Rectangle newRec = (Rectangle) rectangle.copyShape();
    assertEquals(newRec.toString(), rectangle.toString());

    Oval newOval = (Oval) oval.copyShape();
    assertEquals(newOval.toString(), oval.toString());
  }
  
  /**
   * Test if the toString method works properly.
   */
  @Test
  public void testToString() {
    String expected1 =
        "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (255.0,0.0,0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n";
    assertEquals(expected1, rectangle.toString());
    
    String expected2 =
        "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,255.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n";
    assertEquals(expected2, oval.toString());
  }

  
  /**
   * Test invalid color values. All RGB values should between 0 and 255.
   */
  @Test
  public void testInvalidColor() {
    double red;
    double green;
    double blue;
    for (int i = 0; i < 1000; i++) {
      red = Math.random() * 500 * (Math.random() > 0.5 ? 1 : -1);
      green = Math.random() * 500 * (Math.random() > 0.5 ? 1 : -1);
      blue = Math.random() * 500 * (Math.random() > 0.5 ? 1 : -1);
      if (red >= 0 && red <= 255 && green >= 0 && green <= 255 && blue >= 0 && blue <= 255) {
        continue;
      } else {
        try {
          new Color(red, green, blue);
          fail("Should throw an exception when given the invalid inputs");
        } catch (IllegalArgumentException e) {
          e.getMessage();
        }
      }
    }
  }

  /**
   * Test invalid size. Size parameters should not be negative.
   */
  @Test
  public void testInvalidSize() {
    double param1;
    double param2;
    for (int i = 0; i < 1000; i++) {
      param1 = Math.random() * -500 + 1;
      param2 = Math.random() * -500 + 1;
      try {
        new Size(param1, param2);
        fail("Should throw an exception when given the invalid inputs");
      } catch (IllegalArgumentException e) {
        e.getMessage();
      }
    }
  }

}
