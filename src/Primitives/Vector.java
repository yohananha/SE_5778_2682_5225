package Primitives;

public class Vector {
    private Point3D _head;
    // ***************** Constructors ********************** //
    public Vector();
    public Vector(Point3D head);
    public Vector(Vector vector);
    public Vector(double xHead, double yHead, double zHead);
    public Vector(Point3D p1, Point3D p2);
    // ***************** Getters/Setters ********************** //
    public Point3D getHead()
    public void setHead(Point3D head);
    // ***************** Administration ******************** //
    public int compareTo(Vector vector);
    public String toString();
    // ***************** Operations ******************** //
    public void add (Vector vector );
    public void subtract (Vector vector);
    public void scale(double scalingFactor);
    public Vector crossProduct(Vector vector);
    public double length();
    public void normalize(); // Throws exception if length = 0
    public double dotProduct(Vector vector);
}
