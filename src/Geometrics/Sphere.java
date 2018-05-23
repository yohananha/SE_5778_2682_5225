package Geometrics;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.sqrt;

public class Sphere extends Geometry {

    private Point3D _center;
    private double _radius;

    // ***************** Constructors ********************** //
    public Sphere() {
        _center = new Point3D();
        _radius = 0;
    }

    public Sphere(Sphere sphere) {
        this._center = sphere.getCenter();
        this._radius = sphere.getRadius();
    }

    public Sphere(double radius, Point3D center) {
        this._center = center;
        this._radius = radius;
    }

//    public Sphere(Map<String, String> attributes);

    // ***************** Getters/Setters ********************** //
    public Point3D getCenter() {
        return this._center;
    }

    public double getRadius() {
        return this._radius;
    }

    public void setRadius(double radius) {
        this._radius = radius;
    }

    public void setCenter(Point3D center) {
        this._center = center;
    }

    // ***************** Operations ******************** //
    public List<Point3D> FindIntersections(Ray ray) throws Exception {

        List<Point3D> ListInter = new LinkedList<Point3D>();
        // 1. Define the L vector
        Vector L = new Vector(ray.getPOO(), this.getCenter());

        // 2. Find the tm
        double tm = L.dotProduct(ray.getDirection());

        // 3. Find d
        double d = Math.sqrt(Math.pow(L.length(), 2) - Math.pow(tm, 2));

        // 4. Case 1 - No Intersections
        if(d > this._radius)
            return ListInter;

        // 5. find TH - Half of the ray insde the sphere
        double th = sqrt(this._radius*this._radius - d*d);

        // 6. Define t1 t2 - Two parts of the th line
        double t1 = tm - th;
        double t2 = tm + th;

        // 7. Case 2 - there's at least ons intersection
        if(t1>0)
        {
            Vector V = new Vector(ray.getDirection());
            V.scale(t1);
            Point3D P1 = new Point3D(ray.getPOO());
            P1.add(V);
            ListInter.add(P1);
        }

        if(t2>0)
        {
            Vector V = new Vector(ray.getDirection());
            V.scale(t2);
            Point3D P2 = new Point3D(ray.getPOO());
            P2.add(V);
            ListInter.add(P2);
        }

        return ListInter;
    };

    public Vector getNormal(Point3D point) throws Exception {
        Vector vec =  new Vector(_center,point);
        vec.normalize();

        return vec;
    }

}
