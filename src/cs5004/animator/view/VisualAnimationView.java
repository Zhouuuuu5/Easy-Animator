package cs5004.animator.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.util.Canvas;

/**
 * This class represents the Visual Animation view. It implements the view interface.
 * 
 */
public class VisualAnimationView implements View {

  private JFrame frame;
  private VisualAnimationPanel<Shape> panel;
  private Timer timer;

  /**
   * Constructs a new instance of visual animation view.
   * 
   * @param frameShapes the list of frames
   * @param canvas      the canvas of the animation view
   * @param tempo       the speed of the animation
   */
  public VisualAnimationView(List<List<Shape>> frameShapes, Canvas canvas, int tempo) {
    // create frame
    this.frame = new JFrame("Easy Animator Visual View");
    this.frame.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create panel
    this.panel = new VisualAnimationPanel<>(frameShapes);
    this.panel.setPreferredSize(new Dimension(canvas.getWidth(), canvas.getHeight()));

    // create scroll pane
    JScrollPane scrollPane = new JScrollPane(this.panel);
    this.frame.add(scrollPane);

    // refresh the window
    this.timer = new Timer(1000 / tempo, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.repaint();
        if (panel.total == frameShapes.size()) {
          timer.stop();
        }
      }
    });

    this.frame.pack();
    this.frame.setVisible(true);
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
