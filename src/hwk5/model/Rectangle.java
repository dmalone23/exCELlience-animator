package hwk5.model;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;


/**
 * Rectangle class representing rectangular shapes.
 */
public class Rectangle implements IShapes {

  private final String name;
  private int r;
  private int g;
  private int b;
  private Position position;
  private double width;
  private double height;
  private int defR;
  private int defG;
  private int defB;
  private Position defPosition;
  private double defWidth;
  private double defHeight;
  private ArrayList<State> loStates;

  /**
   * Rectangle constructor for creating a rectangle shape.
   *
   * @param r        the color of the rectangle
   * @param g        the color of the rectangle
   * @param b        the color of the rectangle
   * @param position the position of the rectangle
   * @param width    the width of the rectangle
   * @param height   the height of the rectangle
   * @param name     the name of the rectangle
   */
  public Rectangle(int r, int g, int b, Position position, int width, int height, String name) {
    this.position = position;
    this.width = width;
    this.height = height;
    this.name = name;
    this.r = r;
    this.b = b;
    this.g = g;
    this.defPosition = position;
    this.defWidth = width;
    this.defHeight = height;
    this.defR = r;
    this.defB = b;
    this.defG = g;
    this.loStates = new ArrayList<State>();
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public int getRCol() {
    return r;
  }

  @Override
  public int getGCol() {
    return g;
  }

  @Override
  public int getBCol() {
    return b;
  }

  @Override
  public void changeWidth(double w) {
    if (w < 0) {
      throw new IllegalArgumentException();
    }
    this.width = w;
  }

  @Override
  public void changeHeight(double h) {
    if (h < 0) {
      throw new IllegalArgumentException();
    }
    this.height = h;
  }

  @Override
  public void changeColor(int r, int g, int b) {
    if (r < 0 || g < 0 || b < 0 || r > 256 || g > 256 || b > 256) {
      throw new IllegalArgumentException();
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  @Override
  public Position getPosition() {
    return new Position(this.position.getX(), this.position.getY());
  }

  @Override
  public void moveShape(Position newPosition) throws IllegalArgumentException {
    if (newPosition == null) {
      throw new IllegalArgumentException();
    }
    this.position = newPosition;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Circle)) {
      return false;
    }
    Rectangle rec = (Rectangle) other;
    return rec.name.equals(this.name);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public void draw(Graphics2D g) {
    int posX = (int) Math.ceil(this.position.getX());
    int posY = (int) Math.ceil(this.position.getY());
    int width = (int) Math.ceil(this.width);
    int height = (int) Math.ceil(this.height);

    g.setColor(new Color(this.r, this.g, this.b));
    g.fillRect(posX, posY, width, height);
  }

  @Override
  public String svgFormat() {
    return "<rect id=\"" + this.name + "\" x=\"" + (int) this.position.getX() + "\" y=\""
        + (int) this.position.getY()
        + "\" width=\""
        + (int) this.width + "\" height=\"" + (int) this.height + "\" fill=\"rgb(" + this.r + ","
        + this.g + "," + this.b + ")\"/>";
  }

  @Override
  public void reset() {
    this.position = defPosition;
    this.width = defWidth;
    this.height = defHeight;
    this.r = defR;
    this.b = defB;
    this.g = defG;
  }

  @Override
  public void addState(State s) {
    loStates.add(s);
  }

  /**
   * Set the fields of this Rectangle.
   * @param r        the red color val
   * @param g        the green color val
   * @param b        the blue color val
   * @param position the position value
   * @param xRadius  the radius of the rectangle in the x direction
   * @param yRadius  the radius of the rectangle in the y direction
   */
  public void setDefs(int r, int g, int b, Position position, double xRadius, double yRadius) {
    defR = r;
    defB = b;
    defG = g;
    defPosition = position;
    defWidth = xRadius;
    defHeight = yRadius;
  }

  @Override
  public void render(int tick, Graphics g, Point p) {
    State s = null;
    for (int i = 0; i < loStates.size(); i++) {
      if (tick == loStates.get(i).getTick()) {
        s = loStates.get(i);
      }
    }
    if (s == null) {
      s = loStates.get(loStates.size() - 1);
    }
    int posX = (int) Math.ceil(s.getPosition().getX());
    int posY = (int) Math.ceil(s.getPosition().getY());
    int width = (int) Math.ceil(s.getWidth());
    int height = (int) Math.ceil(s.getHeight());

    g.setColor(new Color(s.getRCol(),
        s.getGCol(), s.getBCol()));
    g.fillRect(posX, posY, width, height);
  }

  @Override
  public String toPlaintext(int tps) {
    String str = "";
    for (int i = 0; i < loStates.size(); i++) {
      str += "Motion: " + this.name + " " + loStates.get(i).write(tps);
      str += "\n";
    }
    return str;
  }

  @Override
  public String toSVG(int tps, Point p) {
    return "<rect id=\"" + this.name + "\" x=\"" + (int) this.position.getX() + "\" y=\""
        + (int) this.position.getY()
        + "\" width=\""
        + (int) this.width + "\" height=\"" + (int) this.height + "\" fill=\"rgb(" + this.r + ","
        + this.g + "," + this.b + ")\"/>";
  }
}
