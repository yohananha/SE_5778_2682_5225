package Geometrics;

import Primitives.*;
import Elements.Camera;
import java.util.List;

public class Triangle extends Geometry {

    private Point3D _p1;
    private Point3D _p2;
    private Point3D _p3;

    // ***************** Constructors ********************** //
    public Triangle() {
        _p1 = new Point3D();
        _p2 = new Point3D();
        _p3 = new Point3D();
    }

    public Triangle(Triangle triangle) {
        _p1 = new Point3D(triangle.getP1());
        _p2 = new Point3D(triangle.getP2());
        _p3 = new Point3D(triangle.getP3());
    }

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        _p1 = new Point3D(p1);
        _p2 = new Point3D(p2);
        _p3 = new Point3D(p3);
    }

    // ***************** Getters/Setters ********************** //
    public Point3D getP1() {
        return this._p1;
    }

    public Point3D getP2() {
        return this._p2;
    }

    public Point3D getP3() {
        return this._p3;
    }

    public void setP1(Point3D p1) {
        this._p1 = p1;
    }

    public void setP2(Point3D p2) {
        this._p2 = p2;
    }

    public void setP3(Point3D p3) {
        this._p3 = p3;
    }

    // ***************** Operations ******************** //
    public Vector getNormal(Point3D point) {

        // Setting the U vector
        Vector vecU = new Vector(_p1.getX().getCoordinate()-_p2.getX().getCoordinate(),
                                 _p1.getY().getCoordinate()-_p2.getY().getCoordinate(),
                                 _p1.getZ().getCoordinate()-_p2.getZ().getCoordinate());
        // Setting the V vector
        Vector vecV = new Vector(_p1.getX().getCoordinate()-_p3.getX().getCoordinate(),
                                 _p1.getY().getCoordinate()-_p3.getY().getCoordinate(),
                                 _p1.getZ().getCoordinate()-_p3.getZ().getCoordinate());

        // Finding the normal vector and normalize it
        Vector _normal = new Vector(vecU.crossProduct(vecV)).normalize();
        _normal.scale(-1);

        return _normal;
    }

    ;

    public List<Point3D> FindIntersections(Ray ray)
    {

    };

}
