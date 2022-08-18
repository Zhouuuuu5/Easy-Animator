package cs5004.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import cs5004.animator.model.Model;
import cs5004.animator.view.PlaybackView;
import cs5004.animator.view.SVGAnimationView;
import cs5004.animator.view.TextualView;
import cs5004.animator.view.View;
import cs5004.animator.view.VisualAnimationView;

/**
 * This class implements the controller interface.
 */
public class ControllerImpl implements Controller, ActionListener {

  private Model model;
  private View view;
  private String viewType;
  private String outputFileName;
  private String speed;

  /**
   * Constructs a new instance of the controller.
   * 
   * @param model          the model to create the controller
   * @param viewType       the type of the view
   * @param outputFileName the name of the output file
   * @param speed          the speed of the animation
   */
  public ControllerImpl(Model model, String viewType, String outputFileName, String speed) {
    this.model = model;
    this.viewType = viewType;
    this.outputFileName = outputFileName;
    this.speed = speed;
  }

  @Override
  public void run() throws IOException {
    switch (this.viewType) {
      case "text":
        this.view = new TextualView(this.model.getAllShapes(), this.model.getOrderedAnimations(),
            this.outputFileName, this.speed);
        this.view.display();
        break;
  
      case "visual":
        this.view = new VisualAnimationView(this.model.getFrameShapes(), this.model.getCanvas(),
            Integer.parseInt(this.speed));
        this.view.display();
        break;
  
      case "playback":
        this.view = new PlaybackView(this.model.getFrameShapes(), this.model.getCanvas(),
            Integer.parseInt(this.speed));
        if (this.view instanceof PlaybackView) {
          ((PlaybackView) this.view).setEventListeners(this);
        }
        break;
  
      case "svg":
        this.view = new SVGAnimationView(this.model.getAllShapes(), this.model.getAllAnimations(),
            this.outputFileName, this.speed);
        this.view.display();
        break;
  
      default:
        throw new IOException("Error on display");
    }
  }

  /**
   * Generate events according to the given action by clicking on buttons.
   * 
   * @param clicks the click actions made by the user
   */
  @Override
  public void actionPerformed(ActionEvent clicks) {
    PlaybackView playbackView = ((PlaybackView) this.view);
    switch (clicks.getActionCommand()) {
    
      case "start button":
        playbackView.start();
        break;
  
      case "pause button":
        playbackView.pause();
        break;
  
      case "restart button":
        playbackView.restart();
        break;
  
      case "loop button":
        playbackView.loop();
        break;
  
      case "increase speed button":
        playbackView.increaseSpeed();
        break;
  
      case "decrease speed button":
        playbackView.decreaseSpeed();
        break;
  
      default:
        // no action is intended
    }
  }
}
