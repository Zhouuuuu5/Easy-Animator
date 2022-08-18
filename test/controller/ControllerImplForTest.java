package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import cs5004.animator.controller.Controller;

/**
 * This class implements the controller interface for test.
 */
public class ControllerImplForTest implements ActionListener, Controller {
  protected ViewForTest view;
  protected String viewType;
  protected JButton buttonClicked;

  /**
   * Constructs a new instance of the test controller.
   * 
   * @param viewType the view type as a string
   * @param click    the click command as a string
   */
  public ControllerImplForTest(String viewType, String click) {
    this.view = new ViewForTest();
    this.viewType = viewType;
    switch (click) {
      case "start button":
        this.buttonClicked = this.view.startButton;
        break;
      case "pause button":
        this.buttonClicked = this.view.pauseButton;
        break;
      case "restart button":
        this.buttonClicked = this.view.restartButton;
        break;
      case "loop button":
        this.buttonClicked = this.view.loopButton;
        break;
      case "increase speed button":
        this.buttonClicked = this.view.increaseSpeedButton;
        break;
      case "decrease speed button":
        this.buttonClicked = this.view.decreaseSpeedButton;
        break;
      default:
        // no action is intended
    }
  }
  
  /**
   * This method runs the animator. It will call different views according to 
   * the user command.
   * 
   * @throws IOException thrown if the exceptions produced by failed or interrupted
   *                     I/O operations
   */
  public void run() throws IOException {
    switch (this.viewType) {
      case "text":
        this.view.display();
        break;
      case "visual":
        this.view.display();
        break;
      case "playback":
        this.view.setListeners(this);
        this.buttonClicked.doClick();
        break;
      case "svg":
        this.view.display();
        break;
      default:
        throw new IOException("Display error.");
    }
  }

  @Override
  public void actionPerformed(ActionEvent clicks) {

    switch (clicks.getActionCommand()) {

      case "start button":
        this.view.startPlay();
        break;
  
      case "pause button":
        this.view.pausePlay();
        break;
  
      case "restart button":
        this.view.restartPlay();
        break;
  
      case "loop button":
        this.view.loopPlay();
        break;
  
      case "increase speed button":
        this.view.increaseSpeed();
        break;
  
      case "decrease speed button":
        this.view.decreaseSpeed();
        break;
  
      default:
        // no action is intended
    }
  }
}
