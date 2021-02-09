package hwk5;

import static org.junit.Assert.assertEquals;

import hwk5.model.Circle;
import hwk5.model.Position;
import hwk5.model.Rectangle;
import org.junit.Test;

/**
 * Tests IShapes interface and its  associated classes.
 */
public class IShapesTests {

  Rectangle ret1 = new Rectangle(255, 200, 150, new Position(2, 2),
      20, 20, "ret1");
  Circle circle1 = new Circle(255, 200, 150, new Position(1, 1),
      20, 20, "circ1");

  /**
   * Tests getPosition method on rectangle.
   */
  @Test
  public void testGetPositionRet() {
    assertEquals(2, ret1.getPosition().getX(), .0001);
    assertEquals(2, ret1.getPosition().getY(), .0001);
    ret1.moveShape(new Position(3, 3));
    assertEquals(3, ret1.getPosition().getX(), .0001);
    assertEquals(3, ret1.getPosition().getY(), .0001);
  }

  /**
   * Tests getPosition method on circle.
   */
  @Test
  public void testGetPositionCirc() {
    assertEquals(1, circle1.getPosition().getX(), .0001);
    assertEquals(1, circle1.getPosition().getY(), .0001);
    circle1.moveShape(new Position(3, 3));
    assertEquals(3, circle1.getPosition().getX(), .0001);
    assertEquals(3, circle1.getPosition().getY(), .0001);
  }

  /**
   * Tests moveShape method on rectangle.
   */
  @Test
  public void testMoveShapeRet() {
    assertEquals(2, ret1.getPosition().getX(), .001);
    ret1.moveShape(new Position(10, 10));
    assertEquals(10, ret1.getPosition().getX(), .001);
    ret1.moveShape(new Position(-10, -10));
    assertEquals(-10, ret1.getPosition().getX(), .001);
  }

  /**
   * Tests moveShape method on circle.
   */
  @Test
  public void testMoveShapeCircle() {
    assertEquals(1, circle1.getPosition().getX(), .001);
    circle1.moveShape(new Position(0, 0));
    assertEquals(0, circle1.getPosition().getX(), .001);
    circle1.moveShape(new Position(-10, -10));
    assertEquals(-10, circle1.getPosition().getX(), .001);
  }

  /**
   * Tests moveShape method on rectangle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveShapeRetError() {
    assertEquals(2, ret1.getPosition().getX(), .001);
    ret1.moveShape(null);
  }

  /**
   * Tests moveShape method on circle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveShapeCircError() {
    assertEquals(1, circle1.getPosition().getX(), .001);
    circle1.moveShape(null);
  }

  /**
   * Tests changeColor method on circle.
   */
  @Test
  public void testChangeColorCircle() {
    assertEquals(150, circle1.getBCol(), .001);
    assertEquals(200, circle1.getGCol(), .001);
    assertEquals(255, circle1.getRCol(), .001);
    circle1.changeColor(0, 0, 0);
    assertEquals(0, circle1.getBCol(), .001);
    assertEquals(0, circle1.getGCol(), .001);
    assertEquals(0, circle1.getRCol(), .001);
  }

  /**
   * Tests changeColor method on rectangle.
   */
  @Test
  public void testChangeColorRet() {
    assertEquals(150, ret1.getBCol(), .001);
    assertEquals(200, ret1.getGCol(), .001);
    assertEquals(255, ret1.getRCol(), .001);
    ret1.changeColor(0, 0, 0);
    assertEquals(0, ret1.getBCol(), .001);
    assertEquals(0, ret1.getGCol(), .001);
    assertEquals(0, ret1.getRCol(), .001);
  }

