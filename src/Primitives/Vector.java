package Primitives;

public class Vector {
    private Point3D _head;
    // ***************** Constructors ********************** //
    public Vector()
    {
        _head = new Point3D();
    };
    public Vector(Point3D head)
    {
        _head = head;
    };
    public Vector(Vector vector)
    {
        this._head= vector._head;
    };
    public Vector(double xHead, double yHead, double zHead)
    {
       this._head=  new Point3D(xHead,yHead,zHead);
    };
    public Vector(Point3D p1, Point3D p2)
    {

    };
    // ***************** Getters/Setters ********************** //
    public Point3D getHead(){
        return _head;
    }
    public void setHead(Point3D head)
    {
        this._head = head;
    };
    // ***************** Administration ******************** //
    public int compareTo(Vector vector);
    public String toString();
    // ***************** Operations ******************** //
    public void add (Vector vector )
    {
        this._head+=vector._head;
    };
    public void subtract (Vector vector)
    {
        this._head-=vector._head;
    };
    public void scale(double scalingFactor)
    {

    };
    public Vector crossProduct(Vector vector);
    public double length();
    public void normalize(); // Throws exception if length = 0
    public double dotProduct(Vector vector);
}
