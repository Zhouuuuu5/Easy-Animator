package controller;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;


/**
 * This is the JUnit test for the controller.
 *
 */
public class ControllerTest {

  /**
   * Test run method to check if the controller can create the view successfully.
   * 
   * @throws IOException thrown if exceptions produced by failed or interrupted
   *                     I/O operations.
   */
  @Test
  public void testDisplay() throws IOException {
    ControllerImplForTest c1 = new ControllerImplForTest("text", "");
    c1.run();
    assertEquals("successfully create view", c1.view.displayInfo);

    ControllerImplForTest c2 = new ControllerImplForTest("svg", "");
    c2.run();
    assertEquals("successfully create view", c2.view.displayInfo);

    ControllerImplForTest c3 = new ControllerImplForTest("visual", "");
    c3.run();
    assertEquals("successfully create view", c3.view.displayInfo);

  }

  /**
   * Test if the start method works properly.
   * 
   * @throws IOException thrown if exceptions produced by failed or interrupted
   *                     I/O operations.
   */
  @Test
  public void testStart() throws IOException {
    ControllerImplForTest c = new ControllerImplForTest("playback", "start button");
    c.run();
    assertEquals("successfully create view", c.view.displayInfo);
    assertEquals("start button clicked", c.view.clickStart);
  }

  /**
   * Test if the pause method works properly.
   * 
   * @throws IOException thrown if exceptions produced by failed or interrupted
   *                     I/O operations.
   */
  @Test
  public void testPause() throws IOException {
    ControllerImplForTest c = new ControllerImplForTest("playback", "pause button");
    c.run();
    assertEquals("pause button clicked", c.view.clickPause);
  }

  /**
   * Test if the restart method works properly.
   * 
   * @throws IOException thrown if exceptions produced by failed or interrupted
   *                     I/O operations.
   */
  @Test
  public void testRestartButton() throws IOException {
    ControllerImplForTest c = new ControllerImplForTest("playback", "restart button");
    c.run();
    assertEquals("restart button clicked", c.view.clickRestart);
  }

  /**
   * Test if the loop method works properly.
   * 
   * @throws IOException thrown if exceptions produced by failed or interrupted
   *                     I/O operations.
   */
  @Test
  public void testLoopButton() throws IOException {
    ControllerImplForTest c = new ControllerImplForTest("playback", "loop button");
    c.run();
    assertEquals("loop button clicked", c.view.clickLoop);
  }

  /**
   * Test if the increase speed method works properly.
   * 
   * @throws IOException thrown if exceptions produced by failed or interrupted
   *                     I/O operations.
   */
  @Test
  public void testIncreaseSpeedButton() throws IOException {
    ControllerImplForTest c = new ControllerImplForTest("playback", "increase speed button");
    c.run();
    assertEquals("increase speed button clicked", c.view.clickIncreaseSpeed);
  }

  /**
   * Test if the decrease speed method works properly.
   * 
   * @throws IOException thrown if exceptions produced by failed or interrupted
   *                     I/O operations.
   */
  @Test
  public void testDecreaseSpeedButton() throws IOException {
    ControllerImplForTest c = new ControllerImplForTest("playback", "decrease speed button");
    c.run();
    assertEquals("decrease speed button clicked", c.view.clickDecreaseSpeed);
  }
}