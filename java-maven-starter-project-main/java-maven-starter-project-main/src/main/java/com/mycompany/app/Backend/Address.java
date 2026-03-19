package com.mycompany.app.Backend;


public class Address {
    String suite;
    Integer houseNumber;
    String streetName;

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public Address(String[] record){
        this.suite = record[1].isEmpty()? null: record[1];
        this.houseNumber = record[2].isEmpty()? null: Integer.parseInt(record[2]);
        this.streetName = record[3].isEmpty()? null: record[3];
    }
}

