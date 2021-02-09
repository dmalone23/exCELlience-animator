package hwk5.controller;

import hwk5.model.Animation;
import hwk5.model.ShapesModel;
import hwk5.model.ShapesModel.ShapesModelFactory;
import hwk5.view.IView;
import hwk5.view.InteractiveView;
import hwk5.view.JSwingView;
import hwk5.view.SVGView;
import hwk5.view.TextualView;
import hwk5.view.ViewType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.naming.OperationNotSupportedException;
import javax.swing.Timer;

/**
 * Represents the controller of the animator, handling input and output between model and view.
 */
public class Controller implements ActionListener {

  private Scanner scan;
  private IView viewer;
  private Animation mod;
  private boolean quit;
  private ViewType type;
  private Timer tim;
  private String in;
  private String out;
  private Readable rd;
  private int tPS;
  private int[] curTick;
  private boolean looping;

  /**
   * The constructor for a controller object.
   *
   * @param view   the type of view being animated.
   * @param input  the input being fed to the controller.
   * @param output the output destination of the controller.
   * @param tps    the ticks per second.
   */
  public Controller(String view, String input, String output, int tps)
      throws IllegalArgumentException {
    if (view == null || input == null || output == null || tps < 0) {
      throw new IllegalArgumentException();
    }
    mod = new ShapesModel();
    if (view.equalsIgnoreCase("text")) {
      type = ViewType.TEXT;
      viewer = new TextualView(mod);
    } else if (view.equalsIgnoreCase("svg")) {
      type = ViewType.SVG;
      viewer = new SVGView(mod, 640, 640, tps);
    } else if (view.equalsIgnoreCase("visual")) {
      type = ViewType.JSWING;
      viewer = new JSwingView(mod, 640, 640);
    } else if (view.equalsIgnoreCase("interactive")) {
      type = ViewType.INTERACTIVE;
      viewer = new InteractiveView(mod, 640, 640, tps);
    }

    tPS = tps;
    in = input;
    out = output;
    curTick = new int[]{0};
    looping = false;
  }

  /**
   * Read the input file and act accordingly based on the type of controller.
   * @throws IllegalStateException if given an unsupported operation when rendering
   * @throws FileNotFoundException if given a file that does not exist/incorrect filepath
   */
  public void startAnimation() throws IllegalStateException, FileNotFoundException {
    this.doIn();
    viewer.updateModel(mod);
    if (type == ViewType.SVG) {
      if (out.equalsIgnoreCase("system.out")) {
        File f = null;
        try {
          f = viewer.render("output.svg");
        } catch (OperationNotSupportedException e) {
          throw new IllegalStateException();
        }
        Scanner myReader = null;
        try {
          myReader = new Scanner(f);
        } catch (FileNotFoundException e) {
          throw new IllegalStateException();
        }
        String output = "";
        while (myReader.hasNextLine()) {
          output += myReader.nextLine();
          output += "\n";
        }
        System.out.println(output);
      } else {
        try {
          File outFile = viewer.render(out);
        } catch (OperationNotSupportedException e) {
          throw new IllegalStateException();
        }

      }
    } else if (type == ViewType.JSWING) {
      tim = new Timer(1000 / tPS, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          mod.doTick(curTick[0]);
          curTick[0]++;
          viewer.updateModel(mod);
          try {
            viewer.refresh();
          } catch (OperationNotSupportedException operationNotSupportedException) {
            operationNotSupportedException.printStackTrace();
          }
        }
      });
      tim.start();

    } else if (type == ViewType.TEXT) {
      Appendable ap;
      try {
        ap = viewer.render(tPS);
      } catch (OperationNotSupportedException e) {
        throw new IllegalStateException();
      }
      if (out.equalsIgnoreCase("System.out")) {
        System.out.println(ap);
      } else {
        File fileout = new File(out);
        FileWriter ret;
        try {
          ret = new FileWriter(fileout);
          ret.write(String.valueOf(ap));
        } catch (IOException e) {
          System.out.println(e);
          throw new IllegalStateException();
        }
      }
    } else if (type == ViewType.INTERACTIVE) {
      try {
        viewer.setActionListener(this);
      } catch (OperationNotSupportedException e) {
        System.out.println(e);
      }

      tim = new Timer((int) Math.ceil(1000 / tPS),
          new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              if (looping && curTick[0] > mod.getFinalTick()) {
                tim.restart();
                mod.reset();
                curTick[0] = 0;
                tim.start();
              }
              mod.doTick(curTick[0]);
              curTick[0]++;
              viewer.updateModel(mod);
              try {
                viewer.refresh();
              } catch (OperationNotSupportedException operationNotSupportedException) {
                throw new IllegalStateException();
              }
            }
          });
    }
  }

  private void doIn() throws FileNotFoundException {
    File in = new File(this.in);
    Scanner myReader = new Scanner(in);
    ShapesModelFactory s = new ShapesModelFactory();
    while (myReader.hasNext()) {
      String cmd = myReader.next();
      if (cmd.equalsIgnoreCase("canvas")) {
        s.setBounds(myReader.nextInt(), myReader.nextInt(), myReader.nextInt(), myReader.nextInt());
      } else if (cmd.equalsIgnoreCase("motion")) {
        s.addMotion(myReader.next(), myReader.nextInt(), myReader.nextInt(), myReader.nextInt(),
            myReader.nextInt(), myReader.nextInt(), myReader.nextInt(), myReader.nextInt(),
            myReader.nextInt(),
            myReader.nextInt(), myReader.nextInt(), myReader.nextInt(), myReader.nextInt(),
            myReader.nextInt(), myReader.nextInt(), myReader.nextInt(), myReader.nextInt());
      } else if (cmd.equalsIgnoreCase("shape")) {
        s.declareShape(myReader.next(), myReader.next());
      }
    }
    mod = s.build();

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Play": {
        tim.start();
        break;
      }
      case "Slow Forward": {
        tim.setDelay((int) Math.ceil(tim.getDelay() * 1.25));
        break;
      }
      case "Fast Forward": {
        tim.setDelay((int) Math.ceil(tim.getDelay() * .75));
        break;
      }
      case "Restart": {
        tim.restart();
        mod.reset();
        curTick[0] = 0;
        break;
      }
      case "Loop": {
        looping = !looping;
        break;
      }
      case "Pause": {
        tim.stop();
        break;
      }
      default:
        throw new IllegalArgumentException("Undefined action.");
    }
  }
}
