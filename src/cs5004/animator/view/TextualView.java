package cs5004.animator.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.shape.ShapeType;

/**
 * This class represents the textual view. It implements the view interface.
 * 
 */
public class TextualView implements View {

  private Map<String, Shape> allShapes;
  private List<Animation> orderedAnimations;
  private String outputFileName;

  /**
   * Constructs a new instance of a textual view class.
   * 
   * @param allShapes         all the shapes of the view
   * @param orderedAnimations all the animations in the order of the starting time
   * @param outputFileName    the name of the file which will be output finally
   * @param speed             the speed of the animation
   */
  public TextualView(Map<String, Shape> allShapes, List<Animation> orderedAnimations,
      String outputFileName, String speed) {
    this.allShapes = allShapes;
    this.orderedAnimations = orderedAnimations;
    this.outputFileName = outputFileName;
  }

  @Override
  public void display() throws IOException {
    String description = outputText();
    if (this.outputFileName.equals("")) {
      System.out.println(description);
    } else {
      try {
        FileWriter f = new FileWriter(this.outputFileName);
        f.write(description);
        f.close();
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }

  /**
   * Creates the text for output.
   * 
   * @return the string of output text
   */
  private String outputText() {
    StringBuilder output = new StringBuilder();

    // shape initialization
    Collection<Shape> allShapesForOutput = allShapes.values();
    for (Shape shape : allShapesForOutput) {
      output.append("Create ").append(shape.getColor().toString()).append(" ")
          .append(shape.getType().toString()).append(" ").append(shape.getShapeName()).append(" ");
      if (shape.getType() == ShapeType.RECTANGLE) {
        output.append("with corner at ").append(shape.getPosition().toString())
            .append(String.format(", width %.1f and height %.1f\n", shape.getSize().getParam1(),
                shape.getSize().getParam2()));
      } else {
        output.append("with center at ").append(shape.getPosition().toString())
            .append(String.format(", radius %.1f and %.1f\n", shape.getSize().getParam1(),
                shape.getSize().getParam2()));
      }
    }
    output.append("\n");

    // appear times and disappear times
    for (Shape shape : allShapesForOutput) {
      output.append(shape.getShapeName()).append(" appears at time t=")
          .append(shape.getAppearTime()).append(" and disappears at time t=")
          .append(shape.getDisappearTime()).append("\n");
    }
    output.append("\n");

    // animations
    for (Animation animation : orderedAnimations) {
      output.append(animation.toString());
    }
    return output.toString();

  }

  @Override
  public String toString() {
    return outputText();
  }
}
