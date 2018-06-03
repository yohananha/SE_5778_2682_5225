package Elements;

import Primitives.Point3D;
import Primitives.Vector;

import java.awt.*;

public interface LightSource {
    public Color getIntensity(Point3D point3D) throws Exception;

    public Vector getL(Point3D point3D);
}
