package com.mycompany.app.Backend;

public class Property {
    Integer accountNumber;
    Address address;
    Location location;
    Housing housing;
    Neighborhood neighborhood;


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Housing getHousing() {
        return housing;
    }

    public void setHousing(Housing housing) {
        this.housing = housing;
    }

    public Property(String[] record ){
        this.accountNumber = Integer.parseInt(record[0]);
        this.address = new Address(record);
        this.location = new Location((record));
        this.housing = new Housing(record);
        this.neighborhood = new Neighborhood(record);

    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }
}
