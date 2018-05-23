package Elements;

import Primitives.*;


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
        _P0 = new Point3D(0,0,0);
        _vUp = new Vector(0,1,0);
        _vTo = new Vector(0,0,-1);
        _vRight = new Vector(1,0,0);
    };

    public Camera(Camera camera)
    {
        _P0 = camera._P0;
        _vUp = camera._vUp;
        _vTo = camera._vTo;
        _vRight = camera._vRight;
    };

    public Camera(Point3D P0, Vector vUp, Vector vTo) throws Exception {
        _P0 = P0;
        _vUp = vUp;
        _vTo = vTo;
        _vTo.normalize();
        _vUp.normalize();
        _vRight = new Vector(vUp.crossProduct(vTo));
        _vRight.normalize();

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
    public Ray constructRayThroughPixel(int Nx, int Ny, // Screen size
                                        double x, double y, // Point
                                        double screenDist, double screenWidth, double screenHeight) throws Exception {
        // Define image center
        Vector tempTo = new Vector(_vTo);
        tempTo.scale(screenDist);
        Point3D _Pc = new Point3D(_P0);
        _Pc.add(tempTo);

        // Define the Ratio
        double _Rx = screenWidth/Nx;
        double _Ry = screenHeight/Ny;

        // Rays for ray construct
        Vector vRight = new Vector(_vRight);
        vRight.scale(multiRay(x, Nx, _Rx));

        Vector vUp = new Vector(_vUp);
        vUp.scale(multiRay(y, Ny, _Ry));

        vRight.subtract(vUp);
        vRight.add(_Pc);
         return new Ray(_P0, vRight);


         //---nachum
        //// Calculating P - the intersection point
        //Vector vRight = new Vector(_vRight);
        //Vector vUp = new Vector(_vUp);

        //vRight.scale(((x - (Nx/2.0)) * _Rx + 0.5 * _Rx));
        //vUp. scale(((y - (Ny/2.0)) * _Ry + 0.5 * _Ry));

        //vRight.subtract(vUp);
        //_Pc.add(vRight);

        //Point3D P = new Point3D(_Pc);

        //// constructing ray between P0 and the intersection point
        //Vector ray = new Vector(_P0, P);
        //ray.normalize();

        //// returning the constructed ray
        //return new Ray(P, ray);


    };

    private double multiRay(double x, int xN, double xR)
    {
        return  ((x-(((double)xN/2))*xR)+(xR/2));
    }
}