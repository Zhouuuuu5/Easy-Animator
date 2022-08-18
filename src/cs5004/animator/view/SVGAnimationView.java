package cs5004.animator.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.animation.AnimationType;
import cs5004.animator.model.animation.ChangeColor;
import cs5004.animator.model.animation.Move;
import cs5004.animator.model.animation.Scale;
import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.shape.ShapeType;

/**
 * This class represents the SVG animation view. It implements the view
 * interface.
 * 
 */
public class SVGAnimationView implements View {

  private Map<String, Shape> allShapes;
  private Map<String, List<Animation>> allAnimations;
  private String outputFileName;
  private String speed;

  /**
   * Constructs a new instance of SVG Animation View class.
   * 
   * @param allShapes      all the shapes of the view
   * @param allAnimations  all the animations of the view
   * @param outputFileName the name of the file which will be output finally
   * @param speed          the speed of the animation
   */
  public SVGAnimationView(Map<String, Shape> allShapes, Map<String, List<Animation>> allAnimations,
      String outputFileName, String speed) {
    this.allShapes = allShapes;
    this.allAnimations = allAnimations;
    this.outputFileName = outputFileName;
    this.speed = speed;
  }

  @Override
  public void display() throws IOException {
    String description = outputSVG();
    if (this.outputFileName.equals("")) {
      System.out.println(description);
    } else {
      try {
        FileWriter f = new FileWriter(this.outputFileName);
        f.write(description);
        f.close();
      } catch (IOException ioe) {
        throw new IOException("Failed to write a file.");
      }
    }
  }

  /**
   * Creates the output SVG string.
   * 
   * @return the svg string
   */
  private String outputSVG() {
    StringBuilder output = new StringBuilder();
    output.append("<svg width=\"1000\" height=\"1000\" viewBox=\"0 0 1000 1000\"\n"
        + "     xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\">\n");

    for (Map.Entry<String, Shape> entry : this.allShapes.entrySet()) {
      String shapeName = entry.getKey();
      Shape shape = entry.getValue();
      List<Animation> animations = this.allAnimations.get(shapeName);

      if (animations != null) {
        output.append(svgShape(shape));
        for (Animation animation : animations) {
          output.append(svgAnimation(animation));
        }
      } else {
        output.append(svgHiddenShape(shape));
      }

      if (shape.getType() == ShapeType.RECTANGLE) {
        output.append("</rect>\n");
      } else if (shape.getType() == ShapeType.OVAL) {
        output.append("</ellipse>\n");
      }
    }
    output.append("</svg>");
    return output.toString();
  }

  @Override
  public String toString() {
    return outputSVG();
  }

  /**
   * Create part of the svg shape element.
   * 
   * @param shape the shape to be created
   * @return the string representing part of the svg element
   */
  private String svgShapeElement(Shape shape) {
    String element = "";
    if (shape.getType() == ShapeType.RECTANGLE) {
      element = String.format(
          "\n<rect id=\"%s\" x=\"%.0f\" y=\"%.0f\" width=\"%.0f\" height=\"%.0f\""
              + " fill=\"rgb(%.0f,%.0f,%.0f)\"",
          shape.getShapeName(), shape.getPosition().getX(), shape.getPosition().getY(),
          shape.getSize().getParam1(), shape.getSize().getParam2(), shape.getColor().getRed(),
          shape.getColor().getGreen(), shape.getColor().getBlue());

    } else if (shape.getType() == ShapeType.OVAL) {
      element = String.format(
          "\n<ellipse id=\"%s\" cx=\"%.0f\" cy=\"%.0f\" rx=\"%.0f\" ry=\"%.0f\""
              + " fill=\"rgb(%.0f,%.0f,%.0f)\"",
          shape.getShapeName(), shape.getPosition().getX(), shape.getPosition().getY(),
          shape.getSize().getParam1() / 2, shape.getSize().getParam2() / 2,
          shape.getColor().getRed(), shape.getColor().getGreen(), shape.getColor().getBlue());
    }
    return element;
  }

  /**
   * Create the svg of a visible shape.
   * 
   * @param shape the shape to be created
   * @return the string representing the svg
   */
  private String svgShape(Shape shape) {
    String output = svgShapeElement(shape);
    output += " visibility=\"visible\" >\n";
    return output;
  }

