package Test.renderer;

import Elements.SpotLight;
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

        scene.addLight(new SpotLight(new Color(200, 200, 200), new Point3D(200, 200, -100),
                new Vector(0, 0, -80), 0, 0,0));


        ImageWriter imageWriter = new ImageWriter("Shadow test", 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void shadowTest1() throws Exception {
        Scene scene = new Scene();
       // scene.setCamera(new Camera(new Vector(0, -1, 0), new Vector(0, 0, 1), new Point3D(0, 0, 0)));
       // scene.set_distance(100);
       // scene.set_background(new Color(0,0,0));
       // Geometries geometries = new Geometries();
        Sphere sphere = new Sphere(60, new Point3D(0, 0, -80));
        sphere.setShininess(300);
        sphere.setKd(0.8);
        sphere.setKs(0.9);
        sphere.setEmmission( new Color(241, 6, 151));
        Triangle triangle1 = new Triangle(new Point3D(-250,-250,-120),
                new Point3D(-250,250,-120),new Point3D(250,-250,-120));
        triangle1.setEmmission(new Color(0,0,0));

        Triangle triangle2 = new Triangle(new Point3D(250,250,-120)
                ,new Point3D(-250,250,-120),new Point3D(250,-250,-120));
        triangle2.setEmmission(new Color(0,0,0));
        scene.addGeometry(sphere);
        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
       // scene.set_geometries(geometries);
        //List<LightSource> lights = new ArrayList<LightSource>);

        scene.addLight(new SpotLight(new Color(255,255,255),new Point3D(25,0,0), new Vector(-25,0,80),0,0,0 ));
        ImageWriter imageWriter = new ImageWriter("shadow test1", 500, 500, 500, 500);
        Render testRender = new Render(imageWriter, scene);
        testRender.renderImage();
        testRender.writeToImage();

    }

    }