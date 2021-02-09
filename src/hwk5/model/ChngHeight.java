package hwk5.model;

/**
 * ChngHeight class responsible for the change height operation and its associated methods.
 */
public class ChngHeight extends Operation {

  int endHeight;

  /**
   * Creates a ChngColor object.
   *
   * @param strt the start tick of the operation
   * @param end  the end tick of the operation
   * @param s    the shape being changed
   * @param heg  the desired height after change.
   */
  public ChngHeight(int strt, int end, IShapes s, int heg) {
    super(strt, end, s);
    this.endHeight = heg;
  }


  @Override
  public IShapes run(int currentTick) {
    double x = endHeight - this.shape.getHeight();
    if (currentTick != endTick) {
      x /= (endTick - currentTick);
    }
    this.shape.changeHeight(this.shape.getHeight() + x);
    return this.shape;
  }


  @Override
  public String write(int s) {
    return "morph " + shape.toString() + " start state: " + (strTick / s) + " "
        + (int) shape
        .getPosition().getX() + " "
        + (int) shape.getPosition().getY() + " " + (int) shape.getWidth() + " " + (int) shape
        .getHeight()
        + " " + shape.getRCol() + " " + shape.getGCol() + " " + shape.getBCol()
        + " end state: "
        + (endTick / s) + " " + (int) shape.getPosition().getX() + " "
        + (int) shape.getPosition().getY() + " " + (int) shape.getWidth() + " "
        + endHeight
        + " " + shape.getRCol() + " " + shape.getGCol() + " " + shape.getBCol();
  }

  @Override
  public String svgFormat(int tps) {
    return "<animate" + "\n" + "xlink:href=\"#" + this.shape.toString() + "\"" + "\n"
        + "attributeName=\"height\"" + "\n" + "from=\"" + shape.getHeight() + "\"" + "\n"
        + "to=\"" + endHeight
        + "\"" + "\n" + "dur=\"" + (endTick - strTick) / tps + "\"" + "\n"
        + "fill=\"freeze\"/>";
  }
}