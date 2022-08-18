package controller;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import cs5004.animator.view.View;

/**
 * This class implements the view for the controller test.
 *
 */
public class ViewForTest implements View {
  protected JButton startButton;
  protected JButton pauseButton;
  protected JButton restartButton;
  protected JButton loopButton;
  protected JButton increaseSpeedButton;
  protected JButton decreaseSpeedButton;
  protected String clickStart;
  protected String clickPause;
  protected String clickRestart;
  protected String clickLoop;
  protected String clickIncreaseSpeed;
  protected String clickDecreaseSpeed;
  protected String displayInfo;

  /**
   * Construct a test view with no argument.
   */
  public ViewForTest() {
    // create buttons
    this.startButton = new JButton("Start");
    this.startButton.setActionCommand("start button");
    this.pauseButton = new JButton("Pause/resume");
    this.pauseButton.setActionCommand("pause button");
    this.restartButton = new JButton("Restart");
    this.restartButton.setActionCommand("restart button");
    this.loopButton = new JButton("Enable/disable loop");
    this.loopButton.setActionCommand("loop button");
    this.increaseSpeedButton = new JButton("Increase speed");
    this.increaseSpeedButton.setActionCommand("increase speed button");
    this.decreaseSpeedButton = new JButton("Decrease speed");
    this.decreaseSpeedButton.setActionCommand("decrease speed button");
  }

  /**
   * Set event listener to buttons.
   * 
   * @param clicks the mouse clicks
   */
  public void setListeners(ActionListener clicks) {
    this.startButton.addActionListener(clicks);
    this.pauseButton.addActionListener(clicks);
    this.restartButton.addActionListener(clicks);
    this.loopButton.addActionListener(clicks);
    this.increaseSpeedButton.addActionListener(clicks);
    this.decreaseSpeedButton.addActionListener(clicks);
  }

  @Override
  public void display() {
    displayInfo = "successfully create view";
  }

  /**
   * Start to play the animation.
   * 
   * @throws IOException if exceptions produced by failed or interrupted I/O
   *                     operations occurred
   */
  public void startPlay() {
    this.display();
    clickStart = "start button clicked";
  }

  /**
   * Pause or resume to play animation.
   */
  public void pausePlay() {
    clickPause = "pause button clicked";
  }

  /**
   * Restart to play animation.
   */
  public void restartPlay() {
    clickRestart = "restart button clicked";
  }

  /**
   * Enable or disable loop play.
   */
  public void loopPlay() {
    clickLoop = "loop button clicked";
  }

  /**
   * Increase the playing speed.
   */
  public void increaseSpeed() {
    clickIncreaseSpeed = "increase speed button clicked";
  }

  /**
   * Decrease the playing speed.
   */
  public void decreaseSpeed() {
    clickDecreaseSpeed = "decrease speed button clicked";
  }
}