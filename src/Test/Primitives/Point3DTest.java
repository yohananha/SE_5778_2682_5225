package Primitives;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Point3DTest {

    //---------------CTOR TEST-----------------------------------
    @Test
    public void getZ() {
        Point3D p = new Point3D(1,1,1.111);
        assertEquals(1.111,p.getZ().getCoordinate(),0);
    }

    @Test
    public void getZ1() {
        Point3D p = new Point3D();
        assertEquals(0,p.getZ().getCoordinate(),0);
    }

    @Test
    public void getZ2() {
        Coordinate x = new Coordinate(1.13);
        Coordinate y = new Coordinate(1.12);
        Coordinate z = new Coordinate(1.11);
        Point3D p = new Point3D(x,y,z);
        assertEquals(1.11,p.getZ().getCoordinate(),0);
    }

    @Test
    public void getZ3() {
        Point3D p = new Point3D(1,2,1.11);
        Point3D p1 = p;
        assertEquals(1.11,p1.getZ().getCoordinate(),0);
    }

    //-----------------------SET TEST------------------------------

    @Test
    public void set_z1() {
        Point3D p = new Point3D();
        Coordinate z = new Coordinate(1.11);
        p.set_z(z);
        assertEquals(1.11,p.getZ().getCoordinate(),0);
    }

    //-----------------------ComparTo TEST------------------------------
    @Test
    public void compareTo() {
        Point3D p1 = new Point3D(1,1,1);
        Point3D p2 = new Point3D(1,1,1);
        assertEquals(1,p1.compareTo(p2),0);
    }

    //-----------------------toString TEST------------------------------
    @Test
    public void toStringTest() {
        Point3D p = new Point3D(1.111,2.234,3.459);
        String result = p.toString();
        assertEquals("(1.11, 2.34, 3.46)", result);
    }
}