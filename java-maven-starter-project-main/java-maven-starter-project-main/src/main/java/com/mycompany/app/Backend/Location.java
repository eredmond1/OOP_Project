package com.mycompany.app.Backend;

/**
 * Represents the geographic coordinates of a property record.
 * This class stores latitude and longitude values parsed from a CSV input.
 */
public class Location {
    Double latitude;
    Double longitude;

    /**
     * Constructs a Location object from a CSV record.
     * Parses latitude and longitude values from the record array.
     * Empty strings are converted to null.
     *
     * @param record the CSV record array containing coordinate fields
     */
    public Location(String[] record){
        this.latitude = record[16].isEmpty()? null: Double.parseDouble(record[16]);
        this.longitude =record[17].isEmpty()? null : Double.parseDouble(record[17]);
    }

    /**
     * Returns the latitude coordinate.
     *
     * @return the latitude value, or null if unavailable
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude coordinate.
     *
     * @param latitude the latitude value to assign
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns the longitude coordinate.
     *
     * @return the longitude value, or null if unavailable
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude coordinate.
     *
     * @param longitude the longitude value to assign
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
