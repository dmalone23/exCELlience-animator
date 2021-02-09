package hwk5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import hwk5.model.ChngColor;
import hwk5.model.ChngHeight;
import hwk5.model.ChngWidth;
import hwk5.model.IShapes;
import hwk5.model.Movement;
import hwk5.model.Operation;
import hwk5.model.Position;
import hwk5.model.Rectangle;
import org.junit.Before;
import org.junit.Test;

/**
 * Contains tests to check viability of model operation objects.
 */
public class OperationTests {

  Operation mv1;
  Operation ch1;
  Operation cw1;
  Operation cc1;
  Operation cc2;

  IShapes s1;
  IShapes s2;

  @Before
  public void init() {
    s1 = new Rectangle(255, 0, 0, new Position(15, 75), 45, 25, "Rectangle");
    s2 = new Rectangle(0, 255, 0, new Position(75, 15), 60, 60, "Circle");
    mv1 = new Movement(1, 10, s1, new Position(24, 93));
    ch1 = new ChngHeight(1, 10, s1, 25 + 9);
    cw1 = new ChngWidth(1, 10, s1, 45 + 9);
    cc1 = new ChngColor(1, 10, s2, 0 + 0, 255 + 0, 0 + 9);
    cc2 = new ChngColor(1, 10, s2, 0 + 0, 255 + -9, 0 + 0);
  }

  @Test
  public void testCanRun() {
    assertTrue(mv1.canRun(5));
    assertFalse(mv1.canRun(20));
    assertFalse(mv1.canRun(0));
  }

  @Test
  public void testRun() {
    // Test movement function objects.
    assertEquals(15, s1.getPosition().getX(), 0.0001);
    assertEquals(75, s1.getPosition().getY(), 0.0001);
    this.mv1.run(1);
    assertEquals(16, s1.getPosition().getX(), 0.0001);
    assertEquals(77, s1.getPosition().getY(), 0.0001);

    // Test function objects to change width.
    assertEquals(45, this.s1.getWidth(), 0.0001);
    this.cw1.run(1);
    assertEquals(46, this.s1.getWidth(), 0.0001);

    // Test function objects to change height.
    assertEquals(25, this.s1.getHeight(), 0.0001);
    this.ch1.run(1);
    assertEquals(26, this.s1.getHeight(), 0.0001);

    // Test function objects to change color (positive change).
    assertEquals(0, this.s2.getBCol(), 0.0001);
    this.cc1.run(1);
    assertEquals(1, this.s2.getBCol(), 0.0001);

    // Test function objects to change color (negative change).
    assertEquals(255, this.s2.getGCol(), 0.0001);
    this.cc2.run(1);
    assertEquals(254, this.s2.getGCol(), 0.0001);
  }

  /**
   * Tests the getName method on various operations.
   */
  @Test
  public void testName() {
    assertEquals(mv1.getName(), "Rectangle");
    assertEquals(cc1.getName(), "Circle");
  }

  /**
   * Tests setShape method on various operations.
   */
  @Test
  public void testSetShapes() {
    assertEquals(mv1.getShape(), s1);
    mv1.setShape(s2);
    assertEquals(mv1.getShape(), s2);
  }

  /**
   * Tests getEndTick method on various operations.
   */
  @Test
  public void testGetEndTick() {
    assertEquals(mv1.getEndTick(), 10);
    assertEquals(cc1.getEndTick(), 10);
  }

}
