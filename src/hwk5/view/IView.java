package hwk5.view;

import hwk5.model.Animation;
import java.awt.event.ActionListener;
import java.io.File;
import javax.naming.OperationNotSupportedException;

/**
 * The interface responsible for grouping all of the views into a single type.
 */
public interface IView {

  /**
   * Outputs text representing each step of the animation, including start and end positions, width
   * and height, and colors of shapes.
   *
   * @param secs the duration of the animation
   * @return the textual representation of the animation
   * @throws IllegalArgumentException if the given duration is less than/equal to zero
   */
  Appendable render(int secs) throws IllegalArgumentException, OperationNotSupportedException;

  /**
   * Method to render the model into an svg file by formatting the model in order to be animated.
   *
   * @return returns a svg file or throws an illegal state exception when the file cant be written
   *     to
   */
  File render(String fName) throws IllegalStateException, OperationNotSupportedException;

  /**
   * Refresh method for updating the animation for controller to use on tick to animate the model.
   */
  void refresh() throws OperationNotSupportedException;


  /**
   * Method to update the model of the given view.
   */
  void updateModel(Animation model);

  int getTicks() throws OperationNotSupportedException;

  void setActionListener(ActionListener actLis) throws OperationNotSupportedException;
}