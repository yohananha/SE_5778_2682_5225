package Elements;

import Primitives.*;

import java.util.Map;

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

    };

    public Camera(Camera camera);

    public Camera(Point3D P0, Vector vUp, Vector vTo);

    public Camera(Map<String, String> attributes);

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

  /*  public Vector get_vUp();

    public void set_vUp(Vector vUp);

    public Vector get_vTo();

    public void set_vTo(Vector vTo);

    public Point3D getP0 ();

    public void setP0(Point3D P0);*/

    // ***************** Operations ******************** //
    public Ray constructRayThroughPixel(int Nx, int Ny, double x, double y, double screenDist, double screenWidth, double screenHeight);
}
