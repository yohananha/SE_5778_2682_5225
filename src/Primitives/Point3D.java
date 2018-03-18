package Primitives;

public class Point3D {
    private Coordinate _z;
    // ***************** Constructors ********************** //
    public Point3D();
    public Point3D(Coordinate x, Coordinate y, Coordinate z);
    public Point3D(double x, double y, double z);
    public Point3D(Point3D point3D);
    // ***************** Getters/Setters ********************** //
    public Coordinate getZ();
    public void setZ(Coordinate _z);
    // ***************** Administration ******************** //
    public int compareTo(Point3D point3D);
    public String toString();
    // ***************** Operations ******************** //
    public void add(Vector vector);
    public void subtract(Vector vector);
    public double distance(Point3D point);
}
