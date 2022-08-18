package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

/**
 * This is the JUnit test for the model.
 *
 */
public class ModelImplTest {

  private Model emptymodel;
  private Model model;

  @Before
  public void setUp() {
    emptymodel = new ModelImpl();
    model = new ModelImpl();

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
  }

  /**
   * Test if the getAllShapes method can work properly.
   */
  @Test
  public void testGetAllShapes() {
    assertEquals(0, emptymodel.getAllShapes().size());
    assertEquals(2, model.getAllShapes().size());
  }
  
  /**
   * Test if the getOrderedAnimations method can work properly.
   */
  @Test
  public void testOrderedAnimations() {
    assertEquals(0, emptymodel.getOrderedAnimations().size());
    assertEquals(5, model.getOrderedAnimations().size());
  }
  
  /**
   * Test if the getAllAnimations method can work properly.
   */
  @Test
  public void testGetAllAnimations() {
    assertEquals(0, emptymodel.getAllAnimations().size());
    assertEquals(2, model.getAllAnimations().size());
  }
  
  /**
   * Test if the add shape method can work properly.
   */
  @Test
  public void testAddShape() {
    // add shape to the empty model
    assertEquals(0, emptymodel.getAllShapes().size());
    
    Shape rectangle = new Rectangle("R", new Position(200, 200), new Size(50, 100),
        new Color(255, 0, 0), 1, 100);
    emptymodel.addShape(rectangle);
    assertEquals(1, emptymodel.getAllShapes().size());
    String expected = "Shapes:\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (255.0,0.0,0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n";
    assertEquals(expected, emptymodel.toString());
    
    Shape oval = new Oval("C", new Position(500, 100), new Size(60, 30), new Color(0, 0, 255),
        6, 100);
    emptymodel.addShape(oval);
    assertEquals(2, emptymodel.getAllShapes().size());
    expected = "Shapes:\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (255.0,0.0,0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,255.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n";
    assertEquals(expected, emptymodel.toString());
    
    // add shape to the model which has existing shapes
    assertEquals(2, model.getAllShapes().size());
    expected = "Shapes:\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (255.0,0.0,0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,255.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
        + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
        + "Shape C changes color from (0.0,0.0,255.0) to (0.0,255.0,0.0) from t=50 to t=80\n"
        + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
        + "from t=51 to t=70\n"
        + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n";
    assertEquals(expected, model.toString());
    
    Shape rectangle2 = new Rectangle("R2", new Position(100, 100), new Size(25, 50), 
        new Color(255, 255, 255), 1, 100);
    model.addShape(rectangle2);
    
    assertEquals(3, model.getAllShapes().size());
    expected = "Shapes:\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (255.0,0.0,0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,255.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Name: R2\n"
        + "Type: rectangle\n"
        + "Min corner: (100.0,100.0), Width: 25.0, Height: 50.0, Color: (255.0,255.0,255.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
        + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
        + "Shape C changes color from (0.0,0.0,255.0) to (0.0,255.0,0.0) from t=50 to t=80\n"
        + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
        + "from t=51 to t=70\n"
        + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n";
    assertEquals(expected, model.toString());
  }
  