  /**
   * Create the svg of an invisible shape. It should appears at certain time.
   * 
   * @param shape the shape to be created
   * @return the string representing the svg
   */
  private String svgHiddenShape(Shape shape) {
    String output = svgShapeElement(shape);
    output += " visibility=\"hidden\" >\n";

    int tempo = Integer.parseInt(this.speed);
    double startTime = shape.getAppearTime() * 1000 / tempo;
    double endTime = shape.getDisappearTime() * 1000 / tempo;
    double duration = endTime - startTime;

    output += String.format(
        "      <set attributeName=\"visibility\" attributeType=\"CSS\" to=\"visible\""
            + "          begin=\"%.0fms\" dur=\"%.0fms\" fill=\"freeze\" />\n",
        startTime, duration);

    return output;
  }

  /**
   * Create the svg of the animation.
   * 
   * @param animation the animation to be created
   * @return the string representing the svg
   */
  private String svgAnimation(Animation animation) {
    String x = "";
    String y = "";
    String size1 = "";
    String size2 = "";

    if (animation.getShapeType() == ShapeType.RECTANGLE) {
      x = "x";
      y = "y";
      size1 = "width";
      size2 = "height";
    } else if (animation.getShapeType() == ShapeType.OVAL) {
      x = "cx";
      y = "cy";
      size1 = "rx";
      size2 = "ry";
    }

    int tempo = Integer.parseInt(this.speed);
    double startTime = animation.getFromTime() * 1000 / tempo;
    double endTime = animation.getToTime() * 1000 / tempo;
    double duration = endTime - startTime;

    StringBuilder output = new StringBuilder();
    if (animation.getAnimationType() == AnimationType.MOVE) {
      Move move = (Move) animation;
      output.append(String.format(" <animate attributeName=\"%s\" attributeType=\"XML\"\n"
          + "             begin=\"%.0fms\" dur=\"%.0fms\" fill=\"freeze\" "
          + "from=\"%.0f\" to=\"%.0f\" />\n",
          x, startTime, duration, move.getFromPosition().getX(), move.getToPosition().getX()));
      output.append(String.format(" <animate attributeName=\"%s\" attributeType=\"XML\"\n"
          + "             begin=\"%.0fms\" dur=\"%.0fms\" fill=\"freeze\" "
          + "from=\"%.0f\" to=\"%.0f\" />\n",
          y, startTime, duration, move.getFromPosition().getY(), move.getToPosition().getY()));
    } else if (animation.getAnimationType() == AnimationType.CHANGECOLOR) {
      ChangeColor changeColor = (ChangeColor) animation;
      output.append(String.format(
          "      <animate attributeName=\"fill\" attributeType=\"XML\"\n"
              + "           from=\"rgb(%.0f,%.0f,%.0f)\" to=\"rgb(%.0f,%.0f,%.0f)\"\n"
              + "           begin=\"%.0fms\" dur=\"%.0fms\" fill=\"freeze\" />\n",
          changeColor.getFromColor().getRed(), changeColor.getFromColor().getGreen(),
          changeColor.getFromColor().getBlue(), changeColor.getToColor().getRed(),
          changeColor.getToColor().getGreen(), changeColor.getToColor().getBlue(), startTime,
          duration));

    } else if (animation.getAnimationType() == AnimationType.SCALE) {
      Scale scale = (Scale) animation;
      if (scale.getShapeType() == ShapeType.RECTANGLE) {
        output.append(String.format(" <animate attributeName=\"%s\" attributeType=\"XML\"\n"
            + "             begin=\"%.0fms\" dur=\"%.0fms\" fill=\"freeze\" "
            + "from=\"%.0f\" to=\"%.0f\" />\n",
            size1, startTime, duration, scale.getFromSize().getParam1(),
            scale.getToSize().getParam1()));
        output.append(String.format(" <animate attributeName=\"%s\" attributeType=\"XML\"\n"
            + "             begin=\"%.0fms\" dur=\"%.0fms\" fill=\"freeze\" "
            + "from=\"%.0f\" to=\"%.0f\" />\n",
            size2, startTime, duration, scale.getFromSize().getParam2(),
            scale.getToSize().getParam2()));
      } else if (scale.getShapeType() == ShapeType.OVAL) {
        output.append(String.format(" <animate attributeName=\"%s\" attributeType=\"XML\"\n"
            + "             begin=\"%.0fms\" dur=\"%.0fms\" fill=\"freeze\" "
            + "from=\"%.0f\" to=\"%.0f\" />\n",
            size1, startTime, duration, scale.getFromSize().getParam1() / 2,
            scale.getToSize().getParam1() / 2));
        output.append(String.format(" <animate attributeName=\"%s\" attributeType=\"XML\"\n"
            + "             begin=\"%.0fms\" dur=\"%.0fms\" fill=\"freeze\" "
            + "from=\"%.0f\" to=\"%.0f\" />\n",
            size2, startTime, duration, scale.getFromSize().getParam2() / 2,
            scale.getToSize().getParam2() / 2));
      }
    }
    return output.toString();
  }
}