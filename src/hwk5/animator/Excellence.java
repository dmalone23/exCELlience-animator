package hwk5.animator;

import hwk5.animator.provider.controller.ProvController;
import hwk5.controller.Controller;
import hwk5.model.IShapes;
import hwk5.model.Position;
import hwk5.model.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the entry point of the ExCELLence animator.
 */
public final class Excellence {

  /**
   * The entry point of the animator (passes CLI args to controller and starts animating).
   */
  public static void main(String[] args) {

    String input = "";
    String view = "";
    String output = "";
    int tPS = 1;

    //buildAnimation1();

    ProvController con = new ProvController("Provider Interactive",
        "/Users/dm/OOD/hw/hw6/src/hwk5/animator/toh-3.txt",
        "System.out", 4);
    try {
      con.startAnimation();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    // Uncomment for animation 1.
    // Controller cont = new Controller("interactive",
    //     "/Users/dm/OOD/hw/hw6/resources/animation1.txt",
    //      "System.out", 2);

    // Uncomment for animation 2.
    // Controller cont = new Controller("interactive",
    //     "/Users/dm/OOD/hw/hw6/resources/animation2.txt",
    //     "System.out", 2);

    // Uncomment to run above animations (if chosen).
    // try {
    //   cont.startAnimation();
    // } catch (FileNotFoundException e) {
    //   e.printStackTrace();
    // }

    for (int j = 0; j < args.length; j++) {
      switch (args[j]) {
        case "-in":
          input = args[j + 1];
          break;
        case "-view":
          view = args[j + 1];
          break;
        case "-out":
          output = args[j + 1];
          break;
        case "-speed":
          tPS = Integer.valueOf(args[j + 1]);
          break;
        default:
          continue;
      }
    }

    if (view.equalsIgnoreCase("") || input.equalsIgnoreCase("")) {
      throw new IllegalArgumentException();
    }

    if (output.equalsIgnoreCase("")) {
      output = "System.out";
    }

    // DEFAULT
    if (view.equalsIgnoreCase("Provider Textual") || view
        .equalsIgnoreCase("Provider Interactive")) {
      ProvController cont = new ProvController(view, input, output, tPS);
      try {
        cont.startAnimation();
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException();
      }
    } else {
      Controller cont = new Controller(view, input, output, tPS);
      try {
        cont.startAnimation();
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException();
      }
    }


  }

  /**
   * Animation representing a simple selection sort.
   */
  public static void buildAnimation1() {
    int y1 = 30;
    int y2 = 40;
    int y3 = 10;
    int y4 = 20;
    int y5 = 50;

    int[] yNum = new int[]{30, 40, 10, 20, 50};
    int[] yNumSorted = new int[]{10, 20, 30, 40, 50};
    ArrayList<Position> positions = new ArrayList<Position>();

    positions.add(new Position(30, 200));
    positions.add(new Position(90, 190));
    positions.add(new Position(150, 220));
    positions.add(new Position(210, 210));
    positions.add(new Position(260, 180));

    File animation1 = new File("/Users/dm/OOD/hw/hw6/resources/animation1.txt");
    FileWriter fWrite = null;
    try {
      fWrite = new FileWriter(animation1);
    } catch (IOException e) {
      throw new IllegalStateException();
    }

    IShapes recty1 = new Rectangle(0, 90, 49, new Position(30, 180), 15, y1, "recty1");
    IShapes recty2 = new Rectangle(0, 90, 49, new Position(60, 180), 15, y2, "recty1");
    IShapes recty3 = new Rectangle(0, 90, 49, new Position(90, 180), 15, y3, "recty1");
    IShapes recty4 = new Rectangle(0, 90, 49, new Position(120, 180), 15, y4, "recty1");
    IShapes recty5 = new Rectangle(0, 90, 49, new Position(150, 180), 15, y5, "recty1");

    try {
      fWrite.write("canvas 0 50 250 220");
      fWrite.write("\n");
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int i = 1; i < 6; i++) {
      try {
        fWrite.write("shape rect" + i + " rectangle");
        fWrite.write("\n");
      } catch (IOException e) {
        throw new IllegalStateException();
      }
    }

    for (int i = 1; i < 6; i++) {
      try {
        fWrite.write("motion rect" + i + " 1 " + (int) positions.get(i - 1).getX()
            + " " + (int) positions.get(i - 1).getY() + " 15 " + yNum[i - 1] + " 25 90 49"
            + " 1 " + (int) positions.get(i - 1).getX() + " " + (int) positions.get(i - 1).getY()
            + " 15 " + yNum[i - 1] + " 25 90 49");
        fWrite.write("\n");
      } catch (IOException e) {
        throw new IllegalStateException();
      }
    }

    try {
      fWrite.write("motion rect1 1 " + (int) positions.get(0).getX() + " "
          + (int) positions.get(0).getY()
          + " 15 " + (int) recty1.getHeight() + " 25 90 49"
          + " 10 " + (int) positions.get(2).getX() + " "
          + (int) positions.get(0).getY() + " 15 " + (int) recty1.getHeight() + " 25 90 49");
      fWrite.write("\n");
      fWrite.write("motion rect2 1 " + (int) positions.get(1).getX() + " "
          + (int) positions.get(1).getY()
          + " 15 " + (int) recty2.getHeight() + " 25 90 49"
          + " 10 " + (int) positions.get(1).getX() + " "
          + (int) positions.get(1).getY() + " 15 " + (int) recty2.getHeight() + " 25 90 49");
      fWrite.write("\n");
      fWrite.write("Motion rect3 1 " + (int) positions.get(2).getX() + " "
          + (int) positions.get(2).getY()
          + " 15 " + (int) recty3.getHeight() + " 25 90 49"
          + " 10 " + (int) positions.get(0).getX() + " "
          + (int) positions.get(2).getY() + " 15 " + (int) recty3.getHeight() + " 25 90 49");
      fWrite.write("\n");
      fWrite.write("motion rect4 1 " + (int) positions.get(3).getX() + " "
          + (int) positions.get(3).getY()
          + " 15 " + (int) recty4.getHeight() + " 25 90 49"
          + " 10 " + (int) positions.get(3).getX() + " "
          + (int) positions.get(3).getY() + " 15 " + (int) recty4.getHeight() + " 25 90 49");
      fWrite.write("\n");
      fWrite.write("motion rect5 1 " + (int) positions.get(4).getX() + " "
          + (int) positions.get(4).getY()
          + " 15 " + (int) recty5.getHeight() + " 25 90 49"
          + " 50 " + (int) positions.get(4).getX() + " "
          + (int) positions.get(4).getY() + " 15 " + (int) recty5.getHeight() + " 25 90 49");
      fWrite.write("\n");
    } catch (IOException e) {
      System.out.println(e);
    }

    try {
      fWrite.write("Motion rect2 11 " + (int) positions.get(1).getX() + " "
          + (int) positions.get(1).getY()
          + " 15 " + (int) recty2.getHeight() + " 25 90 49"
          + " 20 " + (int) positions.get(3).getX() + " "
          + (int) positions.get(1).getY() + " 15 " + (int) recty1.getHeight() + " 25 90 49");
      fWrite.write("\n");
      fWrite.write("Motion rect4 11 " + (int) positions.get(3).getX() + " "
          + (int) positions.get(3).getY()
          + " 15 " + (int) recty4.getHeight() + " 25 90 49"
          + " 20 " + (int) positions.get(1).getX() + " "
          + (int) positions.get(3).getY() + " 15 " + (int) recty4.getHeight() + " 25 90 49");
      fWrite.write("\n");
    } catch (IOException e) {
      System.out.println(e);
    }

    try {
      fWrite.write("motion rect1 21 " + (int) positions.get(2).getX() + " "
          + (int) positions.get(0).getY()
          + " 15 " + (int) recty1.getHeight() + " 25 90 49"
          + " 30 " + (int) positions.get(2).getX() + " "
          + (int) positions.get(0).getY() + " 15 " + (int) recty1.getHeight() + " 170 0 29");
      fWrite.write("\n");
      fWrite.write("motion rect2 21 " + (int) positions.get(3).getX() + " "
          + (int) positions.get(1).getY()
          + " 15 " + (int) recty2.getHeight() + " 25 90 49"
          + " 30 " + (int) positions.get(3).getX() + " "
          + (int) positions.get(1).getY() + " 15 " + (int) recty2.getHeight() + " 170 0 29");
      fWrite.write("\n");
      fWrite.write("Motion rect3 21 " + (int) positions.get(0).getX() + " "
          + (int) positions.get(2).getY()
          + " 15 " + (int) recty3.getHeight() + " 25 90 49"
          + " 30 " + (int) positions.get(0).getX() + " "
          + (int) positions.get(2).getY() + " 15 " + (int) recty3.getHeight() + " 170 0 29");
      fWrite.write("\n");
      fWrite.write("motion rect4 21 " + (int) positions.get(1).getX() + " "
          + (int) positions.get(3).getY()
          + " 15 " + (int) recty4.getHeight() + " 25 90 49"
          + " 30 " + (int) positions.get(1).getX() + " "
          + (int) positions.get(3).getY() + " 15 " + (int) recty4.getHeight() + " 170 0 29");
      fWrite.write("\n");
      fWrite.write("motion rect5 21 " + (int) positions.get(4).getX() + " "
          + (int) positions.get(4).getY()
          + " 15 " + (int) recty5.getHeight() + " 25 90 49"
          + " 30 " + (int) positions.get(4).getX() + " "
          + (int) positions.get(4).getY() + " 15 " + (int) recty5.getHeight() + " 170 0 29");
      fWrite.write("\n");
    } catch (IOException e) {
      System.out.println(e);
    }

    try {
      fWrite.flush();
      fWrite.close();
    } catch (IOException e) {
      throw new IllegalStateException();
    }

  }


}