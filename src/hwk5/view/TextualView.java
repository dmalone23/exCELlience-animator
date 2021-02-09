package hwk5.view;

import hwk5.model.Animation;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.naming.OperationNotSupportedException;

/**
 * Represents the text-based representation of an animation.
 */
public class TextualView implements IView {

  private Animation model;
  private final Appendable app;

  /**
   * Constructs a new {@link TextualView} object using the given {@link Animation} model.
   *
   * @param model the desired model to animate
   */
  public TextualView(Animation model) {
    this.model = model;
    this.app = new StringBuilder();

  }

  /**
   * Render the animation for the given duration.
   * @param secs the duration of the animation
   * @return the current state of the textual view
   * @throws IllegalArgumentException if given an invalid number of seconds
   */
  public Appendable render(int secs) throws IllegalArgumentException {
    if (secs <= 0) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i < model.getOps().size(); i++) {
      try {
        app.append("" + model.getOps().get(i).write(secs) + "\n");
      } catch (IOException e) {
        System.out.println(e);
        throw new IllegalStateException();
      }
    }
    return app;
  }

  @Override
  public File render(String fName) throws IllegalStateException, OperationNotSupportedException {
    throw new OperationNotSupportedException();
  }

  @Override
  public void refresh() throws OperationNotSupportedException {
    throw new OperationNotSupportedException();
  }

  @Override
  public void updateModel(Animation model) {
    this.model = model;
  }

  @Override
  public int getTicks() throws OperationNotSupportedException {
    throw new OperationNotSupportedException();
  }

  @Override
  public void setActionListener(ActionListener actLis) throws OperationNotSupportedException {
    throw new OperationNotSupportedException();
  }

}