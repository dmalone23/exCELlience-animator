package hwk5.view;

import hwk5.model.Animation;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Represents the part of the view that that interactively control playback.
 */
public class InteractiveView extends JFrame implements ActionListener, IView {

  private boolean looping;
  private Animation model;
  private AnimationPanel shapePane;
  private double tps;
  private List<JButton> buttonList = new ArrayList<JButton>();
  private JButton fastFor;
  private JButton restart;
  private JButton loop;
  private JButton slowFor;
  private JButton pause;
  private JButton play;

  /**
   * JSwing View constructor that create the jframe and jpanel used in the animation.
   *
   * @param model the model used in the animation
   * @param x     the width value for the animation screen
   * @param y     the height value for the animation screen
   */
  public InteractiveView(Animation model, int x, int y, int tPS) {
    this.model = model;
    this.setVisible(true);
    this.setSize(x, y);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    shapePane = new AnimationPanel();
    shapePane.setPreferredSize(new Dimension(x, y));
    this.add(shapePane, BorderLayout.CENTER);
    tps = tPS;
    JPanel buttonPane = new JPanel();
    fastFor = new JButton("SpeedUp");
    fastFor.setActionCommand("Fast Forward");

    buttonPane.add(fastFor);
    restart = new JButton("Restart");
    restart.setActionCommand("Restart");

    buttonPane.add(restart);
    slowFor = new JButton("SlowDown");
    slowFor.setActionCommand("Slow Forward");

    buttonPane.add(slowFor);
    loop = new JButton("Loop");
    loop.setActionCommand("Loop");

    buttonPane.add(loop);
    pause = new JButton("Pause");
    pause.setActionCommand("Pause");

    buttonPane.add(pause);
    play = new JButton("Play");
    play.setActionCommand("Play");

    buttonPane.add(play);
    this.add(buttonPane, BorderLayout.SOUTH);

    this.pack();
  }

  /**
   * Method to restart the animation as it is in progress.
   */
  private void restart() {
    int curTick = 0;
  }

  /**
   * Method to start the animation.
   */
  private void start() {
    tps = 1000 / tps;
  }

  /**
   * Method to enable/disable looping program defaults to no looping.
   */
  private void setLooping() {
    looping = !looping;
  }

  /**
   * Method to determine if the animation is looping.
   */
  private boolean isLooping() {
    return looping;
  }

  /**
   * Method to speed up the animation by increasing the ticks per second.
   */
  private void speedUp() {
    tps = tps * 1.25;
  }

  /**
   * Method to speed up the animation by decreasing the ticks per second.
   */
  private void slowDown() {
    tps = tps * .75;
  }

  /**
   * Method to catch action inputs from the controller.
   */
  public void actionPerformed(ActionEvent e) {
    try {
      this.refresh();
    } catch (OperationNotSupportedException excep) {
      System.out.println("This should not happen");
    }
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
  public void refresh() throws OperationNotSupportedException {
    shapePane.updateShapes(model.getCurrentState());
    this.repaint();
  }

  @Override
  public void updateModel(Animation model) {
    this.model = model;
  }

  @Override
  public int getTicks() {
    if (tps != 0) {
      return (int) Math.ceil(1000 / tps);
    } else {
      return 0;
    }
  }

  @Override
  public void setActionListener(ActionListener actLis) {
    fastFor.addActionListener(actLis);
    restart.addActionListener(actLis);
    loop.addActionListener(actLis);
    slowFor.addActionListener(actLis);
    pause.addActionListener(actLis);
    play.addActionListener(actLis);
  }

}
