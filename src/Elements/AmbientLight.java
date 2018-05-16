package Elements;

import java.awt.*;

public class AmbientLight extends Light {

    double _Ka;

    // ***************** Constructors ********************** //
    public AmbientLight() {
        _color = new Color(255, 255, 255);
        _Ka = 0.1;
    }

    public AmbientLight(AmbientLight aLight) {
        this._color = aLight.getColor();
        this._Ka = aLight.getKa();
    }

    public AmbientLight(int r, int g, int b) {
        this._color = new Color(r, g, b);
        this._Ka = 0.1;
    }

    public AmbientLight(Color _color, double ka) {
        this._color = _color;
        this._Ka = ka;
    }

    // ***************** Getters/Setters ********************** //
    public Color getColor() {
        return _color;
    }

    public void setColor(Color color) {
        this._color = color;
    }

    public double getKa() {
        return _Ka;
    }

    public Color getIntensity() {
        return new Color((int) (_color.getRed() * _Ka),
                (int) (_color.getGreen() * _Ka),
                (int) (_color.getBlue() * _Ka));

    }
}
