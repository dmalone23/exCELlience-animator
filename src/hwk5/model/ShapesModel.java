package hwk5.model;

import hwk5.animator.provider.model.ViewShape;
import hwk5.model.util.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Shapes model representing an animation of various shapes and the methods responsible for moving
 * and morphing said shapes to create an animation.
 */
public class ShapesModel implements Animation {

  private ArrayList<IShapes> loShapes;
  private ArrayList<String> loNames;
  private ArrayList<Operation> loOps;
  private int xBound;
  private int yBound;
  private int xWidth;
  private int yHeight;

  /**
   * Default constructor for initializing the shapes model.
   */
  public ShapesModel() {
    this(0, 0, 640, 480);
  }

  /**
   * Constructs a new ShapesModel with the given parameters.
   *
   * @param xBound  the x-coordinate the model starts at
   * @param yBound  the y-coordinate the model starts at
   * @param xWidth  the width of the model
   * @param yHeight the width of the model
   */
  public ShapesModel(int xBound, int yBound, int xWidth, int yHeight) {
    this.loShapes = new ArrayList<IShapes>();
    this.loNames = new ArrayList<String>();
    this.loOps = new ArrayList<Operation>();
    this.xBound = xBound;
    this.yBound = yBound;
    this.xWidth = xWidth;
    this.yHeight = yHeight;
  }

  /**
   * Constructs a new ShapesModel with the standard parameters plus the list of shapes, operations,
   * and names of shapes that will be used.
   *
   * @param xBound  the x-coordinate the model starts at
   * @param yBound  the y-coordinate the model starts at
   * @param xWidth  the width of the model
   * @param yHeight the width of the model
   * @param shape   the list of shapes that the model will use
   * @param ops     the list of operations that act on the shapes
   * @param names   the list of names of the shapes
   */
  public ShapesModel(int xBound, int yBound, int xWidth, int yHeight, List<IShapes> shape,
      List<Operation> ops, List<String> names) {
    this.loShapes = new ArrayList<IShapes>(shape);
    this.loNames = new ArrayList<String>(names);
    this.loOps = new ArrayList<Operation>(ops);
    this.xBound = xBound;
    this.yBound = yBound;
    this.xWidth = xWidth;
    this.yHeight = yHeight;
  }


  @Override
  public void addShape(String name, ShapeType type, int w, int h, int r, int g, int b, Position pos)
      throws IllegalArgumentException {
    if (pos.getX() > xBound + xWidth || pos.getX() < xBound || pos.getY() > yBound + yHeight
        || pos.getY() < yBound) {
      throw new IllegalArgumentException();
    }
    if (loNames.contains(name)) {
      throw new IllegalArgumentException();
    }
    if (w < 0 || h < 0 || r < 0 || g < 0 || b < 0 || r > 256 || g > 256 || b > 256) {
      throw new IllegalArgumentException();
    }

    if (type == ShapeType.CIRCLE) {
      loNames.add(name);
      loShapes.add(new Circle(r, g, b, pos, w, h, name));
    } else if (type == ShapeType.RECTANGLE) {
      loNames.add(name);
      loShapes.add(new Rectangle(r, g, b, pos, w, h, name));
    }
  }

  public void addShape(IShapes shape) {
    this.loShapes.add(shape);
    this.loNames.add(shape.toString());
  }

  @Override
  public List<IShapes> getCurrentState() {
    List<IShapes> ret = new ArrayList(this.loShapes);
    return ret;
  }

  @Override
  public void doTick(int tick) throws IllegalArgumentException {
    if (tick < 0 || loOps.isEmpty()) {
      throw new IllegalArgumentException();
    }
    if (tick == 0) {
      for (int i = 0; i < loOps.size(); i++) {
        for (int j = 1; j < loOps.size(); j++) {
          if (loOps.get(i).canRun(tick) && loOps.get(j).canRun(tick)
              && loOps.get(j).getShape().equals(loOps.get(i).getShape())
              && loOps.get(j).getClass().equals(loOps.get(j).getClass())) {
            throw new IllegalArgumentException();
          }
        }
      }
    }
    for (int i = 0; i < loOps.size(); i++) {
      if (loOps.get(i).canRun(tick)) {
        IShapes s = loOps.get(i).run(tick);
        loShapes.set(loNames.indexOf(s.toString()), s);
        this.updateOps(s);
      }
    }
    setStates(tick);
  }

  private void setStates(int t) {
    for (int i = 0; i < loShapes.size(); i++) {
      IShapes s = loShapes.get(i);
      State p = new State(t, s.getRCol(), s.getGCol(), s.getBCol(), s.getPosition(),
          (int)(Math.ceil(s.getWidth())), (int)(Math.ceil(s.getHeight())));
      loShapes.get(i).addState(p);
    }
  }

  @Override
  public int getFinalTick() {
    int r = 0;
    for (int i = 0; i < loOps.size(); i++) {
      if (loOps.get(i).getEndTick() > r) {
        r = loOps.get(i).getEndTick();
      }
    }
    return r;
  }

