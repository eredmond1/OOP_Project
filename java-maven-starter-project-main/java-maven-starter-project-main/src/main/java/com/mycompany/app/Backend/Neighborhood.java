package com.mycompany.app.Backend;

/**
 * Represents neighborhood metadata for a property record.
 * Stores ward, neighborhood name, and neighborhood identifier values parsed
 * from a CSV record.
 */
public class Neighborhood {
    String ward;
    String neighborhood;

    /**
     * Returns the ward associated with the property.
     *
     * @return the ward name, or null if unavailable
     */
    public String getWard() {
        return ward;
    }

    /**
     * Sets the ward associated with the property.
     *
     * @param ward the ward name to assign
     */
    public void setWard(String ward) {
        this.ward = ward;
    }

    /**
     * Returns the neighborhood name.
     *
     * @return the neighborhood name, or null if unavailable
     */
    public String getNeighborhood() {
        return neighborhood;
    }

    /**
     * Sets the neighborhood name for this property.
     *
     * @param neighborhood the neighborhood name to assign
     */
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    /**
     * Returns the neighborhood identifier.
     *
     * @return the neighborhood ID, or null if unavailable
     */
    public Integer getNeighborhoodID() {
        return neighborhoodID;
    }

    /**
     * Sets the numeric identifier for the neighborhood.
     *
     * @param neighborhoodID the neighborhood ID to assign
     */
    public void setNeighborhoodID(Integer neighborhoodID) {
        this.neighborhoodID = neighborhoodID;
    }

    Integer neighborhoodID;

    /**
     * Constructs a Neighborhood object from a CSV record.
     * Parses ward, neighborhood name, and neighborhood ID values from the record.
     * Empty values are converted to null.
     *
     * @param record the CSV record array containing neighborhood fields
     */
    public Neighborhood(String[] record){
        this.ward = record[6].isEmpty()? null: record[6];
        this.neighborhood = record[5].isEmpty()? null: record[5];
        this.neighborhoodID = record[4].isEmpty()? null: Integer.parseInt(record[4]);
    }
}
