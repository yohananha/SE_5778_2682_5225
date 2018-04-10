package Primitives;

public class Coordinate {
    private double _coordinate;

    // ***************** Constructors ********************** //
    public Coordinate() {
        _coordinate = 0.0f;
    }

    ;

    public Coordinate(double coordinate) {
        _coordinate = coordinate;
    }

    ;

    public Coordinate(Coordinate coordinate) {
        _coordinate = coordinate._coordinate;
    }

    ;

    // ***************** Getters/Setters ********************** //
    public double getCoordinate() {
        return _coordinate;
    }

    public void setCoordinate(double _coordinate) {
        this._coordinate = _coordinate;
    }

    // ***************** Administration ******************** //
    public int compareTo(Coordinate coordinate)
    {
        if (this._coordinate == coordinate.getCoordinate())
            return 1;
        else return 0;
    };

    @Override
    public String toString()
    {
        return String.format("%.2f",_coordinate);
    }

    // ***************** Operations ******************** //
    public void add (Coordinate coordinate)
    {
        this._coordinate+=coordinate._coordinate;
    };
    public void subtract (Coordinate coordinate)
    {
        this._coordinate-=coordinate._coordinate;
    };
}
