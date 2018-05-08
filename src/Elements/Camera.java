package Elements;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;


public class Camera {

    // ***************** Administration ********************** //
    public String toString()
    {
        return "Vto: " + _vTo + "\n" + "Vup: " + _vUp + "\n" + "Vright:" + _vRight + ".";
    };


    //Eye point of the camera
    private Point3D _P0;
    private Vector _vUp;
    private Vector _vTo;
    //Should be calculated as the cross product if vUp and vTo
    private Vector _vRight;

    // ***************** Constructors ********************** //
    public Camera()
    {
        _P0 = new Point3D();
        _vUp = new Vector();
        _vTo = new Vector();
        _vRight = new Vector();
    };

    public Camera(Camera camera)
    {
        _P0 = camera._P0;
        _vUp = camera._vUp;
        _vTo = camera._vTo;
        _vRight = camera._vRight;
    };

    public Camera(Point3D P0, Vector vUp, Vector vTo)
    {
        _P0 = P0;
        _vUp = vUp;
        _vTo = vTo;
        _vRight = new Vector(vUp.crossProduct(vTo));
    };

   /// public Camera(Map<String, String> attributes);

    // ***************** Getters/Setters ********************** //
    public Point3D get_P0() {
        return _P0;
    }

    public void set_P0(Point3D _P0) {
        this._P0 = _P0;
    }

    public Vector get_vUp() {
        return _vUp;
    }

    public void set_vUp(Vector _vUp) {
        this._vUp = _vUp;
    }

    public Vector get_vTo() {
        return _vTo;
    }

    public void set_vTo(Vector _vTo) {
        this._vTo = _vTo;
    }

    public Vector get_vRight() {
        return _vRight;
    }

    public void set_vRight(Vector _vRight) {
        this._vRight = _vRight;
    }


    // ***************** Operations ******************** //
    public Ray constructRayThroughPixel(int Nx, int Ny,
                                        double x, double y,
                                        double screenDist, double screenWidth, double screenHeight)
    {
        // Define image center
        Vector tempTo = new Vector(_vTo);
        tempTo.scale(screenDist);
        Point3D _Pc = new Point3D(_P0);
        _Pc.add(tempTo);
        // Define the Ratio
        double _Rx = screenDist/Nx;
        double _Ry = screenHeight/Ny;

        // Rays for ray construct
        Vector vRight = new Vector(_vRight);
        vRight.scale(multiRay(x, Nx, _Rx));

        Vector vUp = new Vector(_vUp);
        vUp.scale(multiRay(y, Ny, _Ry));

        vRight.subtract(vUp);
        vRight.add(_Pc);

        return new Ray(_P0, vRight);


    };

    private double multiRay(double x, int xN, double xR)
    {
       return  ((x-(((double)xN/2))*xR)+(xR/2));
    }
}
