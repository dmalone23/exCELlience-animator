package hwk5.model;

/**
 * ChngWidth class responsible for the change width operation and its associated methods.
 */
public class ChngWidth extends Operation {

  int endWidth;

  /**
   * Creates a ChngColor object.
   *
   * @param strt the start tick of the operation
   * @param end  the end tick of the operation
   * @param s    the shape being changed
   * @param wide the desired width after change.
   */
  public ChngWidth(int strt, int end, IShapes s, int wide) {
    super(strt, end, s);
    this.endWidth = wide;
  }

  @Override
  public IShapes run(int currentTick) {
    double x = endWidth - this.shape.getWidth();
    if (currentTick != endTick) {
      x /= (endTick - currentTick);
    }
    this.shape.changeWidth(this.shape.getWidth() + x);
    return this.shape;
  }


  @Override
  public String write(int s) {
    return "morph " + shape.toString() + " " + "start state: " + (strTick / s) + " "
        + (int) shape.getPosition()
        .getX()
        + " "
        + (int) shape.getPosition().getY() + " " + (int) shape.getWidth() + " " + (int) shape
        .getHeight()
        + " " + shape.getRCol() + " " + shape.getGCol() + " " + shape.getBCol() + " "
        + "end state: " + (endTick / s) + " " + (int) shape.getPosition().getX() + " "
        + (int) shape.getPosition().getY() + " " + endWidth + " " + (int) shape.getHeight()
        + " " + shape.getRCol() + " " + shape.getGCol() + " " + shape.getBCol();
  }

  @Override
  public String svgFormat(int tps) {
    return "<animate" + "\n" + "xlink:href=\"#" + this.shape.toString() + "\"" + "\n"
        + "attributeName=\"width\"" + "\n" + "from=\"" + shape.getWidth() + "\"" + "\n"
        + "to=\"" + endWidth
        + "\"" + "\n" + "dur=\"" + (endTick - strTick) / tps + "\"" + "\n"
        + "fill=\"freeze\"/>";
  }
}