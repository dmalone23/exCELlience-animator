package hwk5.model;

/**
 * Position class representing a 2D x,y position.
 */
public class Position {

  private double xPos;
  private double yPos;


  /**
   * Constructor for the position class.
   *
   * @param xPos the x coordinate
   * @param yPos the y coordinate
   */
  public Position(double xPos, double yPos) {
    this.xPos = xPos;
    this.yPos = yPos;
  }

  /**
   * Method for retrieving the x coordinate of a position.
   */
  public double getX() {
    double l = xPos;
    return l;
  }

  /**
   * Method for retrieving the y coordinate of a position.
   */
  public double getY() {
    double l = yPos;
    return l;
  }

}
