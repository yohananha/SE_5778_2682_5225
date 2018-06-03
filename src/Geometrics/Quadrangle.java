package Geometrics;


import Primitives.Point3D;

public class Quadrangle extends Triangle{
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

}