  /**
   * Test if the add shape method can throw IllegalArgumentException.
   */
  @Test
  public void testAddShapeInvalid() {
    Shape rectangle2 = new Rectangle("R", new Position(100, 100), new Size(25, 50), 
        new Color(255, 255, 255), 
        1, 100);
    try {
      model.addShape(rectangle2);
      fail("Should fail when adding an existing shape.");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
    
    Shape oval2 = new Oval("C", new Position(500, 100), new Size(60, 30), new Color(0, 0, 255), 
        6, 100);
    try {
      model.addShape(oval2);
      fail("Should fail when adding an existing shape.");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
    
    assertEquals(2, model.getAllShapes().size());
    String expected = "Shapes:\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (255.0,0.0,0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,255.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
        + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
        + "Shape C changes color from (0.0,0.0,255.0) to (0.0,255.0,0.0) from t=50 to t=80\n"
        + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
        + "from t=51 to t=70\n"
        + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n";
    assertEquals(expected, model.toString());
  }
  
  /**
   * Test if the remove shape method can work properly.
   */
  @Test
  public void testRemoveShape() {
    assertEquals(2, model.getAllShapes().size());
    assertEquals(2, model.getAllAnimations().size());
    assertEquals(5, model.getOrderedAnimations().size());
    
    // invalid
    try {
      model.removeShape("R2");
      fail("Should fail because of removing an unexisting shape");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
    
    // valid
    model.removeShape("R");
    assertEquals(1, model.getAllShapes().size());
    assertEquals(1, model.getAllAnimations().size());
    assertEquals(2, model.getOrderedAnimations().size());
    
    String expected = "Shapes:\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,255.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
        + "Shape C changes color from (0.0,0.0,255.0) to (0.0,255.0,0.0) from t=50 to t=80\n";
    assertEquals(expected, model.toString());
    
    model.removeShape("C");
    assertEquals(0, model.getAllShapes().size());
    assertEquals(0, model.getAllAnimations().size());
    assertEquals(0, model.getOrderedAnimations().size());
    
    expected = "Shapes:\n";
    assertEquals(expected, model.toString());
  }
  
  /**
   * Test if the add animation method can work properly.
   */
  @Test
  public void testAddAnimation() {
    Shape oval = new Oval("C", new Position(500, 100), new Size(60, 30), new Color(0, 0, 255), 
        6, 100);
    Animation animation1 = new Move("C", ShapeType.OVAL, 20, 70, new Position(500, 100), 
        new Position(500, 400));
    Animation animation2 = new ChangeColor("C", ShapeType.OVAL, 50, 80, new Color(0, 0, 255), 
        new Color(0, 255, 0));
    Animation animation3 = new Scale("C", ShapeType.OVAL, 50, 80, new Size(60, 30), 
        new Size(30, 60));
    emptymodel.addShape(oval);
    emptymodel.addAnimation(animation1);
    emptymodel.addAnimation(animation2);
    emptymodel.addAnimation(animation3);
    
    String expected = "Shapes:\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,255.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
        + "Shape C changes color from (0.0,0.0,255.0) to (0.0,255.0,0.0) from t=50 to t=80\n"
        + "Shape C scales from X radius: 60.0, Y radius: 30.0 to X radius: 30.0, Y radius: 60.0 "
        + "from t=50 to t=80\n";
    assertEquals(expected, emptymodel.toString());
  }
  
  /**
   * Test if the add animation method can throw exceptions when given invalid conditions.
   */
  @Test
  public void testAddAnimationInvalid() {
    Shape oval = new Oval("C", new Position(500, 100), new Size(60, 30), new Color(0, 0, 255), 
        6, 100);
    Animation animation1 = new Move("C", ShapeType.OVAL, 20, 70, new Position(500, 100), 
        new Position(500, 400));
    Animation animation2 = new ChangeColor("C", ShapeType.OVAL, 50, 80, new Color(0, 0, 255), 
        new Color(0, 255, 0));
    Animation animation3 = new Scale("C", ShapeType.OVAL, 50, 80, new Size(60, 30), 
        new Size(30, 60));
    emptymodel.addShape(oval);
    emptymodel.addAnimation(animation1);
    emptymodel.addAnimation(animation2);
    emptymodel.addAnimation(animation3);

    // the shape doesn't exist
    Animation animation4 = new Move("R", ShapeType.RECTANGLE, 10, 50, new Position(200, 200),
        new Position(300, 300));
    try {
      emptymodel.addAnimation(animation4);
      fail("Should throw an exception");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }

    // animation time is not in the valid interval
    Animation animation5 = new Move("C", ShapeType.OVAL, 90, 110, new Position(500, 400),
        new Position(500, 100));
    try {
      emptymodel.addAnimation(animation5);
      fail("Should throw an exception");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }

    // animation has time conflicts
    Animation animation6 = new Scale("C", ShapeType.OVAL, 50, 60, new Size(60, 30), new Size(5, 5));
    try {
      emptymodel.addAnimation(animation6);
      fail("Should throw an exception");
    } catch (IllegalArgumentException e) {
      e.getMessage();
    }
  }
  
  /**
   * Test if the toString method can work properly.
   */
  @Test
  public void testToString() {
    String expected = "Shapes:\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (255.0,0.0,0.0)\n"
        + "Appears at t=1\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Name: C\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,255.0)\n"
        + "Appears at t=6\n"
        + "Disappears at t=100\n"
        + "\n"
        + "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
        + "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
        + "Shape C changes color from (0.0,0.0,255.0) to (0.0,255.0,0.0) from t=50 to t=80\n"
        + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
        + "from t=51 to t=70\n"
        + "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n";
    assertEquals(expected, model.toString());
  }
}
