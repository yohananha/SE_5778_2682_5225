package Primitives;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CoordinateTest {

    @Test
    public void getCoordinate() {
        Coordinate x = new Coordinate();
        assertEquals(0,x.getCoordinate(),0);
    }

    @Test
    public void getCoordinate1() {
        Coordinate x = new Coordinate(2);
        assertEquals(2,x.getCoordinate(),0);
    }

    @Test
    public void getCoordinate2() {
        Coordinate x = new Coordinate(3);
        Coordinate y = new Coordinate(x);
        assertEquals(3,y.getCoordinate(),0);
    }

    @Test
    public void getCoordinate3() {
        Coordinate x = new Coordinate(3);
        Coordinate y = x;
        assertEquals(3,y.getCoordinate(),0);
    }

    @Test
    public void setCoordinate() {
        Coordinate x = new Coordinate();
        x.setCoordinate(3);
        assertEquals(3,x.getCoordinate(), 0);
    }

    @Test
    public void compareTo() {
        Coordinate x = new Coordinate();
        Coordinate y = new Coordinate();
        assertEquals(1, x.compareTo(y), 0);
    }

    @Test
    public void compareTo1() {
        Coordinate x = new Coordinate(1);
        Coordinate y = new Coordinate();
        assertEquals(0, x.compareTo(y), 0);

    }

    @Test
    public void toString1() {
        Coordinate x = new Coordinate(2.5456);
        assertEquals("2.55", x.toString());
    }

    @Test
    public void add() {
        Coordinate x = new Coordinate(1);
        Coordinate y = new Coordinate(2);
        x.add(y);
        assertEquals(3, x.getCoordinate(), 0);
    }

    @Test
    public void subtract() {
        Coordinate x = new Coordinate(1);
        Coordinate y = new Coordinate(2);
        x.subtract(y);
        assertEquals(-1, x.getCoordinate(), 0);
    }
}