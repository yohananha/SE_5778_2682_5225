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
        _P0 = new Point3D(camera.get_P0());
        _vUp = new Vector(camera.get_vUp());
        _vTo = new Vector(camera.get_vTo());
        _vRight = new Vector(camera.get_vRight());
    };

    public Camera(Point3D P0, Vector vUp, Vector vTo) throws Exception {
        _P0 = new Point3D(P0);
        _vUp =new Vector(vUp);
        _vTo = new Vector(vTo);
        _vTo.normalize();
        _vUp.normalize();
        _vRight = new Vector(_vUp.crossProduct(vTo));
        _vUp.crossProduct(_vRight);
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
    /*************************************************
     * FUNCTION
     * constructRayThroughPixel
     * PARAMETERS
     * int Nx, int Ny, // Screen size
     * double x, double y, // Point
     * double screenDist, double screenWidth, double screenHeight
     * RETURN VALUE
     * Ray
     * MEANING
     * This function sends ray through pixel into the view plane
     **************************************************/
    public Ray constructRayThroughPixel(int Nx, int Ny, // Screen size
                                        double x, double y, // Point
                                        double screenDist, double screenWidth, double screenHeight) throws Exception {
        // Define image center
        Vector vRight = new Vector(_vRight);
        Vector vUp = new Vector(_vUp);



        Vector tempTo = new Vector(_vTo);
        tempTo.scale(screenDist);
        Point3D _Pc = new Point3D(get_P0());
        _Pc.add(tempTo);

        // Define the Ratio
        double _Rx = screenWidth/Nx;
        double _Ry = screenHeight/Ny;

        // Rays for ray construct
        vRight.scale((x - (Nx/2.0)) * _Rx + 0.5 * _Rx);
        vUp.scale((y - (Ny/2.0)) * _Ry + 0.5 * _Ry);

        vRight.subtract(vUp);
        _Pc.add(vRight);

        Point3D p = new Point3D((_Pc));
         Vector ray =  new Vector(_P0, p);
         ray.normalize();

         return new Ray(p,ray);

    };

}