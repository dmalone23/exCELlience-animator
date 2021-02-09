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
import hwk5.view.SVGView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

/**
 * A class to test the functionality of the SVG view.
 */
public class SVGViewTests {

  ShapesModel ex;
  IShapes rect;
  Operation move;
  Operation changeColor;
  Operation changeWidth;
  Operation changeHeight;
  SVGView svg;

  /**
   * Initialize the testing environment.
   */
  @Before
  public void init() {
    ex = new ShapesModel();
    rect = new Rectangle(9, 9, 10, new Position(0, 0), 12, 12, "rec1");
    move = new Movement(0, 12, rect, new Position(10, 10));
    changeColor = new ChngColor(13, 17, rect, 5, 5, 5);
    changeWidth = new ChngWidth(18, 20, rect, 18);
    changeHeight = new ChngHeight(21, 22, rect, 22);
    ex.addShape(rect);
    svg = new SVGView(ex, 640, 480, 4);
    ex.addOp(move);
    ex.addOp(changeColor);
    ex.addOp(changeWidth);
    ex.addOp(changeHeight);
  }

  /**
   * A test to show that the SVG view outputs the correct operation given different tickrates.
   */
  @Test
  public void testSVGViewMove() {
    assertEquals(
        "<rect id=\"rec1\" x=\"0\" y=\"0\" width=\"12\" height=\"12\" fill=\"rgb(9,9,10)\"  />",
        rect.svgFormat());
    for (int i = 0; i < 13; i++) {
      move.run(i);
    }
    assertEquals(
        "<rect id=\"rec1\" x=\"10\" y=\"10\" width=\"12\" height=\"12\" fill=\"rgb(9,9,10)\"  />",
        move.getShape().svgFormat());
  }

  /**
   * A test to show that the SVG view outputs the correct operation given different tickrates.
   */
  @Test
  public void testSVGViewColor() {
    assertEquals(
        "<rect id=\"rec1\" x=\"0\" y=\"0\" width=\"12\" height=\"12\" fill=\"rgb(9,9,10)\"  />",
        rect.svgFormat());
    for (int i = 0; i < 18; i++) {
      ex.doTick(i);
    }
    assertEquals(
        "<rect id=\"rec1\" x=\"10\" y=\"10\" width=\"12\" height=\"12\" fill=\"rgb(5,5,5)\"  />",
        rect.svgFormat());
  }

  /**
   * A test to show that the SVG view outputs the correct operation given different tickrates.
   */
  @Test
  public void testSVGViewWidth() {
    assertEquals(
        "<rect id=\"rec1\" x=\"0\" y=\"0\" width=\"12\" height=\"12\" fill=\"rgb(9,9,10)\"  />",
        rect.svgFormat());
    for (int i = 0; i < 21; i++) {
      ex.doTick(i);
    }
    assertEquals(
        "<rect id=\"rec1\" x=\"10\" y=\"10\" width=\"18\" height=\"12\" fill=\"rgb(5,5,5)\"  />",
        rect.svgFormat());
  }

  /**
   * A test to show that the SVG view outputs the correct operation given different tickrates.
   */
  @Test
  public void testSVGViewHeight() {
    assertEquals(
        "<rect id=\"rec1\" x=\"0\" y=\"0\" width=\"12\" height=\"12\" fill=\"rgb(9,9,10)\"  />",
        rect.svgFormat());
    for (int i = 0; i < 23; i++) {
      ex.doTick(i);
    }
    assertEquals(
        "<rect id=\"rec1\" x=\"10\" y=\"10\" width=\"18\" height=\"22\" fill=\"rgb(5,5,5)\"  />",
        rect.svgFormat());
  }

  /**
   * A test to show that the SVG view outputs the correct operation to the file.
   */
  @Test
  public void testSVG() throws FileNotFoundException {
    String out = "";
    File svgOut = svg.render("outfile.svg");
    Scanner myReader = new Scanner(svgOut);
    while (myReader.hasNextLine()) {
      out += myReader.nextLine();
      out += "\n";
    }
    assertEquals("<!DOCTYPE html>" + "\n"
        + "<html>" + "\n" + "<body>" + "\n"
        + "<svg height=\"640\" width=\"480\">" + "\n"
        + "<rect id=\"rec1\" x=\"0\" y=\"0\" width=\"12\" height=\"12\" fill=\"rgb(9,9,10)\"  />"
        + "\n" + "<animate" + "\n" + "xlink:href=\"#rec1\"" + "\n" + "attributeName=\"x\"" + "\n"
        + "from=\"0.0\"" + "\n" + "to=\"10.0\"" + "\n" + "dur=\"3.0\"" + "\n" + "fill=\"freeze\" />"
        + "\n"
        + "<animate" + "\n" + "xlink:href=\"#rec1\"" + "\n" + "attributeName=\"y\"" + "\n"
        + "from=\"0.0\"" + "\n" + "to=\"10.0\"" + "\n" + "dur=\"3.0\"" + "\n"
        + "fill=\"freeze\" />" + "\n" + "<animate" + "\n" + "xlink:href=\"#rec1\"" + "\n"
        + "attributeName=\"fill\"" + "\n" + "from=\"rgb(9,9,10)\"" + "\n"
        + "to=\"rgb(5,5,5)\""
        + "\n" + "dur=\"1.0\"" + "\n" + "fill=\"freeze\" />" + "\n" + "<animate" + "\n"
        + "xlink:href=\"#rec1\"" + "\n" + "attributeName=\"width\"" + "\n" + "from=\"12.0\""
        + "\n" + "to=\"18\"" + "\n" + "dur=\"0.5\"" + "\n" + "fill=\"freeze\" />" + "\n"
        + "<animate" + "\n" + "xlink:href=\"#rec1\"" + "\n" + "attributeName=\"height\"" + "\n"
        + "from=\"12.0\"" + "\n" + "to=\"22\"" + "\n" + "dur=\"0.25\"" + "\n"
        + "fill=\"freeze\" />" + "\n"
        + "</svg>" + "\n" + "</body>" + "\n" + "</html>", out.strip());
  }


}
