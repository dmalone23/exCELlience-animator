package hwk5.view;

import hwk5.model.Animation;
import hwk5.model.IShapes;
import hwk5.model.Operation;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.naming.OperationNotSupportedException;

/**
 * SVGView class which creates an svg file for viewing the animation through the svg format.
 */
public class SVGView implements IView {

  private final int width;
  private final int height;
  private Animation model;
  private int tps;

  /**
   * SVGVIEW constructor to construct an SVGView object with the given model and animations of the
   * given dimensions.
   *
   * @param model the model that is being animated
   * @param w     the width of the animation window
   * @param h     the height of the animation model
   * @param tPS   the ticks per second
   */
  public SVGView(Animation model, int w, int h, int tPS) {
    this.model = model;
    this.width = w;
    this.height = h;
    this.tps = tPS;
  }

  @Override
  public Appendable render(int secs)
      throws IllegalArgumentException, OperationNotSupportedException {
    throw new OperationNotSupportedException();
  }

  @Override
  public File render(String fName) throws IllegalStateException {
    File svg = new File(fName);
    FileWriter svgRet = null;
    try {
      svg.createNewFile();
      svgRet = new FileWriter(svg);
      svgRet.write(" <!DOCTYPE html>\n<html>\n<body>\n");
      String windowInfo = "<svg height=\"" + this.width + "\" width=\"" + this.height + "\">\n";
      svgRet.write(windowInfo);
      for (int i = 0; i < model.getCurrentState().size(); i++) {
        IShapes shape = model.getCurrentState().get(i);
        svgRet.write(shape.svgFormat());
        svgRet.write("\n");
      }
      for (int j = 0; j < model.getOps().size(); j++) {
        Operation op = model.getOps().get(j);
        svgRet.write(op.svgFormat(tps));
        svgRet.write("\n");
      }
      svgRet.write("</svg>\n</body>\n</html>");
      svgRet.flush();
      svgRet.close();
      return svg;
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  @Override
  public void refresh() throws OperationNotSupportedException {
    throw new OperationNotSupportedException();
  }

  @Override
  public void updateModel(Animation model) {
    this.model = model;
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