  @Override
  public void addOp(Operation op) throws IllegalArgumentException {
    if (op == null) {
      throw new IllegalArgumentException();
    }
    boolean in = false;
    for (int i = 0; i < loNames.size(); i++) {
      if (loNames.get(i).equals(op.getName())) {
        op.setShape(loShapes.get(i));
        in = true;
        break;
      }
    }
    if (!in) {
      throw new IllegalArgumentException();
    }

    this.loOps.add(op);

  }

  @Override
  public List<Operation> getOps() {
    List<Operation> ret = new ArrayList(this.loOps);
    return ret;
  }

  @Override
  public void removeOp(int i) throws IllegalArgumentException {
    if (i < 0 || i >= loOps.size()) {
      throw new IllegalArgumentException();
    }
    this.loOps.remove(i);

  }

  @Override
  public void removeShape(int i) throws IllegalArgumentException {
    if (i < 0 || i >= loShapes.size()) {
      throw new IllegalArgumentException();
    }
    this.loShapes.remove(i);
    this.loNames.remove(i);

  }

  @Override
  public void updateOps(IShapes s) {
    for (int i = 0; i < loOps.size(); i++) {
      if (loOps.get(i).getName().equals(s.toString())) {
        loOps.get(i).setShape(s);
      }
    }
  }

  @Override
  public void reset() {
    for (int i = 0; i < loShapes.size(); i++) {
      loShapes.get(i).reset();
      IShapes s = loShapes.get(i);
      updateOps(s);
    }
  }

  /**
   * Parse the respective {@link ShapeType} from the given string.
   *
   * @param type the desired shape
   * @return a {@link ShapeType} corresponding to the string
   */
  protected ShapeType determineShapeType(String type) {
    switch (type.toLowerCase()) {
      case "rectangle":
        return ShapeType.RECTANGLE;
      case "circle":
        return ShapeType.CIRCLE;
      default:
        throw new IllegalArgumentException("unknown ShapeType given");
    }
  }

  @Override
  public List<ViewShape> getViewShapes() {
    List<ViewShape> s = new ArrayList<ViewShape>(getCurrentState());
    return s;
  }

  @Override
  public int getLastTick() {
    return getFinalTick();
  }

  @Override
  public int getX() {
    return this.xBound;
  }

  @Override
  public int getY() {
    return this.yBound;
  }

  @Override
  public int getWidth() {
    return this.xWidth;
  }

  @Override
  public int getHeight() {
    return this.yHeight;
  }

  /**
   * Represents a factory class to easily create new models.
   */
  public static final class ShapesModelFactory implements AnimationBuilder<Animation> {

    private static final ArrayList<IShapes> loShapes = new ArrayList<>();
    private static final ArrayList<String> loNames = new ArrayList<>();
    private static final ArrayList<Operation> loOps = new ArrayList<>();
    private static int xBound = 0;
    private static int yBound = 0;
    private static int xWidth = 640;
    private static int yHeight = 480;

    @Override
    public Animation build() {
      ShapesModel ret = new ShapesModel();
      ret.loShapes = this.loShapes;
      ret.loOps = this.loOps;
      ret.loNames = this.loNames;
      ret.xBound = this.xBound;
      ret.yBound = this.yBound;
      ret.xWidth = this.xWidth;
      ret.yHeight = this.yHeight;
      return ret;
    }

    @Override
    public AnimationBuilder<Animation> setBounds(int x, int y, int width, int height) {
      xBound = x;
      yBound = y;
      xWidth = width;
      yHeight = height;
      return this;
    }

    @Override
    public AnimationBuilder<Animation> declareShape(String name, String type) {
      if (type.equalsIgnoreCase("rectangle")) {
        loShapes.add(new Rectangle(5, 5, 100,
            new Position(20, 20), 30, 75, name));
        loNames.add(name);
      } else {
        loShapes.add(new Circle(125, 125, 125,
            new Position(100, 100), 50, 75, name));
        loNames.add(name);
      }
      return this;
    }

    @Override
    public AnimationBuilder<Animation> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      if (x1 - x2 != 0 || y1 - y2 != 0) {
        this.loOps
            .add(new Movement(t1, t2, loShapes.get(loNames.indexOf(name)), new Position(x2, y2)));
      } else if (h1 != h2) {
        this.loOps.add(new ChngHeight(t1, t2, loShapes.get(loNames.indexOf(name)), h2));
      } else if (w1 != w2) {
        this.loOps.add(new ChngWidth(t1, t2, loShapes.get(loNames.indexOf(name)), w2));
      } else if (r1 != r2 || b1 != b2 || g1 != g2) {
        this.loOps.add(new ChngColor(t1, t2, loShapes.get(loNames.indexOf(name)), r2, g2, b2));
      } else if (t1 == t2) {
        loShapes.get(loNames.indexOf(name)).changeColor(r1, g1, b1);
        loShapes.get(loNames.indexOf(name)).moveShape(new Position(x1, y1));
        loShapes.get(loNames.indexOf(name)).changeWidth(w1);
        loShapes.get(loNames.indexOf(name)).changeHeight(h1);
        loShapes.get(loNames.indexOf(name)).setDefs(r1, g1, b1, new Position(x1, y1), w1, h1);
      }
      return this;
    }
  }

}
