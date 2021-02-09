package hwk5.view;

import hwk5.model.ShapesModel;

/**
 * A class for building default Views using a given view type.
 */
public class ViewFactory {

  /**
   * The method for building default Views using a given view type, represented as a string.
   *
   * @param type the view type to be built
   */
  public IView factory(String type) {
    switch (type.toLowerCase()) {
      case "svg":
        return new SVGView(new ShapesModel(), 640, 480, 4);
      case "text":
        return new TextualView(new ShapesModel());
      case "visual":
        return new JSwingView(new ShapesModel(), 640, 480);
      case "interactive":
        return new InteractiveView(new ShapesModel(), 640, 640, 4);
      default:
        throw new IllegalArgumentException("received an unknown view type");
    }
  }
}

