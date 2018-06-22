package Elements;

import Primitives.Point3D;
import Primitives.Vector;

import java.awt.*;

public class DirectionalLight extends Light implements LightSource {

    private Vector _direction;
    // ***************** Constructors ********************** //
    public DirectionalLight(Color color, Vector direction) throws Exception {
        _color = color;
        _direction = direction;
        direction.normalize();
    }
    // ***************** Getters/Setters ********************** //


    public Color getIntensity() {
        return _color;
    }
    /*************************************************
     * FUNCTION
     * getIntensity
     * PARAMETERS
     * Point3D
     * RETURN VALUE
     * LColor
     * MEANING
     * This functions get a point and return the color in the same area eithou any change
     * SEE ALSO
     * Geomerty-> FindIntersections
     /*********************************************/
    public Color getIntensity(Point3D point){
        return getIntensity();
    }

    public Vector getDirection(){ return _direction; }

    public void setDirection(Vector _direction){ this._direction = _direction;}

    public Vector getL(Point3D point) throws Exception {
        _direction.normalize();
        return _direction;
    }
}
