package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.animation.AnimationType;
import cs5004.animator.model.animation.ChangeColor;
import cs5004.animator.model.animation.Move;
import cs5004.animator.model.animation.Scale;
import cs5004.animator.model.shape.ShapeType;
import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;

/**
 * This is the JUnit test for the animation.
 *
 */
public class AnimationTest {

  private Animation animation1;
  private Animation animation2;
  private Animation animation3;
  private Animation animation4;
  private Animation animation5;

  @Before
  public void setUp() {
    animation1 = new Move("R", ShapeType.RECTANGLE, 10, 50, new Position(200, 200),
        new Position(300, 300));
    animation2 = new Move("C", ShapeType.OVAL, 20, 70, new Position(500, 100),
        new Position(500, 400));
    animation3 = new ChangeColor("C", ShapeType.OVAL, 50, 80, new Color(0, 0, 255),
        new Color(0, 255, 0));
    animation4 = new Scale("R", ShapeType.RECTANGLE, 51, 70, new Size(50, 100), new Size(25, 100));
    animation5 = new Move("R", ShapeType.RECTANGLE, 70, 100, new Position(300, 300),
        new Position(200, 200));
  }

  /**
   * Test if getShapeName method can work properly.
   */
  @Test
  public void testGetShapeName() {
    assertEquals("R", animation1.getShapeName());
    assertEquals("C", animation2.getShapeName());
    assertEquals("C", animation3.getShapeName());
    assertEquals("R", animation4.getShapeName());
    assertEquals("R", animation5.getShapeName());
  }

  /**
   * Test if getShapeType method can work properly.
   */
  @Test
  public void testGetShapeType() {
    assertEquals(ShapeType.RECTANGLE, animation1.getShapeType());
    assertEquals(ShapeType.OVAL, animation2.getShapeType());
    assertEquals(ShapeType.OVAL, animation3.getShapeType());
    assertEquals(ShapeType.RECTANGLE, animation4.getShapeType());
    assertEquals(ShapeType.RECTANGLE, animation5.getShapeType());
  }

  /**
   * Test if getAnimationType method can work properly.
   */
  @Test
  public void testGetAnimationType() {
    assertEquals(AnimationType.MOVE, animation1.getAnimationType());
    assertEquals(AnimationType.MOVE, animation2.getAnimationType());
    assertEquals(AnimationType.CHANGECOLOR, animation3.getAnimationType());
    assertEquals(AnimationType.SCALE, animation4.getAnimationType());
    assertEquals(AnimationType.MOVE, animation5.getAnimationType());
  }

  /**
   * Test if getFromTime method can work properly.
   */
  @Test
  public void testGetFromTime() {
    assertEquals(10, animation1.getFromTime());
    assertEquals(20, animation2.getFromTime());
    assertEquals(50, animation3.getFromTime());
    assertEquals(51, animation4.getFromTime());
    assertEquals(70, animation5.getFromTime());
  }

  /**
   * Test if getToTime method can work properly.
   */
  @Test
  public void testGetToTime() {
    assertEquals(50, animation1.getToTime());
    assertEquals(70, animation2.getToTime());
    assertEquals(80, animation3.getToTime());
    assertEquals(70, animation4.getToTime());
    assertEquals(100, animation5.getToTime());
  }

  /**
   * Test if the toString method work properly.
   */
  @Test
  public void testToString() {
    String expected1 = "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n";
    String expected2 = "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n";
    String expected3 = "Shape C changes color from (0.0,0.0,255.0) to (0.0,255.0,0.0) "
        + "from t=50 to t=80\n";
    String expected4 = "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, "
        + "Height: 100.0 from t=51 to t=70\n";
    String expected5 = "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n";

    assertEquals(expected1, animation1.toString());
    assertEquals(expected2, animation2.toString());
    assertEquals(expected3, animation3.toString());
    assertEquals(expected4, animation4.toString());
    assertEquals(expected5, animation5.toString());

  }

  /**
   * Test invalid animation starting time and ending time.
   */
  @Test
  public void testInvalidTime() {
    // appear time or disappear time < 0
    try {
      new Move("R2", ShapeType.RECTANGLE, -10, 10, new Position(200, 200), new Position(50, 100));
      fail("Should fail when given the invalid time");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }

    try {
      new Move("C2", ShapeType.OVAL, -20, -10, new Position(200, 200), new Position(50, 100));
      fail("Should fail when given the invalid time");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }

    try {
      new Move("R2", ShapeType.RECTANGLE, 120, 60, new Position(200, 200), new Position(50, 100));
      fail("Should fail when given the invalid time");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }

    try {
      new Move("C2", ShapeType.OVAL, 100, 0, new Position(200, 200), new Position(50, 100));
      fail("Should fail when given the invalid time");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }

  /**
   * Test if tweening in move animation works correctly.
   */
  @Test
  public void testCurrentPosition() {
    Move move = (Move) animation1;
    Position actual = move.currentPosition(40);
    assertEquals(275, actual.getX(), 0.001);
    assertEquals(275, actual.getY(), 0.001);
  }

  /**
   * Test if tweening in scale animation works correctly.
   */
  @Test
  public void testCurrentSize() {
    Scale scale = (Scale) animation4;
    Size actual = scale.currentSize(60);
    assertEquals(38.158, actual.getParam1(), 0.001);
    assertEquals(100, actual.getParam2(), 0.001);
  }

  /**
   * Test if tweening in color animation works correctly.
   */
  @Test
  public void testCurrentColor() {
    ChangeColor color = (ChangeColor) animation3;
    Color actual = color.currentColor(60);
    assertEquals(0, actual.getRed(), 0.001);
    assertEquals(85.0, actual.getGreen(), 0.001);
    assertEquals(170.0, actual.getBlue(), 0.001);
  }
}
