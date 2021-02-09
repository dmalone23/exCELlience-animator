package hwk5.model;

import hwk5.animator.provider.model.ViewShape;
import java.awt.Graphics2D;

/**
 * Interface for IShapes and the methods all IShapes must implement.
 */
public interface IShapes extends ViewShape {

  /**
   * Gets the position of the shape.
   */
  Position getPosition();

  /**
   * Moves the desired shape according to this interface's implementation. Throws
   * IllegalArgumentException when position is null.
   *
   * @param newPosition the desired position to move the shape to
   */
  void moveShape(Position newPosition) throws IllegalArgumentException;

  /**
   * Changes the color of the desired {@link IShapes} in this {@code Animation}. Throws an
   * IllegalArgumentException if any of the params are negative or above 256.
   *
   * @param r the desired red color to which the desired {@link IShapes} will change
   * @param g the desired green color to which the desired {@link IShapes} will change
   * @param b the desired blue color to which the desired {@link IShapes} will change
   */
  void changeColor(int r, int g, int b) throws IllegalArgumentException;

  /**
   * Changes the height of this {@code Animation}. Throws an IllegalArgumentException if the double
   * is negative.
   *
   * @param w the desired new width of the shape
   */
  void changeWidth(double w) throws IllegalArgumentException;

  /**
   * Changes the height of this {@code Animation}. Throws an IllegalArgumentException when h is
   * negative.
   *
   * @param h the desired new height of the shape
   */
  void changeHeight(double h) throws IllegalArgumentException;

  /**
   * Retrieves the width of the shape used to determine on tick increments.
   */
  double getWidth();

  /**
   * Retrieves the height of the shape used to determine on tick increments.
   */
  double getHeight();

  /**
   * Retrieves the red color val of the shape used to determine on tick increments.
   */
  int getRCol();

  /**
   * Retrieves the blue color val of the shape used to determine on tick increments.
   */
  int getGCol();

  /**
   * Retrieves the green color val of the shape used to determine on tick increments.
   */
  int getBCol();

  /**
   * Overrides equals method.
   *
   * @param other the object being compared for equivalence
   */
  boolean equals(Object other);

  /**
   * draw method for visualizing the shape on the given graphic.
   *
   * @param g the graphic the shape should be placed on.
   */
  void draw(Graphics2D g);

  /**
   * Format the given shape into a single SVG tag.
   *
   * @return a string representing the SVG code for the given shape
   */
  String svgFormat();

  /**
   * method to set defualt values for a shape to be restored to on reset.
   *
   * @param r        the red color val.
   * @param g        the green color val.
   * @param b        the blue color val.
   * @param position the postion value.
   * @param width    the x axis width of the shape.
   * @param height   the y axis length of the shape.
   */
  void setDefs(int r, int g, int b, Position position, double width, double height);

  /**
   * method to reset the shape to its initial values.
   */
  void reset();

  /**
   * method add a new state to a shape.
   */
  void addState(State s);
}
