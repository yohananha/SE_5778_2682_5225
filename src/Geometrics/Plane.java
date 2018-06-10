package Geometrics;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.LinkedList;
import java.util.List;


public class Plane extends Geometry implements FlatGeometry{

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

/*************************************************
 * FUNCTION
 * FindIntersections
 * PARAMETERS
 * ray
 * RETURN VALUE
 * List<Point3D>
 * MEANING
 * This functions computes a all the intersections of specific ray with the plane
 * SEE ALSO
 * Geomerty-> FindIntersections
 /*********************************************/
    public List<Point3D> FindIntersections(Ray ray) {
        List<Point3D> ListInter = new LinkedList<Point3D>();
        Point3D p0 = new Point3D(ray.getPOO());
        Point3D q0 = new Point3D(_Q);
        Vector N = new Vector(this.getNormal());
        Vector V = new Vector(ray.getDirection());

        // For internal use only
        Vector v = new Vector (q0, p0);

        // calculate t:
        double t = (N.dotProduct(v) * -1) / N.dotProduct(V);

        if (t >= 0) {
            V.scale(t);
            p0.add(V);

            ListInter.add(p0);

        }
        return ListInter;
    }
};

