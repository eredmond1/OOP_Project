package com.mycompany.app.Backend;

public class Neighborhood {
    String ward;
    String neighborhood;

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getNeighborhoodID() {
        return neighborhoodID;
    }

    public void setNeighborhoodID(Integer neighborhoodID) {
        this.neighborhoodID = neighborhoodID;
    }

    Integer neighborhoodID;

    public Neighborhood(String[] record){
        this.ward = record[6].isEmpty()? null: record[6];
        this.neighborhood = record[5].isEmpty()? null: record[5];
        this.neighborhoodID = record[4].isEmpty()? null: Integer.parseInt(record[4]);
    }
}
