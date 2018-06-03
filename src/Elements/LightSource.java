package Elements;

import Primitives.Point3D;
import Primitives.Vector;
public interface LightSource {
    public Light getIntensity(Point3D point3D);

    public Vector getL(Point3D point3D);
}
