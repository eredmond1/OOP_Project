package com.mycompany.app.Backend;

public class Location {
    Double latitude;
    Double longitude;

    public Location(String[] record){
        this.latitude = record[16].isEmpty()? null: Double.parseDouble(record[16]);
        this.longitude =record[17].isEmpty()? null : Double.parseDouble(record[17]);
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
