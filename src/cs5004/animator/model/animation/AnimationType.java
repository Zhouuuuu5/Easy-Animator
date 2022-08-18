package cs5004.animator.model.animation;

/**
 * This enumerator contains all the animation types that an easy animator should
 * support, including move, change color and scale.
 *
 */
public enum AnimationType {
  MOVE("moves"), CHANGECOLOR("changes color"), SCALE("scales");

  private String type;

  /**
   * A constructor that creates a new instance of an animation type.
   * 
   * @param type the string of the animation type
   */
  private AnimationType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return this.type;
  }
}
