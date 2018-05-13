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

        double L = new Vector(this._center.getX().getCoordinate() - ray.getPOO().getX().getCoordinate(),
                this._center.getY().getCoordinate() - ray.getPOO().getY().getCoordinate(),
                this._center.getZ().getCoordinate() - ray.getPOO().getZ().getCoordinate()).length();

        ray.getDirection().normalize();
        Vector V = new Vector(ray);
        ray.getDirection().scale(L);
        double tm = ray.length();

        double d = sqrt(L*L - tm*tm);

        if(d > this._radius)
            return ListInter;

        double th = sqrt(this._radius*this._radius - d*d);

        double t1 = tm - th;
        double t2 = tm + th;

        if(t1>0)
        {
            V.normalize();
            V.scale(t1);
            ListInter.add(new Point3D(ray.getPOO().getX().getCoordinate() + V.getHead().getX().getCoordinate(),
                    ray.getPOO().getY().getCoordinate() + V.getHead().getY().getCoordinate(),
                    ray.getPOO().getZ().getCoordinate() + V.getHead().getZ().getCoordinate()));
        }

        if(t2>0)
        {
            V.normalize();
            V.scale(t2);
            ListInter.add(new Point3D(ray.getPOO().getX().getCoordinate() + V.getHead().getX().getCoordinate(),
                    ray.getPOO().getY().getCoordinate() + V.getHead().getY().getCoordinate(),
                    ray.getPOO().getZ().getCoordinate() + V.getHead().getZ().getCoordinate()));
        }

        return ListInter;
    };

    public Vector getNormal(Point3D point) throws Exception {
        Vector vec =  new Vector(_center.getX().getCoordinate()-point.getX().getCoordinate(),
                                    _center.getY().getCoordinate()-point.getY().getCoordinate(),
                                    _center.getZ().getCoordinate()-point.getZ().getCoordinate());
        vec.normalize();

        return vec;
    }

}
