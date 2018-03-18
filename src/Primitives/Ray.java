package Primitives;

public class Ray {
    // Point of origin
    private Point3D _POO;
    // Ray direction
    private Vector _direction;
    // ***************** Constructors ********************** //
    public Ray();
    public Ray(Ray ray);
    public Ray(Point3D poo, Vector direction);
    // ***************** Getters/Setters ********************** //
    public void setPOO(Point3D _POO);
    public void setDirection(Vector _direction);
    public Vector getDirection();
    public Point3D getPOO();
}
