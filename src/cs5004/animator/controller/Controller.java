package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * This interface represents a controller interface. It contains all the
 * operations that a controller should support.
 *
 */
public interface Controller {

  /**
   * This method runs the animator. It will call different views according to the 
   * user command.
   * 
   * @throws IOException thrown if the exceptions produced by failed or interrupted
   *                     I/O operations
   */
  void run() throws IOException;

  /**
   * Perform different action according to the button click actions.
   * 
   * @param clicks the button click actions
   */
  void actionPerformed(ActionEvent clicks);
}
