package hwk5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import hwk5.model.ChngColor;
import hwk5.model.IShapes;
import hwk5.model.Movement;
import hwk5.model.Operation;
import hwk5.model.Position;
import hwk5.model.Rectangle;
import hwk5.model.ShapeType;
import hwk5.model.ShapesModel;
import org.junit.Test;

/**
 * Tests ShapesModel and its associated methods.
 */
public class ShapesModelTests {

  ShapesModel ex = new ShapesModel();
  IShapes rect = new Rectangle(9, 9, 10,
      new Position(0, 0), 12, 12, "rec1");

  /**
   * Tests addShapes method.
   */
  @Test
  public void toAddShapes() {
    assertEquals(0, ex.getCurrentState().size());
    ex.addShape("blue", ShapeType.CIRCLE, 2, 2, 10,
        11, 240, new Position(0, 0));
    assertEquals(1, ex.getCurrentState().size());
    ex.addShape("rec0", ShapeType.RECTANGLE, 2, 2, 10,
        11, 240, new Position(0, 0));
    assertEquals(2, ex.getCurrentState().size());
    IShapes rect = new Rectangle(9, 9, 10,
        new Position(0, 0), 12, 12, "rec1");
    ex.addShape(rect);
    assertEquals(3, ex.getCurrentState().size());
  }

  /**
   * Tests the remove shape method.
   */
  @Test
  public void toRemoveShapes() {
    assertEquals(0, ex.getCurrentState().size());
    ex.addShape("blue", ShapeType.CIRCLE, 2, 2, 10,
        11, 240, new Position(0, 0));
    assertEquals(1, ex.getCurrentState().size());
    ex.removeShape(0);
    assertEquals(0, ex.getCurrentState().size());
  }

  /**
   * Tests removeShape error when an invalid index is given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void toRemoveShapeError1() {
    ex.addShape("blue", ShapeType.CIRCLE, 2, 2, 3,
        11, 240, new Position(0, 0));
    ex.removeShape(10);
  }

  /**
   * Tests removeShape error when an invalid index is given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void toRemoveShapeError2() {
    ex.addShape("blue", ShapeType.CIRCLE, 2, 2, 3,
        11, 240, new Position(0, 0));
    ex.removeShape(-5);
  }

  /**
   * Tests the removeOp method.
   */
  @Test
  public void toRemoveOperation() {
    Operation move = new Movement(0, 12, rect, new Position(10, 10));
    ex.addShape(rect);
    assertEquals(0, ex.getOps().size());
    ex.addOp(move);
    assertEquals(1, ex.getOps().size());
    ex.removeOp(0);
    assertEquals(0, ex.getOps().size());
  }

  /**
   * Tests removeOp error when an invalid index is given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void toRemoveOpError1() {
    ex.addShape(rect);
    Operation move = new Movement(0, 12, rect, new Position(10, 10));
    ex.addOp(move);
    ex.removeOp(10);
  }

  /**
   * Tests removeShape error when an invalid index is given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void toRemoveOpError2() {
    ex.addShape(rect);
    Operation move = new Movement(0, 12, rect, new Position(10, 10));
    ex.addOp(move);
    ex.removeOp(-5);
  }

  /**
   * Tests addShapes method error on color values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void toAddShapesError1() {
    ex.addShape("blue", ShapeType.CIRCLE, 2, 2, -2,
        11, 240, new Position(0, 0));
  }

  /**
   * Tests addShapes method error on color values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void toAddShapesError2() {
    ex.addShape("blue", ShapeType.CIRCLE, 2, 2, 2,
        -2, 240, new Position(0, 0));
  }

  /**
   * Tests addShapes method error on color values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void toAddShapesError3() {
    ex.addShape("blue", ShapeType.CIRCLE, 2, 2, 2,
        12, -13, new Position(0, 0));
  }

  /**
   * Tests addShapes method error on color values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void toAddShapesError4() {
    ex.addShape("blue", ShapeType.CIRCLE, 2, 2, 300,
        12, 13, new Position(0, 0));
  }

  /**
   * Tests addShapes method error on color values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void toAddShapesError5() {
    ex.addShape("blue", ShapeType.CIRCLE, 2, 2, 30,
        1200, 13, new Position(0, 0));
  }

  /**
   * Tests addShapes method error on color values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void toAddShapesError6() {
    ex.addShape("blue", ShapeType.CIRCLE, 2, 2, 30,
        12, 1300, new Position(0, 0));
  }

  /**
   * Tests addShapes method error on duplicate names.
   */
  @Test(expected = IllegalArgumentException.class)
  public void toAddShapesError7() {
    ex.addShape("blue", ShapeType.CIRCLE, 2, 2, 30,
        12, 13, new Position(0, 0));
    ex.addShape("blue", ShapeType.CIRCLE, 2, 2, 30,
        12, 13, new Position(0, 0));
  }

