package Scene;

import Elements.AmbientLight;
import Elements.Camera;
import Elements.LightSource;
import Geometrics.Geometry;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import Elements.LightSource;


public class Scene
{
    private Color _background;
    private AmbientLight _ambientLight;
    private List<Geometry> _geometries;
    private Camera _camera;
    private double _screenDistance;
    private List<LightSource> _lights;
    private String _sceneName;

    // ***************** Constructors ********************** //
    public Scene()
    {
        this._ambientLight = new AmbientLight();
        this._background = new Color(0,0,0);
        this._camera = new Camera();
        this._screenDistance = 100;
        this._geometries = new ArrayList<Geometry>();
        this._lights = new ArrayList<LightSource>();
        this._sceneName = "scene";
    }

    public Scene (Scene scene)
    {
        this._ambientLight = scene.getAmbientLight();
        this._background = scene.getBackground();
        this._camera = scene.getCamera();
        this._screenDistance = scene.getScreenDistance();
        this._geometries = scene._geometries;
        this._lights = scene._lights;
        this._sceneName = scene.getSceneName();
    }

    public Scene(AmbientLight aLight, Color background,
                 Camera camera, double screenDistance)
    {
        this._ambientLight = aLight;
        this._background = background;
        this._camera = camera;
        this._screenDistance = screenDistance;
        this._geometries = new ArrayList<Geometry>();
        this._lights = new ArrayList<LightSource>();
        this._sceneName = "scene";
    }

    // ***************** Getters/Setters ********************** //
    public Color getBackground(){ return _background;}
    public AmbientLight getAmbientLight() { return  _ambientLight;}
    public Camera getCamera() { return  _camera;}
    public String getSceneName() { return  _sceneName;}
    public double getScreenDistance() { return _screenDistance;}

    public void setBackground(Color background) { this._background = background;}
    public void setAmbientLight(AmbientLight ambientLight) { this._ambientLight = ambientLight;}
    public void setCamera(Camera camera) { this._camera = camera;}
    public void setSceneName(String sceneNAme) { this._sceneName = sceneNAme;}
    public void setScreenDistance(double screenDistance) { this._screenDistance = screenDistance;}

    // ***************** Operations ******************** //
    public void addGeometry(Geometry geometry) { _geometries.add(geometry);}
    public Iterator<Geometry> getGeometriesIterator() { return _geometries.iterator(); }

    public void addLight(LightSource light){_lights.add(light);}
    public Iterator<LightSource> getLightsIterator() {
        return _lights.iterator();
    }
}