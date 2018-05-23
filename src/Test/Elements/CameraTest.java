package Test.Elements;

import Elements.Camera;
import Primitives.*;
import org.junit.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CameraTest {

    //@Test
    //public String toString() {
      //  return null;
    //}

    @Test
    public void get_P0() {
    }

    @Test
    public void set_P0() {
    }

    @Test
    public void get_vUp() {
    }

    @Test
    public void set_vUp() {
    }

    @Test
    public void get_vTo() {
    }

    @Test
    public void set_vTo() {
    }

    @Test
    public void get_vRight() {
    }

    @Test
    public void set_vRight() {
    }

    @Test
    public void testRaysConstruction() throws Exception {
        final int WIDTH = 3;
        final int HEIGHT = 3;
        Point3D[][] screen = new Point3D [HEIGHT][WIDTH];
        Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),
                new Vector(0.0, 1.0, 0.0),
                new Vector (0.0, 0.0, -1.0));
        System.out.println("Camera:\n" + camera);
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                Ray ray = camera.constructRayThroughPixel(
                        WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);
                screen[i][j] = ray.getPOO();
                System.out.print(screen[i][j]);
                System.out.println(ray.getDirection());
// Checking z-coordinate
                assertTrue(Double.compare(screen[i][j].getZ().getCoordinate(),-1.0) == 0);
// Checking all options
                double x = screen[i][j].getX().getCoordinate();
                double y = screen[i][j].getX().getCoordinate();
                if (Double.compare(x, 3) == 0 ||
                        Double.compare(x, 0) == 0 ||
                        Double.compare(x, -3) == 0){
                    if (Double.compare(y, 3) == 0 ||
                            Double.compare(y, 0) == 0 ||
                            Double.compare(y, -3) == 0){
                        assertTrue(true);
                    }
                    else
                        fail("Wrong y coordinate");
                } else
                    fail("Wrong x coordinate");
            }
            System.out.println("---");
        }
    }
}