  /**
   * Tests changeColor method on circle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorCircError1() {
    circle1.changeColor(0, -20, 0);
  }

  /**
   * Tests changeColor method on circle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorCircError2() {
    circle1.changeColor(-10, 0, 0);
  }

  /**
   * Tests changeColor method on circle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorCircError3() {
    circle1.changeColor(0, 0, -120);
  }

  /**
   * Tests changeColor method on circle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorCircError4() {
    circle1.changeColor(0, 290, 0);
  }

  /**
   * Tests changeColor method on circle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorCircError5() {
    circle1.changeColor(500, 0, 0);
  }

  /**
   * Tests changeColor method on circle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorCircError6() {
    circle1.changeColor(0, 0, 600);
  }

  /**
   * Tests changeColor method on rectangle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorRetError1() {
    ret1.changeColor(0, -20, 0);
  }

  /**
   * Tests changeColor method on rectangle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorRetError2() {
    ret1.changeColor(-10, 0, 0);
  }

  /**
   * Tests changeColor method on rectangle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorRetError3() {
    ret1.changeColor(0, 0, -120);
  }

  /**
   * Tests changeColor method on rectangle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorRetError4() {
    ret1.changeColor(0, 290, 0);
  }

  /**
   * Tests changeColor method on rectangle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorRetError5() {
    ret1.changeColor(500, 0, 0);
  }

  /**
   * Tests changeColor method on rectangle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeColorRetError6() {
    ret1.changeColor(0, 0, 600);
  }

  /**
   * Tests changeWidth method on rectangle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthRetError() {
    ret1.changeWidth(-10);
  }

  /**
   * Tests changeHeight method on rectangle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightRetError() {
    ret1.changeHeight(-10);
  }

  /**
   * Tests changeWidth method on circle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeWidthCircleError() {
    circle1.changeWidth(-10);
  }

  /**
   * Tests changeHeight method on circle erring.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeHeightCircleError() {
    circle1.changeHeight(-10);
  }

  /**
   * Tests changeWidth method on circle.
   */
  @Test
  public void testChangeWidthCircle() {
    assertEquals(20, circle1.getWidth(), .001);
    circle1.changeWidth(0);
    assertEquals(0, circle1.getWidth(), .001);
    circle1.changeWidth(100);
    assertEquals(100, circle1.getWidth(), .001);
  }

  /**
   * Tests changeHeight method on circle.
   */
  @Test
  public void testChangeHeightCircle() {
    assertEquals(20, circle1.getHeight(), .001);
    circle1.changeHeight(0);
    assertEquals(0, circle1.getHeight(), .001);
    circle1.changeHeight(100);
    assertEquals(100, circle1.getHeight(), .001);
  }

  /**
   * Tests changeWidth method on rectangle.
   */
  @Test
  public void testChangeWidthRect() {
    assertEquals(20, ret1.getWidth(), .001);
    ret1.changeWidth(0);
    assertEquals(0, ret1.getWidth(), .001);
    ret1.changeWidth(100);
    assertEquals(100, ret1.getWidth(), .001);
  }

  /**
   * Tests changeHeight method on rectangle.
   */
  @Test
  public void testChangeHeightRect() {
    assertEquals(20, ret1.getHeight(), .001);
    ret1.changeHeight(0);
    assertEquals(0, ret1.getHeight(), .001);
    ret1.changeHeight(100);
    assertEquals(100, ret1.getHeight(), .001);
  }

  @Test
  public void testReset() {
    assertEquals(20, ret1.getHeight(), .001);
    ret1.setDefs(0, 0, 0, new Position(0, 0), 10, 10);
    ret1.reset();
    assertEquals(10, ret1.getHeight(), .001);
  }

  @Test
  public void testSetDef() {
    assertEquals(20, ret1.getHeight(), .001);
    ret1.setDefs(0, 0, 0, new Position(0, 0), 10, 10);
    ret1.reset();
    assertEquals(10, ret1.getHeight(), .001);
    ret1.setDefs(0, 0, 0, new Position(0, 0), 10, 20);
    ret1.reset();
    assertEquals(20, ret1.getHeight(), .001);
  }
}


