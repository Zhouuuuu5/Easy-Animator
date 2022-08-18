package cs5004.animator.view;

import java.io.IOException;

/**
 * This interface represents the View. It contains the method to display the
 * user interface.
 */
public interface View {

  /**
   * Display the user interface.
   * 
   * @throws IOException thrown if exceptions produced by failed or interrupted
   *                     I/O operations
   */
  void display() throws IOException;
}
