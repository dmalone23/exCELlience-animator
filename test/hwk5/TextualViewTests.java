package hwk5;

import static org.junit.Assert.assertEquals;

import hwk5.model.ChngColor;
import hwk5.model.ChngHeight;
import hwk5.model.ChngWidth;
import hwk5.model.IShapes;
import hwk5.model.Movement;
import hwk5.model.Operation;
import hwk5.model.Position;
import hwk5.model.Rectangle;
import hwk5.model.ShapesModel;
import hwk5.view.TextualView;
import org.junit.Test;

/**
 * A class to test the functionality of the textual view.
 */
public class TextualViewTests {

  ShapesModel ex = new ShapesModel();

  /**
   * A test to show that the textual view outputs the correct operation given different numbers of
   * ticks per second.
   */
  @Test
  public void textViewMove() {
    IShapes rect = new Rectangle(9, 9, 10,
        new Position(0, 0), 12, 12, "rec1");
    Operation move = new Movement(0, 12, rect, new Position(10, 10));
    ex.addShape(rect);
    ex.addOp(move);

    TextualView view = new TextualView(ex);
    assertEquals(
        "motion rec1 start state: 0.0 0 0 12 12 9 9 10 end state: 12.0 10 10 12 12 9 9 10",
        move.write(1));
    assertEquals(
        "motion rec1 start state: 0.0 0 0 12 12 9 9 10 end state: 1.0 10 10 12 12 9 9 10",
        move.write(12));
  }

  /**
   * A test to show that the textual view outputs the correct operation given different numbers of
   * ticks per second.
   */
  @Test
  public void textViewColor() {
    IShapes rect = new Rectangle(9, 9, 10,
        new Position(0, 0), 12, 12, "rec1");
    Operation morph = new ChngColor(0, 12, rect, 0, 0, 0);
    ex.addShape(rect);
    ex.addOp(morph);

    TextualView view = new TextualView(ex);
    assertEquals("morph rec1 start state: 0.0 0 0 12 12 9 9 10 end state: 1.0 0 0 12 12 0 0 0",
        morph.write(12));
  }

  /**
   * A test to show that the textual view outputs the correct operation given different numbers of
   * ticks per second.
   */
  @Test
  public void textViewWidth() {
    IShapes rect = new Rectangle(9, 9, 10,
        new Position(0, 0), 12, 12, "rec1");
    Operation morph = new ChngWidth(1, 7, rect, 100);
    ex.addShape(rect);
    ex.addOp(morph);

    TextualView view = new TextualView(ex);
    assertEquals("morph rec1 start state: 1.0 0 0 12 12 9 9 10 end state: 7.0 0 0 100 12 9 9 10",
        morph.write(1));
    assertEquals("morph rec1 start state: 0.1 0 0 12 12 9 9 10 end state: 0.7 0 0 100 12 9 9 10",
        morph.write(10));
  }

  /**
   * A test to show that the textual view outputs the correct operation given different numbers of
   * ticks per second.
   */
  @Test
  public void textViewHeight() {
    IShapes rect = new Rectangle(9, 9, 10,
        new Position(0, 0), 12, 12, "rec1");
    Operation morph = new ChngHeight(1, 200, rect, 100);
    ex.addShape(rect);
    ex.addOp(morph);

    TextualView view = new TextualView(ex);
    assertEquals(
        "morph rec1 start state: 1.0 0 0 12 12 9 9 10 end state: 200.0 0 0 12 100 9 9 10",
        morph.write(1));
    assertEquals("morph rec1 start state: 0.1 0 0 12 12 9 9 10 end state: 20.0 0 0 12 100 9 9 10",
        morph.write(10));
  }

  /**
   * A test to show that render throws an error when given a non natural ticks per second.
   */
  @Test(expected = IllegalArgumentException.class)
  public void textViewError() {
    IShapes rect = new Rectangle(9, 9, 10,
        new Position(0, 0), 12, 12, "rec1");
    Operation move = new Movement(0, 12, rect, new Position(10, 10));
    ex.addShape(rect);
    ex.addOp(move);

    TextualView view = new TextualView(ex);
    view.render(-10);
  }

}
