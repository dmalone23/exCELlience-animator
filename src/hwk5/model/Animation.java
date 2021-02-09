package hwk5.model;

import hwk5.animator.provider.model.ViewModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains a set of functions to manipulate and animate shapes.
 */
public interface Animation extends ViewModel {

  /**
   * Does the operations of the animation for the given tick from controller. Throws an
   * IllegalArgumentException if the int is negative or some the operation at the time is invalid.
   *
   * @param tick the current tick from controller
   */
  void doTick(int tick) throws IllegalArgumentException;


  /**
   * Adds a shape to the animation. Throws an IllegalArgumentException if the ints are negative or
   * the others are null or if the name has already been used.
   *
   * @param name the name of the new shape must be unique
   * @param type the type of shape desired
   * @param w    the desired width
   * @param h    the desired height
   * @param r    the desired red color value
   * @param g    the desired green color value
   * @param b    the desired blue color value
   * @param pos  the desired position for the new shape
   */
  void addShape(String name, ShapeType type, int w, int h, int r, int g, int b, Position pos)
      throws IllegalArgumentException;

  /**
   * Adds a shape to the animation. Throws an IllegalArgumentException if the ints are negative or
   * the others are null or if the name has already been used.
   *
   * @return an {@link ArrayList} containing each {@link IShapes} in this {@code Animation}
   */
  List<IShapes> getCurrentState();

  /**
   * Adds an operation to the animation from controller. Throws an IllegalArgumentException if the
   * op is null.
   *
   * @param op the current tick from controller
   */
  void addOp(Operation op) throws IllegalArgumentException;

  /**
   * Get the list of operation objects in the animation, i.e. each {@link Animation}.
   *
   * @return the arraylist of the operation objects present in the model
   */
  List<Operation> getOps();

  /**
   * Removes the operation from the model using the given index throws an exception when the index
   * is invalid.
   *
   * @param ind the index of the operation to be removed from the list of operations
   */
  void removeOp(int ind) throws IllegalArgumentException;

  /**
   * Removes the shape from the model using the given index throws an exception when the index is
   * invalid.
   *
   * @param ind the index of the shape to be removed from the animation
   */
  void removeShape(int ind) throws IllegalArgumentException;

  /**
   * Updates the operations with the newly updated shape to ensure unity.
   *
   * @param s the updated shape for operations acting on said shape
   */
  void updateOps(IShapes s);

  /**
   * Method to reset the shapes in the model to their default values used when resetting/restarting
   * the animation.
   */
  void reset();

  /**
   * Used to retrieve the final/last tick value of the model in which an operation is done.
   *
   * @return the final tick of any operation (the last tick an operation is acting in)
   */
  int getFinalTick();
}