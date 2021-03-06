package Geometrics;


import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.LinkedList;
import java.util.List;

public class Quadrangle extends Geometry implements FlatGeometry{
    private Triangle  _tri1;
    private Triangle _tri2;

    // Constructor
    public Quadrangle(Point3D P1, Point3D P2, Point3D P3, Point3D P4)
    {
        _tri1 = new Triangle(P1, P2, P4);
        _tri2 = new Triangle(P2, P3, P4);
    }

    // Getters/Setters
    public Point3D getP1(){return _tri1.getP1();}

    public Point3D getP2(){return _tri1.getP2();}

    public Point3D getP3(){return _tri2.getP2();}

    public Point3D getP4(){return _tri1.getP3();}

    public void setP1(Point3D point){_tri1.setP1(point);}
    public void setP2(Point3D point){_tri1.setP2(point);
                                     _tri2.setP1(point);}
    public void setP3(Point3D point){_tri2.setP2(point);}
    public void setP4(Point3D point){_tri1.setP3(point);
                                     _tri2.setP3(point);}
    /*************************************************
     * FUNCTION
     * getNormal
     * PARAMETERS
     * Point3D point
     * RETURN VALUE
     * Vector who is the normal to the point
     * MEANING
     * This functions computes a normal vector into specific point
     * SEE ALSO
     * Vector->Normalize
     * Triangle -> getNormal
     **************************************************/
    public Vector getNormal(Point3D point) throws Exception
    {
         return _tri1.getNormal(point);
    }

    /*************************************************
     * FUNCTION
     * FindIntersections
     * PARAMETERS
     * ray
     * RETURN VALUE
     * List<Point3D>
     * MEANING
     * This functions computes a all the intersections of specific ray with the triangle
     * SEE ALSO
     * Geomerty-> FindIntersections
     **************************************************/
    public List<Point3D> FindIntersections(Ray ray) throws Exception
    {
        List<Point3D> ListInter1 = new LinkedList<Point3D>();
        List<Point3D> ListInter2 = new LinkedList<Point3D>();

        ListInter1= _tri1.FindIntersections(ray);
        ListInter2= _tri2.FindIntersections(ray);

         ListInter1.addAll(ListInter2);
         return ListInter1;
    }
}









