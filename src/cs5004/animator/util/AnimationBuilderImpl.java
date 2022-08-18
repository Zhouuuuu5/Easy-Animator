package cs5004.animator.util;

import cs5004.animator.model.Model;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.animation.ChangeColor;
import cs5004.animator.model.animation.Move;
import cs5004.animator.model.animation.Scale;
import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Rectangle;
import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.util.Canvas;
import cs5004.animator.model.util.Color;
import cs5004.animator.model.util.Position;
import cs5004.animator.model.util.Size;

/**
 * This class implements the AnimationBuilder interface.
 *
 */
public final class AnimationBuilderImpl implements AnimationBuilder<Model> {
  private Model model;

  /**
   * Constructs a new instance of the animation builder.
   */
  public AnimationBuilderImpl() {
    this.model = new ModelImpl();
  }

  @Override
  public Model build() {
    return this.model;
  }

  @Override
  public AnimationBuilder<Model> setBounds(int x, int y, int width, int height) {
    this.model.setCanvas(new Canvas(x, y, width, height));
    return this;
  }

  @Override
  public AnimationBuilder<Model> declareShape(String name, String type) {
    if (type.equalsIgnoreCase("rectangle")) {
      this.model.addShape(
          new Rectangle(name, new Position(0, 0), new Size(0, 0), new Color(0, 0, 0), 0, 0));
    } else if (type.equalsIgnoreCase("ellipse")) {
      this.model
          .addShape(new Oval(name, new Position(0, 0), new Size(0, 0), new Color(0, 0, 0), 0, 0));
    }
    return this;
  }

  @Override
  public AnimationBuilder<Model> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
      int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
    Shape shape = this.model.getAllShapes().get(name);
    if (shape == null) {
      throw new IllegalArgumentException("The shape doesn't exist.");
    }

    if (shape.getAppearTime() == 0 && shape.getDisappearTime() == 0) {
      shape.setPosition(new Position(x1, y1));
      shape.setSize(new Size(w1, h1));
      shape.setColor(new Color(r1, g1, b1));
      shape.setAppearTime(t1);
      shape.setDisappearTime(t2);
    }

    shape.setDisappearTime(t2);
    if (x1 != x2 || y1 != y2) {
      this.model.addAnimation(
          new Move(name, shape.getType(), t1, t2, new Position(x1, y1), new Position(x2, y2)));
    }
    if (r1 != r2 || g1 != g2 || b1 != b2) {
      this.model.addAnimation(new ChangeColor(name, shape.getType(), t1, t2, new Color(r1, g1, b1),
          new Color(r2, g2, b2)));
    }
    if (w1 != w2 || h1 != h2) {
      this.model.addAnimation(
          new Scale(name, shape.getType(), t1, t2, new Size(w1, h1), new Size(w2, h2)));
    }
    return this;
  }
}
