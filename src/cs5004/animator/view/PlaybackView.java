package cs5004.animator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.util.Canvas;

/**
 * This PlaybackView class implements View interface. 
 * It also extends JPanel to create an interface.
 *
 */
@SuppressWarnings("serial")
public class PlaybackView extends JPanel implements View {

  private int tempo;
  private int delay;
  private JFrame frame;
  private VisualAnimationPanel<Shape> panel;
  private Timer timer;
  private JButton startButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JButton loopButton;
  private JButton increaseSpeedButton;
  private JButton decreaseSpeedButton;
  private boolean playStatus;
  private boolean loopStatus;
  private JLabel loopMessage;
  private JLabel speedMessage;

  /**
   * Constructs a new instance of the playback view.
   * 
   * @param frameShapes the list of the frames containing shapes
   * @param canvas      the canvas of the view
   * @param tempo       the speed of the animation
   */
  public PlaybackView(List<List<Shape>> frameShapes, Canvas canvas, int tempo) {
    this.tempo = tempo;
    this.delay = 1000 / this.tempo;
    this.playStatus = true;
    this.loopStatus = true;

    // set frame
    this.frame = new JFrame("Easy Animator Playback View");
    this.frame.setPreferredSize(new Dimension(800, 800));
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.frame.setLayout(new FlowLayout());

    // set panel
    this.panel = new VisualAnimationPanel<>(frameShapes);
    this.panel.setPreferredSize(new Dimension(800, 700));
    this.frame.add(this.panel);
    this.timer = new Timer(this.delay, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.repaint();
        if (panel.total == frameShapes.size()) {
          timer.stop();
          if (loopStatus) {
            panel.total = 0;
            timer.restart();
          } else {
            // if now not in the loop status, reset all the buttons
            playStatus = false;
            pauseButton.setEnabled(false);
            loopButton.setEnabled(false);
            increaseSpeedButton.setEnabled(false);
            decreaseSpeedButton.setEnabled(false);
          }
        }
      }
    });

    // set label message
    this.loopMessage = new JLabel("Enabled Loop");
    this.loopMessage.setPreferredSize(new Dimension(380, 20));
    this.frame.add(loopMessage);
    this.speedMessage = new JLabel("Speed: " + this.tempo + " tick(s) per second");
    this.speedMessage.setPreferredSize(new Dimension(380, 20));
    this.frame.add(speedMessage);

    // set buttons
    this.startButton = new JButton("Start");
    this.startButton.setActionCommand("start button");
    this.frame.add(this.startButton);

    this.pauseButton = new JButton("Pause/resume");
    this.pauseButton.setActionCommand("pause button");
    this.frame.add(this.pauseButton);

    this.restartButton = new JButton("Restart");
    this.restartButton.setActionCommand("restart button");
    this.frame.add(this.restartButton);

    this.loopButton = new JButton("Enable/disable loop");
    this.loopButton.setActionCommand("loop button");
    this.frame.add(this.loopButton);

    this.increaseSpeedButton = new JButton("Increase speed");
    this.increaseSpeedButton.setActionCommand("increase speed button");
    this.frame.add(this.increaseSpeedButton);

    this.decreaseSpeedButton = new JButton("Decrease speed");
    this.decreaseSpeedButton.setActionCommand("decrease speed button");
    this.frame.add(this.decreaseSpeedButton);

    this.frame.pack();
    this.frame.setVisible(true);
  }

  /**
   * Set event listener to buttons.
   * 
   * @param clicks the mouse clicks
   */
  public void setEventListeners(ActionListener clicks) {
    this.startButton.addActionListener(clicks);
    this.pauseButton.addActionListener(clicks);
    this.restartButton.addActionListener(clicks);
    this.loopButton.addActionListener(clicks);
    this.increaseSpeedButton.addActionListener(clicks);
    this.decreaseSpeedButton.addActionListener(clicks);
  }

  /**
   * Start to play the animation by clicking the start button.
   */
  public void start() {
    try {
      this.display();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    this.startButton.setEnabled(false);
  }

  /**
   * Pause or resume the animation.
   */
  public void pause() {
    if (this.playStatus) {
      this.timer.stop();
      this.playStatus = false;
    } else {
      this.timer.start();
      this.playStatus = true;
    }
  }

  /**
   * Restart to play the animation.
   */
  public void restart() {
    this.panel.total = 0;
    this.timer.restart();
    this.pauseButton.setEnabled(true);
    this.loopButton.setEnabled(true);
    this.increaseSpeedButton.setEnabled(true);
    this.decreaseSpeedButton.setEnabled(true);
  }

  /**
   * Control loop animation.
   */
  public void loop() {
    if (this.loopStatus) {
      this.loopStatus = false;
      this.loopMessage.setText("Disabled Loop");
    } else {
      this.loopStatus = true;
      this.loopMessage.setText("Enabled Loop");
    }
  }

  /**
   * Increase the speed of the animation. Each click doubles the speed per second.
   */
  public void increaseSpeed() {
    this.tempo = this.tempo * 2;
    this.delay = 1000 / this.tempo;
    this.timer.setDelay(this.delay);
    this.speedMessage.setText("Speed: " + this.tempo + " tick(s) per second");
  }

  /**
   * Decrease the speed of the animation. Each click halves the speed per second.
   */
  public void decreaseSpeed() {
    this.tempo = this.tempo / 2;
    this.delay = 1000 / this.tempo;
    this.timer.setDelay(this.delay);
    this.speedMessage.setText("Speed: " + this.tempo + " tick(s) per second");
  }

  @Override
  public void display() throws IOException {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        timer.start();
      }
    });
  }
}
