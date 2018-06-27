package renderer;

import Elements.LightSource;
import Geometrics.FlatGeometry;
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
                    _imageWriter.writePixel(j,i,calcColor(closestPoint.getKey(),closestPoint.getValue(),ray));
                }
            }
        }
    }

    public void renderImage1() throws Exception {
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

    private Entry<Geometry, Point3D> findClosesntIntersection(Ray ray) throws Exception {
        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);

        if (intersectionPoints.size() == 0)
            return null;

        Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
        Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
        return entry;
    }

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

        return  addColors(emissionLight,ambientLight);
    }

    /*************************************************
     * FUNCTION
     * occluded
     * PARAMETERS
     * LightSource, point3d, Geometry
     * RETURN VALUE
     * boolean
     * MEANING
     * This function check for specific point if thers more then one object how stand in fron of him,
     * and return true only if we have the frontal point
     **************************************************/
    private boolean occluded(LightSource light, Point3D point, Geometry geometry) throws Exception {
        //1. Connect the point to the light source
        Vector lightDirection = light.getL(point);
        //2. Reverse the vector to point backward to the light source
        lightDirection.scale(-1);

        //3. the point that send the ray back
        Point3D geometryPoint = new Point3D(point);

        //3.5 Floating point corecction
        //Vector epsVector = new Vector(0.0000001,0.0000001,0.0000001);
        Vector epsVector = new Vector(geometry.getNormal(point));
        epsVector.scale(2);
        geometryPoint.add(epsVector);

        //4. Construct ray from the point back to the light
        Ray lightRay = new Ray(geometryPoint, lightDirection);
        //5. Get all the intersection between the pint and the light source into a mao
        Map <Geometry,List<Point3D>> intersectionPoint = getSceneRayIntersections(lightRay);

        // 5.5 Flat geometry
        if (geometry instanceof FlatGeometry){
            intersectionPoint.remove(geometry);
        }

        //6. If the map is empty - the light goes directly to the point
        //   Otherwise - there's something between them
        return !intersectionPoint.isEmpty();
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
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay) throws Exception {
        return calcColor(geometry, point, inRay, 0);
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
     private Color calcColor(Geometry geometry, Point3D point,Ray inRay, int level) throws Exception {
         if (level == RECURSION_LEVEL){
             return new Color(0, 0, 0);
         }

         Color ambientLight = _scene.getAmbientLight().getIntensity();
         Color emissionLight = geometry.getEmmission();

         Color inherentColors = addColors(ambientLight, emissionLight);

         Iterator<LightSource> lights = _scene.getLightsIterator();

         Color lightReflected = new Color(0, 0, 0);

         while (lights.hasNext()){

             LightSource light = lights.next();

             if (!occluded(light, point, geometry)){

                 Color lightIntensity = light.getIntensity(point);


                 Color lightDiffuse = calcDiffusiveComp(geometry.getMaterial().getKd(),
                         geometry.getNormal(point),
                         light.getL(point),
                         lightIntensity);


                 Color lightSpecular = calcSpecularComp(geometry.getMaterial().getKs(),
                         new Vector(point, _scene.getCamera().get_P0()),
                         geometry.getNormal(point),
                         light.getL(point),
                         geometry.getShininess(),
                         lightIntensity);

                 lightReflected = addColors(lightDiffuse, lightSpecular);
             }
         }

         Color I0 = addColors(inherentColors, lightReflected);


         //**// Recursive calls

         // Recursive call for a reflected ray
         Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
         Entry<Geometry, Point3D> reflectedEntry = findClosesntIntersection(reflectedRay);
         Color reflected = new Color(0, 0, 0);
         if (reflectedEntry != null){
             reflected = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
             double kr = geometry.getMaterial().getKr();
             reflected = new Color ((int)(reflected.getRed() * kr), (int)(reflected.getGreen() * kr),(int)(reflected.getBlue() * kr));
         }

         // Recursive call for a refracted ray
         Ray refractedRay = constructRefractedRay(geometry, point, inRay);
         Entry<Geometry, Point3D> refractedEntry = findClosesntIntersection(refractedRay);
         Color refracted = new Color(0, 0, 0);
         if (refractedEntry != null){
             refracted = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
             double kt = geometry.getMaterial().getKt();
             refracted = new Color ((int)(refracted.getRed() * kt), (int)(refracted.getGreen() * kt),(int)(refracted.getBlue() * kt));
         }


         //**// End of recursive calls

         Color envColors = addColors(reflected, refracted);

         Color finalColor = addColors(envColors, I0);

         return finalColor;
     }

    /*************************************************
     * FUNCTION
     * constructRefractedRay
     * PARAMETERS
     * Geometry, point3d, Ray
     * RETURN VALUE
     * ray
     * MEANING
     * This function calculate the refracted ray towards the next object
     **************************************************/
    private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay) throws Exception {

        Vector normal = geometry.getNormal(point);
        normal.scale(-2);
        point.add(normal);

        if (geometry instanceof FlatGeometry){
            return new Ray (point, inRay.getDirection());
        } else {
            return new Ray (point, inRay.getDirection());
        }

    }
    /*************************************************
     * FUNCTION
     * constructReflectedRay
     * PARAMETERS
     * Vector, point3d, Ray
     * RETURN VALUE
     * Ray
     * MEANING
     * This function calculate the reflected ray from the surface
     **************************************************/
    private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay) throws Exception {

        Vector l = inRay.getDirection();
        l.normalize();

        normal.scale(-2 * l.dotProduct(normal));
        l.add(normal);

        Vector R = new Vector(l);
        R.normalize();

        point.add(normal);

        Ray reflectedRay = new Ray(point, R);

        return reflectedRay;
    }

    /*************************************************
     * FUNCTION
     * calcSpecularComp
     * PARAMETERS
     * double ks, Vector v, Vector normal,
     * Vector l, double shininess, Color lightIntensity
     * RETURN VALUE
     * color
     * MEANING
     * This function calculate the specular factor and change the color byy it
     **************************************************/
    private Color calcSpecularComp(double ks, Vector v, Vector normal,
                                    Vector l, double shininess, Color lightIntensity) throws Exception {
        Vector r = new Vector(normal);
        r.scale(-2*normal.dotProduct(l));
        r.add(l);
        r.normalize();
        v.normalize();
        double specular = ks*Math.pow(r.dotProduct(v),shininess);
        if(specular < 0)
        {specular *= -1;}
        return new Color((int)(lightIntensity.getRed()* specular)%256 ,
                (int)(lightIntensity.getGreen()*specular)%256,
                (int)(lightIntensity.getBlue()*specular)%256);
    };

    /*************************************************
     * FUNCTION
     * calcDiffusiveComp
     * PARAMETERS
     * double kd, Vector normal, Vector l,
     * Color lightIntensity
     * RETURN VALUE
     * color
     * MEANING
     * This function calculate the diffusive factor and change the color byy it
     **************************************************/
     private Color calcDiffusiveComp(double kd, Vector normal, Vector l,
                                     Color lightIntensity)
     {
         double dif = Math.abs(kd*normal.dotProduct(l));

         return new Color((int)(lightIntensity.getRed()*dif)%256 ,
                 (int)(lightIntensity.getGreen()*dif)%256,
                 (int)(lightIntensity.getBlue()*dif)%256);
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

    /*************************************************
     * FUNCTION
     * addColors
     * PARAMETERS
     * color
     * RETURN VALUE
     * color
     * MEANING
     * this functions get two different color and add them one to each other
     **************************************************/
    private Color addColors(Color a, Color b){

        int red = a.getRed() + b.getRed();
        if (red > 255) red = 255;

        int green = a.getGreen() + b.getGreen();
        if (green > 255) green = 255;

        int blue = a.getBlue() + b.getBlue();
        if (blue > 255) blue = 255;

        Color x = new Color (red, green, blue);

        return x;
    }

}