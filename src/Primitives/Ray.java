package Primitives;

public class Ray {
    // Point of origin
    private Point3D _POO;
    // Ray direction
    private Vector _direction;
    // ***************** Constructors ********************** //
    public Ray()
    {
        _POO = new Point3D();
        _direction = new Vector();
    }

    public Ray(Ray ray)
    {
        _POO = ray.getPOO();
        _direction = ray.getDirection();
    }

    public Ray(Point3D poo, Vector direction)
    {
        _POO = new Point3D(poo);
        _direction = new Vector(direction);
    }

    // ***************** Getters/Setters ********************** //
    public void setPOO(Point3D _POO){this._POO = _POO;}
    public void setDirection(Vector _direction){this._direction = _direction;}
    public Vector getDirection(){return _direction;}
    public Point3D getPOO(){return _POO;}
}