  /**
   * Tests getCurrentState method.
   */
  @Test
  public void testGetCurrentState() {
    assertTrue(ex.getCurrentState().isEmpty());
    assertEquals(0, ex.getCurrentState().size());
    ex.addShape("blue", ShapeType.CIRCLE, 2, 2, 10,
        11, 240, new Position(0, 0));
    assertEquals(1, ex.getCurrentState().size());
    ex.addShape("rec", ShapeType.RECTANGLE, 2, 2, 10,
        11, 240, new Position(0, 0));
    assertEquals(2, ex.getCurrentState().size());
    assertFalse(ex.getCurrentState().isEmpty());
  }

  /**
   * Tests getOps method.
   */
  @Test
  public void testGetOps() {
    IShapes rect = new Rectangle(9, 9, 10,
        new Position(0, 0), 12, 12, "rec1");
    Operation move = new Movement(0, 12, rect, new Position(10, 10));
    Operation morph = new ChngColor(0, 12, rect, 5, 5, 5);
    ex.addShape(rect);
    assertTrue(ex.getOps().isEmpty());
    assertEquals(0, ex.getOps().size());
    ex.addOp(move);
    assertEquals(1, ex.getOps().size());
    ex.addOp(morph);
    assertEquals(2, ex.getOps().size());
    assertFalse(ex.getOps().isEmpty());
  }

  /**
   * Tests addOps method.
   */
  @Test
  public void testAddOps() {
    IShapes rect = new Rectangle(9, 9, 10,
        new Position(0, 0), 12, 12, "rec1");
    Operation move = new Movement(0, 12, rect, new Position(10, 10));
    ex.addShape(rect);
    try {
      ex.doTick(10);
    } catch (IllegalArgumentException e) {
      ex.addOp(move);
      assertFalse(ex.getOps().isEmpty());
    }
    ex.doTick(10);
  }

  /**
   * Tests exception in addOps method when inputting null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddOpsError() {
    ex.addOp(null);
  }

  /**
   * Tests the doTick methods error on duplicate operations.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDoTickError1() {
    IShapes rect = new Rectangle(9, 9, 10,
        new Position(0, 0), 12, 12, "rec1");
    Operation move = new Movement(0, 12, rect, new Position(10, 10));
    ex.addShape(rect);
    ex.addOp(move);
    ex.addOp(move);
    ex.doTick(0);
  }

  /**
   * Tests the doTick methods error on bad input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDoTickError2() {
    IShapes rect = new Rectangle(9, 9, 10,
        new Position(0, 0), 12, 12, "rec1");
    Operation move = new Movement(0, 12, rect, new Position(10, 10));
    ex.addShape(rect);
    ex.addOp(move);
    ex.doTick(-10);
  }

  /**
   * Tests the doTick methods error on bad input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDoTickError3() {
    IShapes rect = new Rectangle(9, 9, 10,
        new Position(0, 0), 12, 12, "rec1");
    Operation move = new Movement(0, 12, rect, new Position(10, 10));
    ex.doTick(10);
  }


  /**
   * Tests the updateOps method.
   */
  @Test
  public void testUpdateOps() {
    IShapes rect = new Rectangle(9, 9, 10,
        new Position(0, 0), 12, 12, "rec1");
    Operation move = new Movement(0, 10, rect, new Position(10, 10));
    ex.addShape(rect);
    ex.addOp(move);
    assertEquals(0, move.getShape().getPosition().getX(), 0.01);
    ex.doTick(0);
    assertEquals(1, move.getShape().getPosition().getX(), 0.01);
  }

  /**
   * Tests the reset method.
   */
  @Test
  public void testReset() {
    IShapes rect = new Rectangle(9, 9, 10,
        new Position(0, 0), 12, 12, "rec1");
    rect.setDefs(9, 9, 9, new Position(10, 10), 5, 5);
    Operation move = new Movement(0, 10, rect, new Position(10, 10));
    ex.addShape(rect);
    ex.addOp(move);
    assertEquals(0, move.getShape().getPosition().getX(), 0.01);
    ex.doTick(0);
    assertEquals(1, move.getShape().getPosition().getX(), 0.01);
    ex.reset();
    assertEquals(10, move.getShape().getPosition().getX(), 0.01);
  }

  @Test
  public void testGetFinalTick() {
    IShapes rect = new Rectangle(9, 9, 10,
        new Position(0, 0), 12, 12, "rec1");
    rect.setDefs(9, 9, 9, new Position(10, 10), 5, 5);
    Operation move = new Movement(0, 10, rect, new Position(10, 10));
    Operation move2 = new Movement(12, 20, rect, new Position(50, 50));
    Operation move3 = new Movement(30, 50, rect, new Position(0, 0));
    ex.addShape(rect);
    ex.addOp(move);
    assertEquals(10, ex.getFinalTick());
    ex.addOp(move2);
    assertEquals(20, ex.getFinalTick());
    ex.addOp(move3);
    assertEquals(50, ex.getFinalTick());
  }


}
