package Primitives;

public class Vector{
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
        _head = new Point3D(p2._x.getCoordinate() - p1._x.getCoordinate() ,
                            p2._y.getCoordinate() - p1._y.getCoordinate() ,
                            p2._z.getCoordinate() - p1._z.getCoordinate() );
    }
    // ***************** Getters/Setters ********************** //
    public Point3D getHead(){
        return _head;
    }
    public void setHead(Point3D head)
    {
        this._head = head;
    };
    // ***************** Administration ******************** //
    public int compareTo(Vector vector){
        return _head.compareTo(vector._head);
    };
    public String toString()
    {
        return _head.toString();
    };
    // ***************** Operations ******************** //
    public void add (Vector vector )
    {
        _head.add(vector);
    };
    public void subtract (Vector vector)
    {
        _head.subtract(vector);
    };
    public void scale(double scalingFactor)
    {
        _head._x.setCoordinate(_head.getX().getCoordinate()*scalingFactor);
        _head._y.setCoordinate(_head.getY().getCoordinate()*scalingFactor);
        _head._z.setCoordinate(_head.getZ().getCoordinate()*scalingFactor);
    };
    public Vector crossProduct(Vector vector)
    {
        Vector vector1 = new Vector(_head._y.getCoordinate()*vector._head.getZ().getCoordinate()-_head._z.getCoordinate()*vector._head.getY().getCoordinate(),
                                    _head._x.getCoordinate()*vector._head.getZ().getCoordinate()-_head._z.getCoordinate()*vector._head.getX().getCoordinate()*-1,
                                    _head._x.getCoordinate()*vector._head.getY().getCoordinate()-_head._y.getCoordinate()*vector._head.getX().getCoordinate());

        return vector1;
    };
    public double length()
    {
        Point3D point = new Point3D(0,0,0);
        return  _head.distance(point);
    };
    public void normalize() throws Exception
    {
        try {
            double vecLen = length();
            if (vecLen ==0)
                throw new Exception("0");
            _head._x.setCoordinate(_head.getX().getCoordinate()/vecLen);
            _head._y.setCoordinate(_head.getY().getCoordinate()/vecLen);
            _head._z.setCoordinate(_head.getZ().getCoordinate()/vecLen);
        }
        catch (Exception ex) {
            return;
        }
    }; // Throws exception if length = 0
    public double dotProduct(Vector vector)
    {
        return (_head.getX().getCoordinate()*vector._head.getX().getCoordinate()+
                _head.getY().getCoordinate()*vector._head.getY().getCoordinate()+
                _head.getZ().getCoordinate()*vector._head.getZ().getCoordinate());
    };
}
