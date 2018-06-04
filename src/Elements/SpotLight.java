package Elements;

import Primitives.Point3D;
import Primitives.Vector;

import java.awt.*;

public class SpotLight extends PointLight {

    private Vector _direction;
    // ***************** Constructor ********************** //
    public SpotLight(Color color, Point3D position, Vector direction,
                     double kc, double kl, double kq) throws Exception {
        super(color, position, kc, kl, kq);
        _direction = direction;
        _direction.normalize();

    }
    // ***************** Getters/Setters ********************** //
    public Color getIntensity(Point3D point) throws Exception {

        Color pointLightColor = super.getIntensity(point);

        Vector l = super.getL(point);
        l.normalize();

        double k = _direction.dotProduct(l);
        if (k>1)
            k=1;
        return new Color(((int)(pointLightColor.getRed()* k)%256),
                ((int)(pointLightColor.getGreen() * k))%256,
                ((int)(pointLightColor.getBlue()  * k))%256);
    }
}
