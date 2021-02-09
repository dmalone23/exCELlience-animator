package hwk5.model;

/**
 * Movement class responsible for representing a move operation and its associated methods.
 */
public class Movement extends Operation {

  Position endPos;

  /**
   * Creates an movement object.
   *
   * @param strt the start tick of the operation
   * @param end  the end tick of the operation
   * @param s    the shape being changed
   * @param pos  the desired endPos
   */
  public Movement(int strt, int end, IShapes s, Position pos) {
    super(strt, end, s);
    this.endPos = pos;
  }

  @Override
  public IShapes run(int currentTick) {
    double x = endPos.getX() - this.shape.getPosition().getX();
    double y = endPos.getY() - this.shape.getPosition().getY();
    if (currentTick != endTick) {
      x /= (endTick - currentTick);
      y /= (endTick - currentTick);
    }
    this.shape.moveShape(new Position(this.shape.getPosition().getX() + x,
        this.shape.getPosition().getY() + y));
    return this.shape;
  }

  @Override
  public String write(int s) {
    return "motion " + shape.toString() + " start state: " + (strTick / s) + " "
        + (int) shape
        .getPosition().getX() + " "
        + (int) shape.getPosition().getY() + " " + (int) shape.getWidth() + " " + (int) shape
        .getHeight()
        + " " + shape.getRCol() + " " + shape.getGCol() + " " + shape.getBCol()
        + " end state: "
        + (endTick / s) + " " + (int) endPos.getX() + " "
        + (int) endPos.getY() + " " + (int) shape.getWidth() + " " + (int) shape.getHeight()
        + " " + shape.getRCol() + " " + shape.getGCol() + " " + shape.getBCol();
  }

  @Override
  public String svgFormat(int tps) {
    return "<animate" + "\n" + "xlink:href=\"#" + this.shape.toString() + "\"" + "\n"
        + "attributeName=\"x\"" + "\n" + "from=\"" + shape.getPosition().getX() + "\"" + "\n"
        + "to=\"" + endPos.getX() + "\"" + "\n" + "dur=\"" + (endTick - strTick) / tps + "\"" + "\n"
        + "fill=\"freeze\" />" + "\n" + "<animate" + "\n" + "xlink:href=\"#" + this.shape.toString()
        + "\"" + "\n"
        + "attributeName=\"y\"" + "\n" + "from=\"" + shape.getPosition().getY() + "\"" + "\n"
        + "to=\"" + endPos.getY() + "\"" + "\n" + "dur=\"" + (endTick - strTick) / tps + "\"" + "\n"
        + "fill=\"freeze\"/>";
  }
}