package Geometrics;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.LinkedList;
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
    public Vector getNormal(Point3D point) throws Exception {

        // Setting the U vector
        Vector vecU = new Vector(_p1.getX().getCoordinate()-_p2.getX().getCoordinate(),
                                 _p1.getY().getCoordinate()-_p2.getY().getCoordinate(),
                                 _p1.getZ().getCoordinate()-_p2.getZ().getCoordinate());
        // Setting the V vector
        Vector vecV = new Vector(_p1.getX().getCoordinate()-_p3.getX().getCoordinate(),
                                 _p1.getY().getCoordinate()-_p3.getY().getCoordinate(),
                                 _p1.getZ().getCoordinate()-_p3.getZ().getCoordinate());

        // Finding the normal vector and normalize it
        Vector _normal = new Vector(vecU.crossProduct(vecV));
        _normal.normalize();
        _normal.scale(-1);

        return _normal;
    }

    ;

    public List<Point3D> FindIntersections(Ray ray) throws Exception
    {
        List<Point3D> ListInter = new LinkedList<Point3D>();

        //find P0
        Point3D P0 = ray.getPOO();

        //find the triangle's Normal
        Vector N = this.getNormal(this.getP1());

        //find Plane
        Plane plane = new Plane(N, this.getP3());

        //if the intersection between the ray and plane
        List<Point3D> ListIntersectionPlane = plane.FindIntersections(ray);

        if(ListIntersectionPlane.isEmpty())
            return ListInter;

        Point3D intersectionPlane = ListIntersectionPlane.get(0);

        //defin Vector P_P0
        Vector P_P0 = new Vector(P0, intersectionPlane);

        //checking side 1-2
        Vector V1_1 = new Vector(P0, this._p1);
        Vector V2_1 = new Vector(P0, this._p2);
        Vector N1 = V1_1.crossProduct(V2_1);
        N1.normalize();
        double S1 = -P_P0.dotProduct(N1);

        //checking side 2-3
        Vector V1_2 = new Vector(P0, this._p2);
        Vector V2_2 = new Vector(P0, this._p3);
        Vector N2 = V1_2.crossProduct(V2_2);
        N2.normalize();
        double S2 = -P_P0.dotProduct(N2);

        //checking side 3-1
        Vector V1_3 = new Vector(P0, this._p3);
        Vector V2_3 = new Vector(P0, this._p1);
        Vector N3 = V1_3.crossProduct(V2_3);
        N3.normalize();
        double S3 = -P_P0.dotProduct(N3);

        if (((S1 > 0) && (S2 > 0) && (S3 > 0)) || ((S1 < 0) && (S2 < 0) && (S3 < 0)))
            ListInter.add(intersectionPlane);


        return ListInter;




    }

}
