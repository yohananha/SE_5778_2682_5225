package Primitives;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Point3DTest {

    @Test
    public void getZ() {
        Point3D p = new Point3D();
        assertEquals(0,p.getZ().getCoordinate(),0);
    }


}