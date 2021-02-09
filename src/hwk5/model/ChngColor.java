package hwk5.model;

/**
 * ChngColor class responsible for the change color operation and its associated methods.
 */
public class ChngColor extends Operation {

  int rCol;
  int gCol;
  int bCol;

  /**
   * Creates a ChngColor object.
   *
   * @param strt the start tick of the operation
   * @param end  the end tick of the operation
   * @param s    the shape being changed
   * @param r    the desired red color of the rectangle
   * @param g    the desired green color of the rectangle
   * @param b    the desired blue color of the rectangle
   */
  public ChngColor(int strt, int end, IShapes s, int r, int g, int b) {
    super(strt, end, s);
    this.rCol = r;
    this.gCol = g;
    this.bCol = b;
  }

  @Override
  public IShapes run(int currentTick) {
    int x = rCol - this.shape.getRCol();
    int y = gCol - this.shape.getGCol();
    int z = bCol - this.shape.getBCol();
    if (currentTick != this.endTick) {
      x /= (endTick - currentTick);
      y /= (endTick - currentTick);
      z /= (endTick - currentTick);
    }
    this.shape.changeColor(this.shape.getRCol() + x,
        this.shape.getGCol() + y, this.shape.getBCol() + z);
    return this.shape;
  }


  @Override
  public String write(int s) {
    return "morph " + shape.toString() + " start state: " + (strTick / s) + " " + (int) shape
        .getPosition().getX() + " "
        + (int) shape.getPosition().getY() + " " + (int) shape.getWidth() + " " + (int) shape
        .getHeight()
        + " " + shape.getRCol() + " " + shape.getGCol() + " " + shape.getBCol() + " end state: "
        + (endTick / s) + " " + (int) shape.getPosition().getX() + " "
        + (int) shape.getPosition().getY() + " " + (int) shape.getWidth() + " " + (int) shape
        .getHeight()
        + " " + rCol + " " + gCol + " " + bCol;
  }

  @Override
  public String svgFormat(int tps) {
    return "<animate" + "\n" + "xlink:href=\"#" + this.shape.toString() + "\"" + "\n"
        + "attributeName=\"fill\"" + "\n" + "from=\"" + "rgb(" + shape.getRCol() + ","
        + shape.getGCol() + "," + shape.getBCol() + ")" + "\"" + "\n"
        + "to=\"" + "rgb(" + rCol + ","
        + gCol + "," + bCol + ")"
        + "\"" + "\n" + "dur=\"" + (endTick - strTick) / tps + "\"" + "\n"
        + "fill=\"freeze\"/>";
  }
}