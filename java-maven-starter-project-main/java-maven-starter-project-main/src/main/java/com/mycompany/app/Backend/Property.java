package com.mycompany.app.Backend;

/**
 * Represents a single property record with its related data.
 * This class aggregates an account number, address, geographic location,
 * housing details, and neighborhood metadata parsed from a CSV record.
 */
public class Property {
    Integer accountNumber;
    Address address;
    Location location;
    Housing housing;
    Neighborhood neighborhood;


    /**
     * Returns the address object associated with this property.
     *
     * @return the Address object, or null if not set
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address object for this property.
     *
     * @param address the Address object to assign
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns the geographic location of this property.
     *
     * @return the Location object, or null if not set
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the geographic location for this property.
     *
     * @param location the Location object to assign
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns the neighborhood metadata associated with this property.
     *
     * @return the Neighborhood object, or null if not set
     */
    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    /**
     * Sets the neighborhood metadata for this property.
     *
     * @param neighborhood the Neighborhood object to assign
     */
    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    /**
     * Returns the housing details for this property.
     *
     * @return the Housing object, or null if not set
     */
    public Housing getHousing() {
        return housing;
    }

    /**
     * Sets the housing details for this property.
     *
     * @param housing the Housing object to assign
     */
    public void setHousing(Housing housing) {
        this.housing = housing;
    }

    /**
     * Constructs a Property object from a CSV record.
     * Parses the account number, address, location, housing details, and neighborhood
     * information from the provided record array.
     *
     * @param record a CSV record as a string array
     */
    public Property(String[] record ){
        this.accountNumber = Integer.parseInt(record[0]);
        this.address = new Address(record);
        this.location = new Location((record));
        this.housing = new Housing(record);
        this.neighborhood = new Neighborhood(record);

    }

    /**
     * Returns the unique account number for this property.
     *
     * @return the account number
     */
    public Integer getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the unique account number for this property.
     *
     * @param accountNumber the account number to assign
     */
    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }
}
