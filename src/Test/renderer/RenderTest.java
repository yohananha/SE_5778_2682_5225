package Test.renderer;

import Elements.SpotLight;
import Geometrics.Geometry;
import Geometrics.Quadrangle;
import Geometrics.Sphere;
import Geometrics.Triangle;
import Primitives.Point3D;
import Primitives.Vector;
import Scene.Scene;
import org.junit.Test;
import renderer.ImageWriter;
import renderer.Render;

import java.awt.*;

public class RenderTest {

    @Test
    public void basicRendering() throws Exception {

        Scene scene = new Scene();

        scene.addGeometry(new Sphere(50, new Point3D(0.0, 0.0, -150)));

        Triangle triangle = new Triangle(new Point3D( 100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D( 100, 100, -149));

        Triangle triangle2 = new Triangle(new Point3D( 100, 0, -149),
                new Point3D(  0, -100, -149),
                new Point3D( 100,-100, -149));

        Triangle triangle3 = new Triangle(new Point3D(-100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D(-100, 100, -149));

        Triangle triangle4 = new Triangle(new Point3D(-100, 0, -149),
                new Point3D(  0,  -100, -149),
                new Point3D(-100, -100, -149));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter = new ImageWriter("Render test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(50);
        render.writeToImage();

    }


    @Test
    public void emmissionTest() throws Exception {
        Scene scene = new Scene();
        scene.setScreenDistance(50);

        Sphere sphere = new Sphere(50, new Point3D(0.0, 0.0, -150));
        Triangle triangle1 = new Triangle(new Point3D( 100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D( 100, 100, -149));

        Triangle triangle2 = new Triangle(new Point3D( 100, 0, -149),
                new Point3D(  0, -100, -149),
                new Point3D( 100,-100, -149));

        Triangle triangle3 = new Triangle(new Point3D(-100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D(-100, 100, -149));

        Triangle triangle4 =new Triangle(new Point3D(-100, 0, -149),
                new Point3D(  0,  -100, -149),
                new Point3D(-100, -100, -149));

        sphere.setEmmission(new Color(255, 255, 255));
        triangle1.setEmmission(new Color (255, 255, 255));
        triangle2.setEmmission(new Color (255, 255, 255));
        triangle3.setEmmission(new Color (255, 255, 255));
        triangle4.setEmmission(new Color (255, 255, 255));

        scene.addGeometry(sphere);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter = new ImageWriter("Emmission test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.printGrid(50);
        render.writeToImage();
    }

        @Test
        public void emmissionTest1() throws Exception {
            Scene scene = new Scene();
            scene.setScreenDistance(50);

            Sphere sphere = new Sphere(100, new Point3D(0.0, 0.0, -50));
            Triangle triangle = new Triangle(new Point3D( 150, 0, -51),
                    new Point3D(  0, 150, -51),
                    new Point3D( 150, 150, -51));

            Triangle triangle2 = new Triangle(new Point3D( 150, 0, -51),
                    new Point3D(  0, -150, -51),
                    new Point3D( 150,-150, -51));

            Triangle triangle3 = new Triangle(new Point3D(-150, 0, -51),
                    new Point3D(  0, 150, -51),
                    new Point3D(-150, 150, -51));

            Triangle triangle4 = new Triangle(new Point3D(-150, 0, -51),
                    new Point3D(  0,  -150, -51),
                    new Point3D(-150, -150, -51));

            sphere.setEmmission(new Color (255, 0, 120));
            triangle.setEmmission(new Color (255, 0, 0));
            triangle2.setEmmission(new Color (0, 255, 0));
            triangle3.setEmmission(new Color (255, 0, 255));
            triangle4.setEmmission(new Color (0, 255, 255));

            scene.addGeometry(sphere);
            scene.addGeometry(triangle);
            scene.addGeometry(triangle2);
            scene.addGeometry(triangle3);
            scene.addGeometry(triangle4);



            ImageWriter imageWriter = new ImageWriter("Emmission test1", 500, 500, 500, 500);

            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.printGrid(50);
            render.writeToImage();
        }

    @Test
    public void shadowTest() throws Exception {

        Scene scene = new Scene();
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -800));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(241, 6, 151));

        scene.addGeometry(sphere);

        Triangle triangle1 = new Triangle(new Point3D(  3500,  3500, -1000),
                new Point3D( -3500, -3500, -1000),
                new Point3D(  3500, -3500, -1000));

        Triangle triangle2 = new Triangle(new Point3D(  3500,  3500, -1000),
                new Point3D( -3500,  3500, -1000),
                new Point3D( -3500, -3500, -1000));

       scene.addGeometry(triangle1);
       scene.addGeometry(triangle2);

        scene.addLight(new SpotLight(new Color(200, 200, 200), new Point3D(500, 500, -100),
                new Vector(0, 0, -80), 0.0001, 0.0001,0.0001));


        ImageWriter imageWriter = new ImageWriter("Shadow test SpotLight1", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void shadowTest1() throws Exception {
        Scene scene = new Scene();
        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -800));
        sphere.setShininess(20);
        sphere.setEmmission(new Color(241, 6, 151));

        scene.addGeometry(sphere);

        Triangle triangle1 = new Triangle(new Point3D(  300,  300, -500),
                new Point3D( -300, -300, -500),
                new Point3D(  300, -300, -500));



        scene.addGeometry(triangle1);


        scene.addLight(new SpotLight(new Color(200, 200, 200), new Point3D(-500, -500, -100),
                new Vector(100, 100, -80), 0.0001, 0.0001,0.0001));


        ImageWriter imageWriter = new ImageWriter("Shadow test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void spotLightTest2() throws Exception {


        Scene scene = new Scene();
        //scene.setScreenDistance(200);
        Sphere sphere = new Sphere( 500, new Point3D(0.0, 0.0, -1000));
        sphere.setEmmission(new Color(241, 6, 151));
        sphere.setShininess(20);
        scene.addGeometry(sphere);

        Triangle triangle = new Triangle(new Point3D(-125, -225, -260),
                new Point3D(-225, -125, -260),
                new Point3D(-225, -225, -270));

        triangle.setEmmission(new Color(68, 242, 16));

        Triangle triangle1 = new Triangle(new Point3D(  3500,  3500, -1000),
                new Point3D( -3500, -3500, -1000),
                new Point3D(  3500, -3500, -1000));

        Triangle triangle2 = new Triangle(new Point3D(  3500,  3500, -1000),
                new Point3D( -3500,  3500, -1000),
                new Point3D( -3500, -3500, -1000));

        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);

        triangle.setShininess(4);
        scene.addGeometry(triangle);


        scene.addLight(new SpotLight(new Color(200, 200, 200), new Point3D(-250, -250, -150),
                new Vector(2, 2, -2), 0.0001, 0.0001,0.0001));

        ImageWriter imageWriter = new ImageWriter("Spot test 2", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        //render.printGrid(50);
        render.writeToImage();

    }


        @Test
        public void recursiveTest() throws Exception {
            Scene scene = new Scene();
            scene.setScreenDistance(300);

            Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
            sphere.setShininess(20);
            sphere.setEmmission(new Color(0, 0, 100));
            sphere.setKt(0.5);
            scene.addGeometry(sphere);

            Sphere sphere2 = new Sphere(250, new Point3D(0.0, 0.0, -1000));
            sphere2.setShininess(20);
            sphere2.setEmmission(new Color(100, 20, 20));
            sphere2.setKt(0);
            scene.addGeometry(sphere2);

            scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150),
                    new Vector(2, 2, -3), 0.1, 0.00001, 0.000005));

            ImageWriter imageWriter = new ImageWriter("Recursive Test 11", 500, 500, 500, 500);

            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();
        }


        @Test
        public void recursiveTest2() throws Exception {

            Scene scene = new Scene();
            scene.setScreenDistance(300);

            Sphere sphere = new Sphere(300, new Point3D(-550, -500, -1000));
            sphere.setShininess(20);
            sphere.setEmmission(new Color(0, 0, 100));
            sphere.setKt(0.5);
            scene.addGeometry(sphere);

            Sphere sphere2 = new Sphere(150, new Point3D(-550, -500, -1000));
            sphere2.setShininess(20);
            sphere2.setEmmission(new Color(100, 20, 20));
            sphere2.setKt(0);
            scene.addGeometry(sphere2);

            Triangle triangle = new Triangle(new Point3D(  1500, -1500, -1500),
                    new Point3D( -1500,  1500, -1500),
                    new Point3D(  200,  200, -375));

            Triangle triangle2 = new Triangle(new Point3D(  1500, -1500, -1500),
                    new Point3D( -1500,  1500, -1500),
                    new Point3D( -1500, -1500, -1500));

            triangle.setEmmission(new Color(20, 20, 20));
            triangle2.setEmmission(new Color(20, 20, 20));
            triangle.setKr(1);
            triangle2.setKr(0.5);
            scene.addGeometry(triangle);
            scene.addGeometry(triangle2);

            scene.addLight(new SpotLight(new Color(255, 100, 100),  new Point3D(200, 200, -150),
                    new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

            ImageWriter imageWriter = new ImageWriter("Recursive Test 2", 500, 500, 500, 500);

            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();

        }

        @Test
        public void recursiveTest3() throws Exception {

            Scene scene = new Scene();
            scene.setScreenDistance(300);

            Sphere sphere = new Sphere(300, new Point3D(0, 0, -1000));
            sphere.setShininess(20);
            sphere.setEmmission(new Color(0, 0, 100));
            sphere.setKt(0.5);
            scene.addGeometry(sphere);

            Sphere sphere2 = new Sphere(150, new Point3D(0, 0, -1000));
            sphere2.setShininess(20);
            sphere2.setEmmission(new Color(100, 20, 20));
            sphere2.setKt(0);
            scene.addGeometry(sphere2);

            Triangle triangle = new Triangle(new Point3D(  2000, -1000, -1500),
                    new Point3D( -1000,  2000, -1500),
                    new Point3D(  700,  700, -375));

            Triangle triangle2 = new Triangle(new Point3D(  2000, -1000, -1500),
                    new Point3D( -1000,  2000, -1500),
                    new Point3D( -1000, -1000, -1500));

            triangle.setEmmission(new Color(20, 20, 20));
            triangle2.setEmmission(new Color(20, 20, 20));
            triangle.setKr(1);
            triangle2.setKr(0.5);
            scene.addGeometry(triangle);
            scene.addGeometry(triangle2);

            scene.addLight(new SpotLight(new Color(255, 100, 100),  new Point3D(200, 200, -150),
                    new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

            ImageWriter imageWriter = new ImageWriter("Recursive Test 3", 500, 500, 500, 500);

            Render render = new Render(imageWriter, scene);

            render.renderImage();
            render.writeToImage();
        }

    @Test
    public void IdoYohananTest() throws Exception {

        Scene scene = new Scene();
        scene.setScreenDistance(50);

        Geometry[] shapes = {
                new Quadrangle(new Point3D(264.5, 21.5,-55),new Point3D(277.5, 14.5,-55),new Point3D(283.5, -42.52,-55),new Point3D(260.591, -63.5,-55)),//ear-1
                new Quadrangle(new Point3D(53.591, 21.5,-55),new Point3D(57.5, -63.5,-55),new Point3D(34.59, -42.5,-55),new Point3D(40.591, 14.5,-55)),//ear
                new Quadrangle(new Point3D(208.5, 152.5,-54),new Point3D(220.5, 145.5,-54),new Point3D(253.5, 62.5,-54),new Point3D(248.5, 29.5,-54)),//face right 0
                new Quadrangle(new Point3D(208.5, 152.5,-54),new Point3D(264.5, -20.5,-54),new Point3D(249.5, -93.5,-54),new Point3D(213.5, -129.5,-54)),
                new Quadrangle(new Point3D(208.5, 152.5,-54),new Point3D(187, 97,-54),new Point3D(157, 97,-54),new Point3D(213.5, -129.5,-54)),
                new Quadrangle(new Point3D(213.5, -129.5,-54),new Point3D(207.27, -160.19,-54),new Point3D(158, -182,-54),new Point3D(157, 97,-54)),
                new Quadrangle(new Point3D(157, 97,-54),new Point3D(100.01, -130,-54),new Point3D(109.815, -160.19,-54),new Point3D(158, -182,-54)),//face left 0
                new Quadrangle(new Point3D(100.01, -130,-54),new Point3D(157, 97,-54),new Point3D(127.087, 97,-54),new Point3D(106.87, 152.5,-54)),
                new Quadrangle(new Point3D(106.87, 152.5,-54),new Point3D(49.59, -20.5,-54),new Point3D(64.59, -93.5,-54),new Point3D(100.01, -130,-54)),
                new Quadrangle(new Point3D(106.87, 152.5,-54),new Point3D(93.59, 145.5,-54),new Point3D(60.59, 62.5,-54),new Point3D(65.59, 29.5,-54)),
                new Quadrangle(new Point3D(139.5, -117.5,-54),new Point3D(107.5, -93.5,-54),new Point3D(206.5, -93.5,-54),new Point3D(181.5, 117.5,-54)),//smile
                new Quadrangle(new Point3D(158.5, 170.5,-53),new Point3D(209.5, 152.5,-53),new Point3D(187.5, 97.5,-54),new Point3D(158.5, 97.5,-54)),//hair right 1
                new Triangle(new Point3D(158.5, 170.5,-53),new Point3D(205.5, 166.5,-53),new Point3D(249.5, 128.5,-53)),

                new Triangle(new Point3D(50, 20,-50),new Point3D(86, 85,-50),new Point3D(37, 94,-50)),
                new Triangle(new Point3D(12, -24,-50),new Point3D(52, 19,-50),new Point3D(37, 94,-50)),
                new Triangle(new Point3D(85, 84,-50),new Point3D(159, 99,-50),new Point3D(120, 138,-50)),
                new Quadrangle(new Point3D(25, 34,-50),new Point3D(37, 94,-50),new Point3D(-124, 125,-50),new Point3D(-96, 89,-50)),
                new Triangle(new Point3D(37, 94,-50),new Point3D(21, 179,-50),new Point3D(-124, 125,-50))
        };

        Color [] colors = {
                new Color(32,178,170),
                new Color(60,179,119),
                new Color(70,130,180),
                new Color(70,130,180),
                new Color(34,140,34),
                new Color(32,178,170),
                new Color(60,179,119),
                new Color(70,130,180),
                new Color(70,130,180),
                new Color(34,140,34),
                new Color(32,178,170),
                new Color(60,179,119),
                new Color(70,130,180),
                new Color(70,130,180),
                new Color(34,140,34),
                new Color(32,178,170)
        };

        // x = 650 - x (picture)
        // y = y (picture) - 650
        for (int i = 0; i < shapes.length; i++)
        {
            shapes[i].setEmmission(colors[i]);
            scene.addGeometry(shapes[i]);
        }

        ImageWriter imageWriter = new ImageWriter("IdoYohananTest", 1300, 1300, 1300, 1300);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        //render.printGrid(25);
        render.writeToImage();
    }

}