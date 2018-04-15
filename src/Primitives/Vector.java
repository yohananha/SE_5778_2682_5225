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
    }

    // ***************** Administration ******************** //
    /*************************************************
     * FUNCTION
     * compareTo
     * PARAMETERS
     * vector
     * RETURN VALUE
     * 0/1
     * MEANING
     * This functions compare between two vectors to check if they are equals
     * If eqauls returns 1
     * Else return 0
     * The function uses the compareTo functions of the point of the vectors
     * SEE ALSO
     * Point3D -> comppareTo
     **************************************************/
    public int compareTo(Vector vector){
        return _head.compareTo(vector._head);
    }

    /*************************************************
     * FUNCTION
     * toString
     * PARAMETERS
     * none
     * RETURN VALUE
     * Format string of coordinate
     * MEANING
     * This function return the head's coordinate by format that declared before
     **************************************************/
    @Override
    public String toString()
    {
        return _head.toString();
    }

    // ***************** Operations ******************** //

    /*************************************************
     * FUNCTION
     * add
     * PARAMETERS
     * vector
     * RETURN VALUE
     * none
     * MEANING
     * This function add two points, by taking the parameters from vectors' point
     * The function uses the point3D's add
     * SEE ALSO
     * point3D -> add
     **************************************************/
    public void add (Vector vector )
    {
        _head.add(vector);
    }

    /*************************************************
     * FUNCTION
     * subtract
     * PARAMETERS
     * vector
     * RETURN VALUE
     * none
     * MEANING
     * This function subtract two points, by taking the parameters from vectors' point
     * The function uses the point3D's substract
     * SEE ALSO
     * point3D -> substract
     ***************************************************/
    public void subtract (Vector vector)
    {
        _head.subtract(vector);
    }

    /*************************************************
     * FUNCTION
     * scale
     * PARAMETERS
     * double
     * RETURN VALUE
     * none
     * MEANING
     * This function scaling the vector by miltiply each component by the scaling factor
     **************************************************/
    public void scale(double scalingFactor)
    {
        _head._x.setCoordinate(_head.getX().getCoordinate()*scalingFactor);
        _head._y.setCoordinate(_head.getY().getCoordinate()*scalingFactor);
        _head._z.setCoordinate(_head.getZ().getCoordinate()*scalingFactor);
    }

    /*************************************************
     * FUNCTION
     * crossProduct
     * PARAMETERS
     * vector
     * RETURN VALUE
     * vector
     * MEANING
     * This function aplly the cross product of two 3D vectors
     **************************************************/
    public Vector crossProduct(Vector vector)
    {
        Vector vector1 = new Vector(_head._y.getCoordinate()*vector._head.getZ().getCoordinate()-_head._z.getCoordinate()*vector._head.getY().getCoordinate(),
                                    _head._z.getCoordinate()*vector._head.getX().getCoordinate()-_head._x.getCoordinate()*vector._head.getZ().getCoordinate(),
                                    _head._x.getCoordinate()*vector._head.getY().getCoordinate()-_head._y.getCoordinate()*vector._head.getX().getCoordinate());

        return vector1;
    }

    /*************************************************
     * FUNCTION
     * length
     * PARAMETERS
     * none (This vector)
     * RETURN VALUE
     * double
     * MEANING
     * This function calculate the length of a vector
     * The function uses the distance method between the head and the (0,0,0) point
     * SEE aLSO
     * point3D -> distance
     **************************************************/
    public double length()
    {
        Point3D point = new Point3D(0,0,0);
        return  _head.distance(point);
    }

    /*************************************************
     * FUNCTION
     * normalize
     * PARAMETERS
     * none
     * RETURN VALUE
     * none
     * MEANING
     * This function normalize the vector
     * Normilzaton - Divide each component by the vector length
     * The function throws exception if the length = 0
     * SEE ALSO
     * length
     **************************************************/
    public void normalize() throws Exception
    {
            double vecLen = length();
            if (vecLen ==0)
                throw new ArithmeticException("0");
            _head._x.setCoordinate(_head.getX().getCoordinate()/vecLen);
            _head._y.setCoordinate(_head.getY().getCoordinate()/vecLen);
            _head._z.setCoordinate(_head.getZ().getCoordinate()/vecLen);

            return;

    }

    /*************************************************
     * FUNCTION
     * dotProduct
     * PARAMETERS
     * vector
     * RETURN VALUE
     * double
     * MEANING
     * This function return the value of dot product between two vectors
     * Dot product - multiply each component with his equal
     **************************************************/
    public double dotProduct(Vector vector)
    {
        return (_head.getX().getCoordinate()*vector._head.getX().getCoordinate()+
                _head.getY().getCoordinate()*vector._head.getY().getCoordinate()+
                _head.getZ().getCoordinate()*vector._head.getZ().getCoordinate());
    };
}
