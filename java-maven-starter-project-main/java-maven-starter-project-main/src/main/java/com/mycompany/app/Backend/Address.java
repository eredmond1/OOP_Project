package com.mycompany.app.Backend;


/**
 * Represents an address component for a property record.
 * Stores suite, house number, and street name values parsed from a CSV record.
 */
public class Address {
    String suite;
    Integer houseNumber;
    String streetName;

    /**
     * Returns the house number portion of the address.
     *
     * @return the house number, or null if not available
     */
    public Integer getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets the house number portion of the address.
     *
     * @param houseNumber the house number to assign
     */
    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Returns the street name portion of the address.
     *
     * @return the street name, or null if not available
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the street name portion of the address.
     *
     * @param streetName the street name to assign
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * Returns the suite identifier for the address.
     *
     * @return the suite identifier, or null if not available
     */
    public String getSuite() {
        return suite;
    }

    /**
     * Sets the suite identifier for the address.
     *
     * @param suite the suite identifier to assign
     */
    public void setSuite(String suite) {
        this.suite = suite;
    }

    /**
     * Constructs an Address object from a CSV record.
     * Parses suite, house number, and street name values from the supplied record.
     * Empty string values are converted to null.
     *
     * @param record the CSV record array containing address fields
     */
    public Address(String[] record){
        this.suite = record[1].isEmpty()? null: record[1];
        this.houseNumber = record[2].isEmpty()? null: Integer.parseInt(record[2]);
        this.streetName = record[3].isEmpty()? null: record[3];
    }
}

