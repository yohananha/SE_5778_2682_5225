import Elements.SpotLight;
import Geometrics.Geometry;
import Geometrics.Quadrangle;
import Geometrics.Sphere;
import Geometrics.Triangle;
import Primitives.Point3D;
import Primitives.Vector;
import Scene.Scene;
import renderer.ImageWriter;
import renderer.Render;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class guiTest {
    private JPanel mainPanel;
    private JTextArea pictureBuilderTextArea;
    private JRadioButton radioButtonSpot;
    private JRadioButton radioButtonRecursive;
    private JRadioButton radioButtonFace;
    private JTextArea shadowTestTextArea;
    private JTextArea recursiveTestTextArea;
    private JTextArea faceTestTextArea;
    private JTextArea lightColorTextArea;
    private JTextArea rTextArea;
    private JTextArea gTextArea;
    private JTextArea bTextArea;
    private JTextField textFieldR;
    private JTextField textFieldG;
    private JTextField textFieldB;
    private JTextArea fileNameTextArea;
    private JTextField textFieldFileName;
    private JButton STARTButton;
    private JTextArea itensityTextArea;
    private JTextArea textAreaAngel;
    private JTextField textFieldAngle;
    private JTextField a0TextField;


    public guiTest() {
        radioButtonSpot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonSpot.isSelected())
                {
                    radioButtonFace.setSelected(false);
                    radioButtonRecursive.setSelected(false);
                }
            }
        });
        radioButtonRecursive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonRecursive.isSelected())
                {
                    radioButtonFace.setSelected(false);
                    radioButtonSpot.setSelected(false);
                }
            }
        });
        radioButtonFace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonFace.isSelected())
                {
                    radioButtonRecursive.setSelected(false);
                    radioButtonSpot.setSelected(false);
                }
            }
        });
        STARTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = textFieldFileName.getText();

                int R = Integer.parseInt(textFieldR.getText());
                int G = Integer.parseInt(textFieldG.getText());
                int B = Integer.parseInt(textFieldB.getText());


                if(radioButtonSpot.isSelected()){
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



                    try {
                        scene.addLight(new SpotLight(new Color(R,G,B), new Point3D(500, 500, -100),
                                new Vector(0, 0, -80), 0.0001, 0.0001,0.0001));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }


                    ImageWriter imageWriter = new ImageWriter(fileName, 500, 500, 500, 500);

                    Render render = new Render(imageWriter, scene);

                    try {
                        render.renderImage();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    render.writeToImage();
                }

                if(radioButtonRecursive.isSelected())
                {
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



                    try {
                        scene.addLight(new SpotLight(new Color(R,G,B),  new Point3D(200, 200, -150),
                                new Vector(-2, -2, -3), 0, 0.00001, 0.000005));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    ImageWriter imageWriter = new ImageWriter(fileName, 500, 500, 500, 500);

                    Render render = new Render(imageWriter, scene);

                    try {
                        render.renderImage();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    render.writeToImage();

                }

                if(radioButtonFace.isSelected())
                {
                    Scene scene = new Scene();
                    scene.setScreenDistance(50);

                    Quadrangle bg= new Quadrangle(new Point3D(-350, 350,-60),new Point3D(350, 350,-60),new Point3D(350, -350,-60),new Point3D(-350, -350,-60));//ear-1
                    bg.setEmmission(new Color(90, 217, 239));
                    scene.addGeometry(bg);
                    Geometry[] shapes = {
                            //ears
                            new Quadrangle(new Point3D(264.5, 21.5,-55),new Point3D(277.5, 14.5,-55),new Point3D(283.5, -42.52,-55),new Point3D(260.591, -63.5,-55)),//ear-1
                            new Quadrangle(new Point3D(53.591, 21.5,-55),new Point3D(57.5, -63.5,-55),new Point3D(34.59, -42.5,-55),new Point3D(40.591, 14.5,-55)),//ear
                            // face right
                            new Quadrangle(new Point3D(208.5, 152.5,-54),new Point3D(220.5, 145.5,-54),new Point3D(253.5, 62.5,-54),new Point3D(248.5, 29.5,-54)),//face right 0
                            new Quadrangle(new Point3D(208.5, 152.5,-54),new Point3D(264.5, -20.5,-54),new Point3D(249.5, -93.5,-54),new Point3D(213.5, -129.5,-54)),
                            new Quadrangle(new Point3D(208.5, 152.5,-54),new Point3D(187, 97,-54),new Point3D(157, 97,-54),new Point3D(213.5, -129.5,-54)),
                            new Quadrangle(new Point3D(213.5, -129.5,-54),new Point3D(207.27, -160.19,-54),new Point3D(158, -182,-54),new Point3D(157, 97,-54)),
                            // face left
                            new Quadrangle(new Point3D(157, 97,-54),new Point3D(100.01, -130,-54),new Point3D(109.815, -160.19,-54),new Point3D(158, -182,-54)),//face left 0
                            new Quadrangle(new Point3D(100.01, -130,-54),new Point3D(157, 97,-54),new Point3D(127.087, 97,-54),new Point3D(106.87, 152.5,-54)),
                            new Quadrangle(new Point3D(106.87, 152.5,-54),new Point3D(49.59, -20.5,-54),new Point3D(64.59, -93.5,-54),new Point3D(100.01, -130,-54)),
                            new Quadrangle(new Point3D(106.87, 152.5,-54),new Point3D(93.59, 145.5,-54),new Point3D(60.59, 62.5,-54),new Point3D(65.59, 29.5,-54)),
                            // smile
                            new Quadrangle(new Point3D(107.5, -93.5,-52),new Point3D(206.5, -93.5,-52),new Point3D(181.5, -117.5,-52),new Point3D(139.5, -117.5,-52)),//smile

                            new Quadrangle(new Point3D(158.5, 170.5,-53),new Point3D(209.5, 152.5,-53),new Point3D(187.5, 97.5,-53),new Point3D(158.5, 97.5,-53)),//hair right 1
                            new Triangle(new Point3D(158.5, 170.5,-53),new Point3D(205.5, 166.5,-53),new Point3D(249.5, 128.5,-53)),
                            new Triangle(new Point3D(249.5, 128.5,-53),new Point3D(221.5, 145.5,-53),new Point3D(254.5, 62.5,-53)),
                            new Quadrangle(new Point3D(249.5, 128.5,-53),new Point3D(263.5, 74.5,-53),new Point3D(263.5, -10.5,-53),new Point3D(249.5, 29.5,-53)),

                            new Quadrangle(new Point3D(158.5, 170.5,-53),new Point3D(158.5, 97.5,-53),new Point3D(127.5, 97.5,-53),new Point3D(105.5, 152.5,-53)),//hair left 1
                            new Triangle(new Point3D(158.5, 170.5,-53),new Point3D(109.5, 166.5,-53),new Point3D(65.5, 128.5,-53)),
                            new Triangle(new Point3D(93.5, 145.5,-53),new Point3D(65.5, 128.5,-53),new Point3D(60.5, 62.5,-53)),
                            new Quadrangle(new Point3D(65.5, 128.5,-53),new Point3D(51.5, 74.5,-53),new Point3D(51.5, -10.5,-53),new Point3D(65.56, 29.5,-54)),

                            new Triangle(new Point3D(214.5, -103.5,-53),new Point3D(205.5, -82.5,-53),new Point3D(206.5, -93.5,-53)),//mustache
                            new Quadrangle(new Point3D(206.5, -93.5,-53),new Point3D(205.5, -82.5,-53),new Point3D(182.52, -71.35,-53),new Point3D(156.5, -93.5,-53)),
                            new Quadrangle(new Point3D(156.5, -93.5,-53),new Point3D(132.5, -71.5,-53),new Point3D(157.5, -75,-53),new Point3D(182.52, -71.35,-53)),
                            new Quadrangle(new Point3D(156.5, -93.5,-53),new Point3D(132.5, -71.5,-53),new Point3D(108.5, -82.5,-53),new Point3D(107.5, -93.5,-53)),
                            new Triangle(new Point3D(108.5, -82.5,-53),new Point3D(107.5, -93.5,-53),new Point3D(99.5, -103.5,-53)),

                            new Quadrangle(new Point3D(139.5, -130.5,-53),new Point3D(181.5, -130.5,-53),new Point3D(169.5, -148.5,-53),new Point3D(144.5, -148.5,-53)),//zvov

                            new Quadrangle(new Point3D(264.5, -20.5,-53),new Point3D(249.5, -93.5,-53),new Point3D(256.5, -108.5,-53),new Point3D(213.5, -129.5,-53)),//beard right
                            new Quadrangle(new Point3D(256.5, -108.5,-53),new Point3D(213.5, -129.5,-53),new Point3D(204.5, -160.5,-53),new Point3D(205.5, -173.5,-53)),
                            new Quadrangle(new Point3D(204.5, -160.5,-53),new Point3D(205.5, -173.5,-53),new Point3D(157.5, -189.5,-53),new Point3D(156.5, -181.5,-53)),

                            new Quadrangle(new Point3D(157.5, -189.5,-53),new Point3D(156.5, -181.5,-53),new Point3D(108.5, -173.5,-53),new Point3D(109.5, -160.5,-53)),//beard left
                            new Quadrangle(new Point3D(108.5, -173.5,-53),new Point3D(109.5, -160.5,-53),new Point3D(100.5, -129.5,-53),new Point3D(57.5, -108.5,-53)),
                            new Quadrangle(new Point3D(100.5, -129.5,-53),new Point3D(57.5, -108.5,-53),new Point3D(64.5, -93.5,-53),new Point3D(49.5, -20.5,-53)),

                            new Sphere(10,new Point3D(204,-4,-50)),//eyes 2
                            new Sphere(10,new Point3D(115,-4,-50)),

                            new Triangle(new Point3D(200.5, 57.5,-51),new Point3D(156.5, 65.2,-51),new Point3D(119.5, 57.5,-51)),//forehead 3

                            new Quadrangle(new Point3D(179.5, 26.5,-51),new Point3D(179.5, 19.5,-51),new Point3D(228.5, 19.5,-51),new Point3D(228.5, 26.5,-51)),//eyebrow right
                            new Quadrangle(new Point3D(139.5, 26.5,-51),new Point3D(139.5, 19.5,-51),new Point3D(90.5, 19.5,-51),new Point3D(90.5, 26.5,-51)),//eyebrow left

                            new Quadrangle(new Point3D(185.5, -10.5,-51),new Point3D(187.5, -37.5,-51),new Point3D(156.5, -75,-51),new Point3D(156.5, 22.3,-51)),//Nose
                            new Quadrangle(new Point3D(156.5, -75,-51),new Point3D(156.5, 22.3,-51),new Point3D(128.5, -10.5,-51),new Point3D(126.5, -37.5,-51)),//
                            new Quadrangle(new Point3D(187.5, -37.5,-51),new Point3D(156.5, -75,-51),new Point3D(200.5, -45.5,-51),new Point3D(200.5, -68.5,-51)),//
                            new Quadrangle(new Point3D(126.5, -37.5,-51),new Point3D(156.5, -75,-51),new Point3D(113.5, -45.5,-51),new Point3D(113.5, -68.5,-51)),//

                            new Quadrangle(new Point3D(106.5, -93.5,-51),new Point3D(139.5, -117.5,-51),new Point3D(181.5, -117.5,-51),new Point3D(139.5, -130.5,-51)),//lips
                            new Quadrangle(new Point3D(181.5, -117.5,-51),new Point3D(139.5, -130.5,-51),new Point3D(206.5, -93.5,-51),new Point3D(185.5, -130.5,-51)),//

                    };


                    Geometry[] yohanan = {
                            // ears
                            new Quadrangle(new Point3D(-283.5, 25.5,-55),new Point3D(-268.5, 27,-55),new Point3D(-278.5, -47.5,-55),new Point3D(-283.5, -39.5,-55)),//ear-1
                            new Triangle(new Point3D(-278.5, -47.5,-55),new Point3D(-268.5, 26.5,-55),new Point3D(-266.5, -47.5,-55)),
                            new Quadrangle(new Point3D(-89.5, 27.5,-55),new Point3D(-74.5, 25.5,-55),new Point3D(-74.5, -39.5,-55),new Point3D(-79.5, -47.5,-55)),//ear-1
                            new Triangle(new Point3D(-79.5, -47.5,-55),new Point3D(-89.6, 26.9,-55),new Point3D(-91.5, -47.5,-55)),
                            // face
                            new Quadrangle(new Point3D(-265, 107,-54),new Point3D(-251.5, 117.5,-54),new Point3D(-259, -67,-54),new Point3D(-272, 63,-54)),
                            new Quadrangle(new Point3D(-259, -67,-54),new Point3D(-252, 118,-54),new Point3D(-179, 111,-54),new Point3D(-179, -60,-54)),
                            new Quadrangle(new Point3D(-106, 118,-54),new Point3D(-179, 111,-54),new Point3D(-179, -60,-54),new Point3D(-99, -67,-54)),
                            new Quadrangle(new Point3D(-106, 118,-54),new Point3D(-99, -67,-54),new Point3D(-93, 107,-54),new Point3D(-86, 63,-54)),
                            // lower face
                            new Quadrangle(new Point3D(-131.5, -73.5,-54),new Point3D(-126.5, -73.5,-54),new Point3D(-143.5, -93.5,-54),new Point3D(-137.5, 107.5,-54)),
                            new Quadrangle(new Point3D(-143.5, -93.5,-54),new Point3D(-137.5, -107.5,-54),new Point3D(-161.5, -93.5,-54),new Point3D(-154.5, -107.5,-54)),
                            new Quadrangle(new Point3D(-196.5, -93.5,-54),new Point3D(-204.5, -107.5,-54),new Point3D(-220.5, -107.5,-54),new Point3D(-213.5, -93.5,-54)),
                            new Triangle(new Point3D(-220.5, -107.5,-54),new Point3D(-213.5, 93.5,-54),new Point3D(-229.5, -72.5,-54)),
                            // Sdmile
                            new Quadrangle(new Point3D(-229.5, -72.5,-53),new Point3D(-131.5, -73.5,-53),new Point3D(-143.5, -89.5,-53),new Point3D(-211.5, -90.5,-53)),
                            // Hair
                            new Quadrangle(new Point3D(-268.5, 30.5,-53),new Point3D(-281.5, 72.5,-53),new Point3D(-278.5, 113.5,-53),new Point3D(-264.5, 166.5,-53)),
                            new Quadrangle(new Point3D(-264.5, 166.5,-53),new Point3D(-266.5, 105.5,-53),new Point3D(-251.5, 117.5,-53),new Point3D(-134.5, 110.5,-53)),
                            new Quadrangle(new Point3D(-264.5, 166.5,-53),new Point3D(-134.5, 110.5,-53),new Point3D(-105.5, 138.5,-53),new Point3D(-89.5, 122.5,-53)),
                            new Quadrangle(new Point3D(-89.5, 122.5,-53),new Point3D(-106, 118,-53),new Point3D(-92.76, 105.5,-53),new Point3D(-89.5, 27.5,-53)),
                            new Quadrangle(new Point3D(-89.5, 27.5,-53),new Point3D(-89.5, 122.5,-53),new Point3D(-79.5, 90.5,-53),new Point3D(-79.5, 51.5,-53)),
                            // Beard left
                            new Quadrangle(new Point3D(-268.5, 30.5,-54),new Point3D(-272.5, -14.2,-54),new Point3D(-250, -152.5,-54),new Point3D(-252.1, -66.5,-54)),
                            new Quadrangle(new Point3D(-250, -152.5,-54),new Point3D(-252.1, -66.5,-54),new Point3D(-230.5, -73.5,-54),new Point3D(-219.5, -107.5,-54)),
                            // Beard right
                            new Quadrangle(new Point3D(-89.6, 26.9,-54),new Point3D(-84, -14.2,-54),new Point3D(-105.5, -152.5,-54),new Point3D(-104.6, -65,-54)),
                            new Quadrangle(new Point3D(-105.5, -152.5,-54),new Point3D(-104, -65,-54),new Point3D(-126.5, -73.5,-54),new Point3D(-137.5, -107.5,-54)),
                            // Mustache
                            new Quadrangle(new Point3D(-252.1, -66.5,-54),new Point3D(-179.5, -58.5,-54),new Point3D(-179.5, -73.5,-54),new Point3D(-229.5, -72.5,-54)),
                            new Quadrangle(new Point3D(-179.5, -58.5,-54),new Point3D(-179.5, -73.5,-54),new Point3D(-104, -65,-54),new Point3D(-126.5, -73.5,-54)),
                            // Chin beard
                            new Quadrangle(new Point3D(-209.5, -173.5,-53),new Point3D(-146.5, -173.5,-54),new Point3D(-105.5, -152.5,-54),new Point3D(-250, -152.5,-54)),

                            new Quadrangle(new Point3D(-105.5, -152.5,-54),new Point3D(-250, -152.5,-54),new Point3D(-219.5, -107.5,-54),new Point3D(-137.5, -107.5,-54)),
                            new Quadrangle(new Point3D(-196.5, -93.5,-54),new Point3D(-161.5, -93.5,-54),new Point3D(-154, -107.5,-54),new Point3D(-205, -107.5,-54 )),
                            // Face detail
                            new Triangle(new Point3D(-228.5, 72.5,-51),new Point3D(-179, 75,-51),new Point3D(-129.5, 72.5,-51)),
                            // Eyebrows
                            new Quadrangle(new Point3D(-101.5, 39.5,-53),new Point3D(-101.5, 26.5,-53),new Point3D(-161.5, 26.5,-53),new Point3D(-161.5, 39.5,-53)),
                            new Quadrangle(new Point3D(-196.5, 39.5,-53),new Point3D(-196.5, 26.5,-53),new Point3D(-256.5, 26.5,-53),new Point3D(-256.5, 39.5,-53)),
                            // Nose
                            new Quadrangle(new Point3D(-161.5, -56.5,-51),new Point3D(-164.5, 17.5,-51),new Point3D(-178.5, 21.5,-51),new Point3D(-178.5, -63.5,-51)),
                            new Quadrangle(new Point3D(-192.9, 17.5,-51),new Point3D(-178.5, 21.5,-51),new Point3D(-178.5, -63.5,-51),new Point3D(-195.9, -56.5,-51)),
                            // Lips
                            new Quadrangle(new Point3D(-211.5, -90.5,-53),new Point3D(-213.5, -93.5,-53),new Point3D(-143.5, -93.5,-53),new Point3D(-143.5, -89.5,-53)),
                            new Triangle(new Point3D(-143.5, -93.5,-53),new Point3D(-143.5, -89.5,-53),new Point3D(-131.5, -73.5,-53)),
                            new Triangle(new Point3D(-211.5, -90.5,-53),new Point3D(-213.5, -93.5,-53),new Point3D(-229.5, -72.5,-53)),
                            // Eyes
                            new Sphere(10, new Point3D(-215,-1,-50)),
                            new Sphere(10, new Point3D(-144, -1 , -50))
                    };

                    Color [] yohananColors = {
                            //ears
                            new Color(239, 159, 120),
                            new Color(239, 159, 120),
                            new Color(239, 159, 120),
                            new Color(239, 159, 120),
                            //face
                            new Color(247, 174, 146),
                            new Color(247, 174, 146),
                            new Color(247, 174, 146),
                            new Color(247, 174, 146),
                            new Color(247, 174, 146),
                            new Color(247, 174, 146),
                            new Color(247, 174, 146),
                            new Color(247, 174, 146),
                            // smile
                            new Color(255, 255, 255),
                            //hsir
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            //beard
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            //nustache
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            //chin
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            // detail
                            new Color(203, 135, 102),
                            //eye brows
                            new Color(0, 0, 0)
                            ,new Color(0, 0, 0),
                            // nose
                            new Color(203, 135, 102),
                            new Color(203, 135, 102),
                            //lips
                            new Color(203, 135, 102),
                            new Color(203, 135, 102),
                            new Color(203, 135, 102),
                            // eyes
                            new Color(64, 46, 32),
                            new Color(64, 46, 32),
                    };

                    // x = 650 - x (picture)
                    // y = y (picture) - 650
                    for (int i = 0; i < yohanan.length; i++)
                    {
                        yohanan[i].setEmmission(yohananColors[i]);
                        scene.addGeometry(yohanan[i]);
                    }

                    Color [] colors = {
                            // ears
                            new Color(181, 112, 85),
                            new Color(181, 112, 85),
                            //face right
                            new Color(239, 159, 120),
                            new Color(239, 159, 120),
                            new Color(239, 159, 120),
                            new Color(239, 159, 120),
                            // face left
                            new Color(239, 159, 120),
                            new Color(239, 159, 120),
                            new Color(239, 159, 120),
                            new Color(239, 159, 120),
                            //smile
                            new Color(255, 255, 255),
                            //hair
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            //mustache
                            new Color(112, 83, 71),
                            new Color(112, 83, 71),
                            new Color(112, 83, 71),
                            new Color(112, 83, 71),
                            new Color(112, 83, 71),
                            //fly
                            new Color(112, 83, 71),
                            //beard
                            new Color(112, 83, 71),
                            new Color(112, 83, 71),
                            new Color(112, 83, 71),
                            new Color(112, 83, 71),
                            new Color(112, 83, 71),
                            new Color(112, 83, 71),
                            //eyes
                            new Color(64, 46, 32),
                            new Color(64, 46, 32),
                            // detail
                            new Color(181, 105, 75),
                            // eyebrows
                            new Color(0, 0, 0),
                            new Color(0, 0, 0),
                            //nose
                            new Color(181, 105, 75),
                            new Color(181, 105, 75),
                            new Color(181, 105, 75),
                            new Color(181, 105, 75),
                            //lips
                            new Color(219, 122, 88),
                            new Color(219, 122, 88)

                    };

                    // x = 650 - x (picture)
                    // y = y (picture) - 650
                    for (int i = 0; i < shapes.length; i++)
                    {
                        shapes[i].setEmmission(colors[i]);
                        scene.addGeometry(shapes[i]);
                    }

                    ImageWriter imageWriter = new ImageWriter(fileName, 700, 700, 700, 700);

                    Render render = new Render(imageWriter, scene);

                    try {
                        render.renderImage1();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    //render.printGrid(25);
                    render.writeToImage();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("guiTest");
        frame.setContentPane(new guiTest().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}




