package Elements;

import Primitives.Point3D;
import Primitives.Vector;

import java.awt.*;

public class DirectionalLight extends Light implements LightSource {

    private Vector _direction;
    // ***************** Constructors ********************** //
    public DirectionalLight(Color color, Vector direction){
        _color = color;
        _direction = direction;
    }
    // ***************** Getters/Setters ********************** //


    public Color getIntensity() {
        return _color;
    }

    public Color getIntensity(Point3D point){
        return getIntensity();
    }
    public Vector getDirection(){ return _direction; }

    public void setDirection(Vector _direction){ this._direction = _direction;}

    public Vector getL(Point3D point){
        return _direction;
    }
}
