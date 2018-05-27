package Primitives;

public class Coordinate {
    private double _coordinate;

    // ***************** Constructors ********************** //
    public Coordinate() {
        _coordinate = 0.0f;
    };

    public Coordinate(double coordinate) {_coordinate = coordinate;};

    public Coordinate(Coordinate coordinate) {
        _coordinate = coordinate.getCoordinate();
    };

    // ***************** Getters/Setters ********************** //
    public double getCoordinate() {
        return _coordinate;
    }

    public void setCoordinate(double _coordinate) {
        this._coordinate = _coordinate;
    }

    // ***************** Administration ******************** //

    /*************************************************
     * FUNCTION
     * compareTo
     * PARAMETERS
     * coordinate
     * RETURN VALUE
     * 0/1
     * MEANING
     * This functions compare between two coordinates to check if they are equals
     * If eqauls returns 1
     * Else return 0
     **************************************************/
    public int compareTo(Coordinate coordinate)
    {
        if (this._coordinate == coordinate.getCoordinate())
            return 1;
        else return 0;
    };

        @Override
        /*************************************************
         * FUNCTION
         * toString
         * PARAMETERS
         * none
         * RETURN VALUE
         * Format string of coordinate
         * MEANING
         * This function return the coordinate by specific format
         **************************************************/
        public String toString()
        {
            return String.format("%.2f",_coordinate);
        }

    // ***************** Operations ******************** //

    /*************************************************
     * FUNCTION
     * add
     * PARAMETERS
     * coordinate
     * RETURN VALUE
     * none
     * MEANING
     * This function add coordinate to an existing one
     **************************************************/
    public void add (Coordinate coordinate)
    {
        this._coordinate+=coordinate._coordinate;
    };

    /*************************************************
     * FUNCTION
     * subtract
     * PARAMETERS
     * coordinate
     * RETURN VALUE
     * none
     * MEANING
     * This function subtract coordinate to an existing one
     **************************************************/
    public void subtract (Coordinate coordinate)
    {
        this._coordinate-=coordinate._coordinate;
    };
}
