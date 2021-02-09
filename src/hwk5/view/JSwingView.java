package hwk5.view;

import hwk5.model.Animation;
import hwk5.model.ShapesModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.io.File;
import javax.naming.OperationNotSupportedException;
import javax.swing.JFrame;

/**
 * JSwing view class for creating a view that uses java swing and awt classes to create an
 * animation.
 */
public class JSwingView extends JFrame implements IView {

  private Animation model = new ShapesModel();
  private Graphics2D g;
  private AnimationPanel shapePane = new AnimationPanel();

  /**
   * JSwing View constructor that create the jframe and jpanel used in the animation.
   *
   * @param model the model used in the animation
   * @param x     the width value for the animation screen
   * @param y     the height value for the animation screen
   */
  public JSwingView(Animation model, int x, int y) {
    this.model = model;

    this.setSize(x, y);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    shapePane = new AnimationPanel();
    shapePane.setPreferredSize(new Dimension(x, y));
    shapePane.setVisible(true);
    this.add(shapePane, BorderLayout.CENTER);
    this.setVisible(true);

    this.pack();
  }

  @Override
  public Appendable render(int secs)
      throws IllegalArgumentException, OperationNotSupportedException {
    throw new OperationNotSupportedException();
  }

  @Override
  public File render(String fName) throws IllegalStateException, OperationNotSupportedException {
    throw new OperationNotSupportedException();
  }

  @Override
  public void refresh() {
    shapePane.updateShapes(model.getCurrentState());
    this.repaint();
  }

  @Override
  public void updateModel(Animation m) {
    this.model = m;
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
