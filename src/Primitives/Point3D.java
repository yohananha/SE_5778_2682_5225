package Primitives;

import static java.lang.Math.sqrt;

public class Point3D extends Point2D {


    protected Coordinate _z;
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
    public Point3D(Point3D point3D)
    {
        _x = new Coordinate(point3D._x);
        _y = new Coordinate(point3D._y);
        _z = new Coordinate(point3D._z);
    };
    // ***************** Getters/Setters ********************** //
    public Coordinate getZ() {
        return _z;
    }

    public void set_z(Coordinate _z) {
        this._z = _z;
    }

     // ***************** Administration ******************** //
    public int compareTo(Point3D point3D)
    {
        if(this._x.compareTo(point3D._x) == 1 &&
                this._y.compareTo(point3D._y) == 1 &&
                this._z.compareTo(point3D._z) == 1)
            return 1;
        else return 0;
    };
    @Override
    public String toString()
    {
       return "(" + _x + ", " + _y + ", " + _z + ")" ;
    };
    // ***************** Operations ******************** //
    public void add(Vector vector)
    {
        _x.add(vector.getHead().getX());
        _y.add(vector.getHead().getY());
        _z.add(vector.getHead().getZ());
    };
    public void subtract(Vector vector)
    {
        _x.subtract(vector.getHead().getX());
        _y.subtract(vector.getHead().getY());
        _z.subtract(vector.getHead().getZ());
    };

    //==help function to perform power of doubles
    private double pow (double power)
    {
        return power*power;
    }

    public double distance(Point3D point)
    {
        return sqrt(pow(getX().getCoordinate() - point.getX().getCoordinate() ) +
                    pow(getY().getCoordinate() - point.getY().getCoordinate() ) +
                    pow(getZ().getCoordinate() - point.getZ().getCoordinate() ) );
    }
}
