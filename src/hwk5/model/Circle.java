package hwk5.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Circle class representing a circular shape and its associated methods.
 */
public class Circle implements IShapes {

  private final String name;
  private int rColor;
  private int bColor;
  private int gColor;
  private Position position;
  private double xRadius;
  private double yRadius;
  private int defR;
  private int defB;
  private int defG;
  private Position defPos;
  private double defXRad;
  private double defYRad;
  private List<State> loStates;

  /**
   * Rectangle constructor for creating a circular shape.
   *
   * @param r        the red color val of the circular shape
   * @param g        the green color val of the circular shape
   * @param b        the blue color val of the circular shape
   * @param position the position of the circular shape
   * @param xRadius  the width radius of the circular shape
   * @param yRadius  the height radius of the circular shape
   * @param name     the name of the circular shape
   */
  public Circle(int r, int g, int b, Position position, double xRadius, double yRadius,
      String name) {
    this.rColor = r;
    this.gColor = g;
    this.bColor = b;
    this.position = position;
    this.xRadius = xRadius;
    this.yRadius = yRadius;
    this.name = name;
    this.defPos = position;
    this.defXRad = xRadius;
    this.defYRad = yRadius;
    this.defR = r;
    this.defB = b;
    this.defG = g;
    this.loStates = new ArrayList<State>();
  }

  @Override
  public void changeWidth(double w) throws IllegalArgumentException {
    if (w < 0) {
      throw new IllegalArgumentException();
    }
    this.xRadius = w;
  }

  @Override
  public double getHeight() {
    return this.yRadius;
  }

  @Override
  public int getRCol() {
    return this.rColor;
  }

  @Override
  public int getGCol() {
    return this.gColor;
  }

  @Override
  public int getBCol() {
    return this.bColor;
  }

  @Override
  public void changeHeight(double h) throws IllegalArgumentException {
    if (h < 0) {
      throw new IllegalArgumentException();
    }
    this.yRadius = h;
  }

  @Override
  public double getWidth() {
    return this.xRadius;
  }

  @Override
  public void changeColor(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0 || r > 256 || g > 256 || b > 256) {
      throw new IllegalArgumentException();
    }
    this.rColor = r;
    this.gColor = g;
    this.bColor = b;
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
    Circle cir = (Circle) other;
    return cir.name.equals(this.name);
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
    int width = (int) Math.ceil(this.xRadius);
    int height = (int) Math.ceil(this.yRadius);

    g.setColor(new Color(this.rColor, this.gColor, this.bColor));
    g.fillOval(posX, posY, width, height);
  }

  @Override
  public String svgFormat() {
    return "  <circle x= \"" + (int) this.position.getX() + "\" y=\"" + (int) this.position.getY()
        + "\" width=\"" + (int) this.getWidth() + "\" height=\"" + (int) this.getHeight()
        + "\" fill=\"rgb(" + this.rColor + ","
        + this.gColor + "," + this.bColor + ")\"/>";
  }

  @Override
  public void setDefs(int r, int g, int b, Position position, double xRadius, double yRadius) {
    defR = r;
    defB = b;
    defG = g;
    defPos = position;
    defXRad = xRadius;
    defYRad = yRadius;
  }

  @Override
  public void reset() {
    this.rColor = defR;
    this.gColor = defG;
    this.bColor = defB;
    this.position = defPos;
    this.xRadius = defXRad;
    this.yRadius = defYRad;

  }

  @Override
  public void addState(State s) {
    loStates.add(s);
  }

  @Override
  public void render(int tick, Graphics g, Point p) {
    State s = new State(1,1,1,1,new Position(1,1),1,1);
    for (int i = 0; i < loStates.size(); i++) {
      if (tick == loStates.get(i).getTick()) {
        s = loStates.get(i);
      }
    }
    System.out.println(s.write(1));
    int posX = (int) Math.ceil(this.loStates.get(tick).getPosition().getX());
    int posY = (int) Math.ceil(this.loStates.get(tick).getPosition().getY());
    int width = (int) Math.ceil(this.loStates.get(tick).getWidth());
    int height = (int) Math.ceil(this.loStates.get(tick).getHeight());

    g.setColor(new Color(this.loStates.get(tick).getRCol(),
        this.loStates.get(tick).getGCol(), this.loStates.get(tick).getBCol()));
    g.fillOval(posX, posY, width, height);
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
    return "  <circle x= \"" + (int) this.position.getX() + "\" y=\"" + (int) this.position.getY()
        + "\" width=\"" + (int) this.getWidth() + "\" height=\"" + (int) this.getHeight()
        + "\" fill=\"rgb(" + this.rColor + ","
        + this.gColor + "," + this.bColor + ")\"/>";
  }
}
