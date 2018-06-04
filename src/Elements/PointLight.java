package Elements;

import Primitives.Point3D;
import Primitives.Vector;

import java.awt.*;

public class PointLight extends Light implements LightSource {
    Point3D _position;
    double _Kc, _Kl, _Kq;
    // ***************** Constructors ********************** //
    public PointLight(Color color, Point3D position,
                      double kc, double kl, double kq){
        _color = color;
        _position = position;
        _Kc = kc;
        _Kl = kl;
        _Kq = kq;
    }
    // ***************** Getters/Setters ********************** //
    public Color getIntensity(){return _color;}

    public Color getIntensity(Point3D point) throws Exception {

        Color I0 = this.getIntensity();

        double d = _position.distance(point);

        double Il = 1/(_Kc + (_Kl * d) + (_Kq*Math.pow(d,2)));

        return new Color((int)(I0.getRed()*Il),
                (int)(I0.getGreen()*Il),
                (int)(I0.getBlue()*Il));

    }

    public Vector getL(Point3D point) throws Exception {
        Vector l = new Vector(_position, point);
        l.normalize();

        return l;
    }
}
