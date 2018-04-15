package Primitives;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class VectorTest {

    @Test
    public void getHead() {
        Vector v = new Vector(1,2,3);
        assertEquals(v.getHead().compareTo(new Point3D(1,2,3)),1,0);
    }

    @Test
    public void setHead() {
        Point3D p = new Point3D(-10.213,2.45,0.3344);
        Vector v = new Vector();
        v.setHead(p);
        assertEquals(v.getHead().compareTo(p),1,0);
    }

    @Test
    public void compareTo() {
        Vector v = new Vector(21.77,45.3,-98.012);
        assertEquals(v.getHead().compareTo(new Point3D(21.77,45.3,-98.012)),1,0);
    }

    @Test
    public void toString1() {
        Vector v = new Vector(3.22456,5.01234,6.9876);
        assertEquals(v.toString(), "(3.22, 5.01, 6.99)");
    }

    @Test
    public void add() {
        Vector v1 = new Vector(3,-4,5.223);
        Vector v2 = new Vector(0,2.33,-5.223);
        v1.add(v2);

        assertEquals(v1.compareTo(new Vector(3,-1.67,0)),1,0);
    }

    @Test
    public void subtract() {
        Vector v1 = new Vector(3,-4,5.223);
        Vector v2 = new Vector(0,2.33,-5.223);
        v1.subtract(v2);

        assertEquals(v1.compareTo(new Vector(3,-6.33,10.446)),1,0);
    }

    @Test
    public void scale() {
        Vector v = new Vector(3,-4,5.2);
        v.scale(-2.11);
        assertEquals(v.compareTo(new Vector(-6.33,8.44,-10.972)),1,0);
    }

    @Test
    public void crossProduct() {
        Vector v1 = new Vector(1,2,0);
        Vector v2 = new Vector(1,2,3);
        Vector v3 = v1.crossProduct(v2);

        assertEquals(1,v3.compareTo(new Vector(6, -3, 0)),0);
    }

    @Test
    public void length() {
        Vector v1 = new Vector(2,-2,1);
        assertEquals(v1.length(),3,0);
    }

    @Test
    public void normalize() throws Exception
    {
        Vector v1 = new Vector(-2,2,1);
        v1.normalize();
        double a = (double)2/3;
        double b = (double)1/3;
        assertEquals(v1.compareTo(new Vector(-a,a,b)),1,0);
    }

    @Test
    public void dotProduct() {
        Vector v1 = new Vector(1,2,0);
        Vector v2 = new Vector(1,2,3);
        double x = v1.dotProduct(v2);
        assertEquals(x,5,0);
    }
}