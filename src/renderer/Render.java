package renderer;

import Elements.LightSource;
import Geometrics.Geometry;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import Scene.Scene;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//import Elements.LightSource;
//import Geometries.FlatGeometry;


public class Render
{
    private Scene _scene;
    private ImageWriter _imageWriter;
    private final int RECURSION_LEVEL = 3;
    // ***************** Constructors ********************** //
    public Render(ImageWriter imageWriter, Scene scene)
    {
        _scene = new Scene(scene);
        _imageWriter = new ImageWriter(imageWriter);
    }

    // ***************** Operations ******************** //
    /*************************************************
     * FUNCTION
     * renderImage
     * PARAMETERS
     * N\A
     * RETURN VALUE
     * color
     * MEANING
     * This function conpute each pixel in the scene to th right color
     **************************************************/
    public void renderImage() throws Exception {
        for (int i = 0 ; i < _imageWriter.getWidth(); i++){
            for (int j = 0; j < _imageWriter.getHeight(); j++){
                Ray ray=_scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(),
                        _imageWriter.getNy(),j,i,_scene.getScreenDistance()
                        ,_imageWriter.getWidth()
                        ,_imageWriter.getHeight());
                Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
                if(intersectionPoints.isEmpty()){
                    _imageWriter.writePixel(j,i,_scene.getBackground());
                }
                else{
                    Entry<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints).entrySet().iterator().next();
                    _imageWriter.writePixel(j,i,calcColor(closestPoint.getKey(),closestPoint.getValue()));
                }
            }
        }
    }

    //private Entry<Geometry, Point3D> findClosesntIntersection(Ray ray);

    /*************************************************
     * FUNCTION
     * printGrid
     * PARAMETERS
     * int
     * RETURN VALUE
     * N/A
     * MEANING
     * This function prints white grid on the scene
     **************************************************/
    public void printGrid(int interval){
        int height = _imageWriter.getHeight();
        int width = _imageWriter.getWidth();

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){

                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(j, i, 255, 255, 255);

            }
        }
    }

        public void writeToImage(){
        _imageWriter.writeToimage();
    }

    /*************************************************
     * FUNCTION
     * calcColor
     * PARAMETERS
     * Geometry, point3d
     * RETURN VALUE
     * color
     * MEANING
     * This function calculate color for point with his geometry color
     **************************************************/
    private Color calcColor(Geometry geometry, Point3D point) throws Exception {
        // 1. Emission light:
        Color emissionLight = geometry.getEmmission();
        // 2. Ambient light:
        Color ambientLight = _scene.getAmbientLight().getIntensity();
        // 3. Declare color:
        //Color io = addColors(emissionLight,ambientLight);
        Iterator<LightSource> lights = _scene.getLightsIterator();
        Color specularLight = new Color(0,0,0);
        Color diffuseLight = new Color(0,0,0);
        while (lights.hasNext()){
            LightSource light = lights.next();
             diffuseLight = addColors(diffuseLight, calcDiffusiveComp(geometry.getMaterial().getKd(),
                    geometry.getNormal(point),
                    light.getL(point),
                    light.getIntensity(point)));
             specularLight = addColors(specularLight, calcSpecularComp(geometry.getMaterial().getKs(),
                    new Vector(point, _scene.getCamera().get_P0()),
                    geometry.getNormal(point),
                    light.getL(point),
                    geometry.getShininess(),
                   light.getIntensity(point)));
        }

        return  addColors(addColors(emissionLight,ambientLight), addColors(diffuseLight,specularLight));
    }

    // private Color calcColor(Geometry geometry, Point3D point, Ray ray);

    // private Color calcColor(Geometry geometry, Point3D point,
    //                         Ray inRay, int level); // Recursive

    // private Ray constructRefractedRay(Geometry geometry, Point3D point,Ray inRay);

    // private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay);

    //private boolean occluded(LightSource light, Point3D point,Geometry geometry);

    private Color calcSpecularComp(double ks, Vector v, Vector normal,
                                    Vector l, double shininess, Color lightIntensity) throws Exception {
        Vector r = new Vector(normal);
        r.scale(-2*normal.dotProduct(l));
        r.add(l);
        v.normalize();
        double specular = ks*Math.pow(r.dotProduct(v),shininess);
        if(specular < 0)
        {specular *= -1;}
        return new Color((int)(lightIntensity.getRed()* specular) ,
                (int)(lightIntensity.getGreen()*specular),
                (int)(lightIntensity.getBlue()*specular));
    };
     private Color calcDiffusiveComp(double kd, Vector normal, Vector l,
                                     Color lightIntensity){
         double dif = kd*normal.dotProduct(l);

         if(dif < 0){dif *= -1;}

         return new Color((int)(lightIntensity.getRed()*dif) ,
                 (int)(lightIntensity.getGreen()*dif),
                 (int)(lightIntensity.getBlue()*dif));
     }


    /*************************************************
     * FUNCTION
     * getClosestPoint
     * PARAMETERS
     * Map (geometry, list(point3d))
     * RETURN VALUE
     * Map (geometry, point3d)
     * MEANING
     * this functions takes all the points and calculate the closest one to the camera
     **************************************************/
     private Map<Geometry, Point3D> getClosestPoint(Map<Geometry,List<Point3D>> intersectionPoints){

         double distance = Double.MAX_VALUE;
         Point3D P0 = new Point3D(_scene.getCamera().get_P0());
         Point3D minDistancPoint = null;
         Geometry minDistancGeometry = null;

         for(Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()){
             for (Point3D point: entry.getValue())
             if(P0.distance(point) < distance){
                 minDistancPoint = new Point3D(point);
                 minDistancGeometry = entry.getKey();
                 distance = P0.distance(point);
             }
         }

         Map<Geometry,Point3D> minDistancMap=new HashMap<>();
         minDistancMap.put(minDistancGeometry,minDistancPoint);
         return minDistancMap;
     }


    /*************************************************
     * FUNCTION
     * getSceneRayIntersections
     * PARAMETERS
     * ray
     * RETURN VALUE
     * Map (geometry, point3d)
     * MEANING
     * this functions takes all the points and calculate the closest one to the camera
     **************************************************/
    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) throws Exception {
        Iterator<Geometry> geometryIterator = _scene.getGeometriesIterator();
        Map<Geometry, List<Point3D>> intersectionPoint = new HashMap<Geometry, List<Point3D>>();

        while(geometryIterator.hasNext()){
            Geometry geometry = geometryIterator.next();
            List<Point3D> geomtryIntersectionPoint = geometry.FindIntersections(ray);
            if (!geomtryIntersectionPoint.isEmpty())
                intersectionPoint.put(geometry,geomtryIntersectionPoint);
        }

        return intersectionPoint;
    }

   // private  List<Point3D> getSceneRayIntersections(Ray ray) throws Exception {
   //     Iterator<Geometry> geometryIterator = _scene.getGeometriesIterator();
   //     List<Point3D> intersectionPoint = new ArrayList<Point3D>();

   //     while(geometryIterator.hasNext()){
   //         Geometry geometry = geometryIterator.next();
   //         List<Point3D> geomtryIntersectionPoint = geometry.FindIntersections(ray);
   //         if (!geomtryIntersectionPoint.isEmpty())
   //             intersectionPoint.addAll(geomtryIntersectionPoint);
   //     }

   //     return intersectionPoint;
   // }

    private Color addColors(Color a, Color b){
        return new Color((a.getRed()+b.getRed())%256,
                (a.getGreen()+b.getGreen())%256,
                (a.getBlue()+b.getBlue())%256);
    }

}