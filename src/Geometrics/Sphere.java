package Geometrics;

import Primitives.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

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
    public List<Point3D> FindIntersections(Ray ray)
    {

    };

    public Vector getNormal(Point3D point) throws Exception {
        Vector vec =  new Vector(_center.getX().getCoordinate()-point.getX().getCoordinate(),
                                    _center.getY().getCoordinate()-point.getY().getCoordinate(),
                                    _center.getZ().getCoordinate()-point.getZ().getCoordinate());
        vec.normalize();

        return vec;
    }

}
