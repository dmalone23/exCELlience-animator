package hwk5.view;

import hwk5.model.IShapes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Animation panel in which the shapes are drawn in order to crete the graphic of the jframe.
 */
public class AnimationPanel extends JPanel {

  private List<IShapes> shapes;

  /**
   * Constructor to create an empty animation panel.
   */
  public AnimationPanel() {
    super();
    this.setBackground(Color.WHITE);
    shapes = new ArrayList<IShapes>();
  }

  /**
   * Method to draw the shapes onto the given graphic.
   *
   * @param g graphic on which to draw the shapes
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    for (int i = 0; i < shapes.size(); i++) {
      IShapes shape = shapes.get(i);
      shape.draw(g2d);
    }
  }

  /**
   * Method to update the shapes being drawn in order to create the animation movements and morphs.
   *
   * @param l the new updated list of shapes to be drawn
   */
  public void updateShapes(List<IShapes> l) {
    shapes = l;
  }
}
