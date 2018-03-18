package Primitives;

public class Point2D {
    protected Coordinate _x;
    protected Coordinate _y;
    // ***************** Constructors ********************** //
    public Point2D();
    public Point2D(Coordinate x, Coordinate y);
    public Point2D(Point2D point2D);
    // ***************** Getters/Setters ********************** //
    public Coordinate getX();
    public Coordinate getY();
    public void setX(Coordinate _x);
    public void setY(Coordinate _y);
    // ***************** Administration ******************** //
    public int compareTo(Point2D point2D);
}
