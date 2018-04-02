package Primitives;

public class Point2D {

    protected Coordinate _x;
    protected Coordinate _y;
    // ***************** Constructors ********************** //
    public Point2D()
    {
        _x = new Coordinate();
        _y = new Coordinate();
    }
    public Point2D(Coordinate x, Coordinate y)
    {
        _x = x;
        _y = y;
    }
    public Point2D(Point2D point2D)
    {
        _x = point2D.getX();
        _y = point2D.getY();
    }
    // ***************** Getters/Setters ********************** //
    public Coordinate getX() {
        return _x;
    }
    public void setX(Coordinate _x) {
        this._x = _x;
    }

    public Coordinate getY() {
        return _y;
    }
    public void setY(Coordinate _y) {
        this._y = _y;
    }


    // ***************** Administration ******************** //
    public int compareTo(Point2D point2D)
    {
        if(_x.compareTo(point2D._x) == 1 && _y.compareTo(point2D._y) == 1)
            return 1;
        else return 0;
    };
}
