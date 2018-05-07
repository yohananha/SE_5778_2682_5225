package Geometrics;

import Primitives.*;

import java.awt.*;
import java.util.List;

public abstract class Geometry {

    private Material _material = new Material();
    private double _nShininess = 1;
    private Color _emmission = new Color(0, 0, 0);

    public abstract List<Point3D> FindIntersections(Ray ray);

    public abstract Vector getNormal(Point3D point);

    // ***************** Getters/Setters ********************** //
    public double getShininess() {
        return this._nShininess;
    }

    public Material getMaterial() {
        return this._material;
    }

    public Color getEmmission() {
        return this._emmission;
    }

    public void setShininess(double n) {
        this._nShininess = n;
    }

    public void setMaterial(Material _material) {
        this._material = _material;
    }

    public void setEmmission(Color emmission) {
        this._emmission = emmission;
    }

    public void setKs(double ks) {
        this._material.setKs(ks);
    }

    public void setKd(double kd) {
        this._material.setKd(kd);
    }

    public void setKr(double Kr) {
        this._material.setKr(Kr);
    }

    public void setKt(double Kt) {
        this._material.setKt(Kt);
    }
}
