package Primitives;

public class Point3D extends Point2D {


    private Coordinate _z;
    // ***************** Constructors ********************** //
    public Point3D()
    {
        _x = new Coordinate();
        _y = new Coordinate();
        _z = new Coordinate();
    }
    public Point3D(Coordinate x, Coordinate y, Coordinate z)
    {
        _x = x;
        _y = y;
        _z = z;
    }
    public Point3D(double x, double y, double z)
    {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }
    public Point3D(Point3D point3D);
    // ***************** Getters/Setters ********************** //
    public Coordinate get_z() {
        return _z;
    }

    public void set_z(Coordinate _z) {
        this._z = _z;
    }
    public Coordinate getZ() {
        return _z;
    }

    public void setZ(Coordinate _z) {
        this._z = _z;
    }

    // ***************** Administration ******************** //
    public int compareTo(Point3D point3D);
    public String toString();
    // ***************** Operations ******************** //
    public void add(Vector vector)
    {

    };
    public void subtract(Vector vector);
    public double distance(Point3D point);
}
