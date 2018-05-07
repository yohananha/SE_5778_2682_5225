package Geometrics;

import Primitives.*;

import java.util.List;


public class Plane extends Geometry {

    private Vector _normal;
    private Point3D _Q;

    // ***************** Constructors ********************** //
    public Plane() {
        _normal = new Vector();
        _Q = new Point3D();
    }

    public Plane(Plane plane) {
        _normal = new Vector(plane.getNormal());
        _Q = new Point3D(plane.getQ());
    }

    public Plane(Vector normal, Point3D q) {
        _normal = new Vector(normal);
        _Q = new Point3D(q);
    }

    // ***************** Getters/Setters ********************** //
    public Vector getNormal() {
        return this._normal;
    }

    public Point3D getQ() {
        return this._Q;
    }

    public void setNormal(Vector normal) {
        this._normal = normal;
    }

    public void setQ(Point3D d) {
        this._Q = d;
    }

    // ***************** Operations ******************** //
    public Vector getNormal(Point3D point);

    public List<Point3D> FindIntersections(Ray ray);

}
