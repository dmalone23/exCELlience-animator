package hwk5.model;

/**
 * Represents an operation to control animation state.
 */
public abstract class Operation {

  protected double strTick;
  protected double endTick;
  protected IShapes shape;

  /**
   * Creates an operation object.
   *
   * @param strt the start tick of the operation
   * @param end  the end tick of the operation
   * @param s    the shape being changed
   */
  public Operation(int strt, int end, IShapes s) {
    this.strTick = strt;
    this.endTick = end;
    this.shape = s;
  }

  /**
   * Determines if an operation should run at the given tick.
   *
   * @param tick the current tick
   * @return boolean true if it can run false otherwise
   */
  public boolean canRun(int tick) {
    return tick <= endTick && tick >= strTick;
  }

  /**
   * Runs the operation for the given tick.
   *
   * @param currentTick the current tick
   * @return returns the shape after modification
   */
  public abstract IShapes run(int currentTick);


  /**
   * Returns the {@link IShapes} associated with this {@code Operation}.
   *
   * @return returns the shape in this Operation object
   */
  public IShapes getShape() {
    IShapes s = shape;
    return s;
  }

  /**
   * Returns the {@link IShapes} associated with this {@code Operation}.
   *
   * @param shape the shape the operation is being set to act on.
   */
  public void setShape(IShapes shape) {
    this.shape = shape;
  }

  /**
   * Returns the string describing the operation ie the starting state of and the ending state of
   * said shape.
   *
   * @return string desribing the motion
   */
  public String write(int s) {
    return "";
  }

  /**
   * Returns the string that is the name of the shape.
   *
   * @return string giving the name of the shape
   */
  public String getName() {
    return this.shape.toString();
  }

  /**
   * Returns the string that represents the operation in svg form.
   *
   * @param tps the number of ticks per second.
   * @return string representing the operation in svg form.
   */
  public abstract String svgFormat(int tps);

  /**
   * Returns the endtick of the operation.
   *
   * @return the endtick of the operation
   */
  public int getEndTick() {
    return (int) Math.ceil(endTick);
  }

}