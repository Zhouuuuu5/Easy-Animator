package cs5004.animator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.ControllerImpl;
import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationBuilderImpl;
import cs5004.animator.util.AnimationReader;

/**
 * Represents the main class that runs the easy animator program.
 *
 */
public final class EasyAnimator {

  /**
   * This main() method will be the entry point for the program. User will need to
   * create an Application run configuration in Eclipse/IntelliJ that chooses
   * cs5004.animator.EasyAnimator as its main class. In this run configuration,
   * user can also specify command-line arguments, such as the file to read in,
   * and the view name to use. The options for the view name are "text", "visual"
   * and "svg".
   * 
   * <p>The command-line arguments will be of the form:
   *
   * <p>-in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go"
   * -speed "integer-ticks-per-second"
   * 
   * @param args the command-line arguments
   * @throws IOException thrown if exceptions produced by failed or interrupted
   *                     I/O operations occurred
   */
  public static void main(String[] args) throws IOException {
    if (!validArgs(args)) {
      JOptionPane.showMessageDialog(new JFrame(), "Invalid command-line arguments.", "ERROR",
          JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
    String inputFileName = "";
    String outputFileName = "";
    String viewType = "";
    String speed = "1";

    for (int i = 0; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("-in")) {
        inputFileName = args[i + 1];
      } else if (args[i].equalsIgnoreCase("-out")) {
        outputFileName = args[i + 1];
      } else if (args[i].equalsIgnoreCase("-view")) {
        viewType = args[i + 1];
      } else if (args[i].equalsIgnoreCase("-speed")) {
        speed = args[i + 1];
      }
    }

    // model
    AnimationBuilder<Model> builder = new AnimationBuilderImpl();
    Model model = new ModelImpl();
    try {
      InputStream inputStream = new FileInputStream(inputFileName);
      model = AnimationReader.parseFile(new InputStreamReader(inputStream), builder);
    } catch (IOException e) {
      JOptionPane.showMessageDialog(new JFrame(), "Fail to read the file.", "ERROR",
          JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }

    // controller
    Controller controller = new ControllerImpl(model, viewType, outputFileName, speed);

    controller.run();
  }

  /**
   * To verify if the arguments are valid or not.
   * 
   * @param args the command-line arguments
   * @return true of valid arguments, and false otherwise
   */
  private static boolean validArgs(String[] args) {
    boolean inputArgs = false;
    boolean viewArgs = false;
    if (args.length == 4 || args.length == 6 || args.length == 8) {
      for (int i = 0; i < args.length; i += 2) {
        if (args[i].equalsIgnoreCase("-in")) {
          inputArgs = true;
          if (!args[i + 1].contains("txt")) {
            return false;
          }
        } else if (args[i].equalsIgnoreCase("-view")) {
          viewArgs = true;
          if (!args[i + 1].contains("text") && !args[i + 1].contains("svg")
              && !args[i + 1].contains("visual") && !args[i + 1].contains("playback")) {
            return false;
          }
        } else if (args[i].equalsIgnoreCase("-out")) {
          if (!args[i + 1].contains("svg") && !args[i + 1].contains("txt")) {
            return false;
          }
        } else if (args[i].equalsIgnoreCase("-speed")) {
          String speed = args[i + 1];
          if (Integer.parseInt(speed) == 0) {
            return false;
          } else if (!speed.matches("^[0-9]+$")) {
            return false;
          }
        }
      }
    }
    return inputArgs && viewArgs;
  }
}