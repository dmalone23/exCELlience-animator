package hwk5.model;

/**
 * State class for representing the state of a shapes data at a given tick.
 */
public class State {
  private int rColor;
  private int bColor;
  private int gColor;
  private Position position;
  private int width;
  private int height;
  private int tick;

  /**
   * Constructs a new ShapesModel with the given parameters.
   *
   * @param t  the tick of the current state
   * @param r  the red color value
   * @param g the green color value
   * @param b the blue color value
   * @param pos  the position data
   * @param w the width value
   * @param h the height value
   */
  public State(int t, int r, int g, int b, Position pos, int w, int h) {
    this.tick = t;
    this.rColor = r;
    this.bColor = b;
    this.gColor = g;
    this.position = pos;
    this.width = w;
    this.height = h;
  }

  /**
   * Retrieves the height of the state.
   *
   * @return the height value of the state
   */
  public int getHeight() {
    return height;
  }

  /**
   * Retrieves the blue color value of the state.
   *
   * @return the blue color value of the state
   */
  public int getBCol() {
    return bColor;
  }

  /**
   * Retrieves the green color of the state.
   *
   * @return the green color value of the state
   */
  public int getGCol() {
    return gColor;
  }

  /**
   * Retrieves the red color of the state.
   *
   * @return the red color value of the state
   */
  public int getRCol() {
    return rColor;
  }

  /**
   * Retrieves the width of the state.
   *
   * @return the width value of the state
   */
  public int getWidth() {
    return width;
  }

  /**
   * Retrieves the position of the state.
   *
   * @return the position object of the state
   */
  public Position getPosition() {
    return position;
  }

  /**
   * Retrieves the tick value of the state.
   *
   * @return the tick value of the state
   */
  public int getTick() {
    return tick;
  }

  /**
   * Creates a textual representation of the state for use in textual view.
   *
   * @param tps the ticks per second to be used in creating the representation.
   * @return the textual representation of the state
   */
  public String write(int tps) {
    String ret = "";
    ret += (tick / tps) + position.getX() + " "
        + position.getY() + " " + width + " " + height
        + " " + rColor + " " + gColor + " " + bColor;
    return ret;
  }
}
