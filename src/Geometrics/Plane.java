package Geometrics;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.LinkedList;
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
    public Vector getNormal(Point3D point) {
        return _normal;
    }

    public List<Point3D> FindIntersections(Ray ray) {
        List<Point3D> ListInter = new LinkedList<Point3D>();
        Vector p0 = new Vector(ray.getPOO());
        Point3D q0 = new Point3D(_Q);
        Vector N = new Vector(_normal);
        Vector V = new Vector(ray.getDirection());

        // For internal use only
        Vector intVec = new Vector();

        Vector v = new Vector(q0);

        // calculate t:
        intVec = N;
        intVec.scale(-1);
        Vector Nminus = new Vector(intVec);

        intVec = p0;
        intVec.subtract(new Vector(q0));
        Vector pq0 = new Vector(intVec);

        double nv = V.dotProduct(N);
        pq0.scale(1 / nv);
        double t = Nminus.dotProduct(pq0);

        if (t >= 0) {
            V.scale(t);
            p0.add(V);

            ListInter.add(p0.getHead());

        }
        return ListInter;
    }
};

