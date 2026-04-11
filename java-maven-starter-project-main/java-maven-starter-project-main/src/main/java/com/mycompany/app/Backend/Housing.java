package com.mycompany.app.Backend;

/**
 * Represents housing-specific information for a property.
 * This class stores zoning, lot size, gross total area, year built, and assessed value
 * values parsed from a property CSV record.
 */
public class Housing {
    String zoning;
    Float lotSize;
    Float grossTotalArea;
    Integer yearBuild;

    /**
     * Returns the zoning designation for the property.
     *
     * @return the zoning information, or null if not available
     */
    public String getZoning() {
        return zoning;
    }

    /**
     * Sets the zoning designation for the property.
     *
     * @param zoning the zoning information to assign
     */
    public void setZoning(String zoning) {
        this.zoning = zoning;
    }

    /**
     * Returns the lot size for the property.
     *
     * @return the lot size, or null if not available
     */
    public Float getLotSize() {
        return lotSize;
    }

    /**
     * Sets the lot size for the property.
     *
     * @param lotSize the lot size to assign
     */
    public void setLotSize(Float lotSize) {
        this.lotSize = lotSize;
    }

    /**
     * Returns the gross total area of the property.
     *
     * @return the gross total area, or null if not available
     */
    public Float getGrossTotalArea() {
        return grossTotalArea;
    }

    /**
     * Sets the gross total area of the property.
     *
     * @param grossTotalArea the gross total area to assign
     */
    public void setGrossTotalArea(Float grossTotalArea) {
        this.grossTotalArea = grossTotalArea;
    }

    /**
     * Returns the year the property was built.
     *
     * @return the year built, or null if not available
     */
    public Integer getYearBuild() {
        return yearBuild;
    }

    /**
     * Sets the year the property was built.
     *
     * @param yearBuild the year built to assign
     */
    public void setYearBuild(Integer yearBuild) {
        this.yearBuild = yearBuild;
    }

    /**
     * Returns the assessed value of the property.
     *
     * @return the assessed value, or null if not available
     */
    public Float getAssessedValue() {
        return assessedValue;
    }

    /**
     * Sets the assessed value for the property.
     *
     * @param assessedValue the assessed value to assign
     */
    public void setAssessedValue(Float assessedValue) {
        this.assessedValue = assessedValue;
    }

    Float assessedValue;



    /**
     * Constructs a Housing object from a CSV record.
     * Parses zoning, lot size, gross total area, year built, and assessed value fields.
     * Empty strings are converted to null.
     *
     * @param record the CSV record array containing housing data fields
     */
    public Housing(String[] record){
        this.zoning = record[20].isEmpty()? null: record[20];
        this.lotSize = record[21].isEmpty()? null: Float.parseFloat(record[21].replace(",", "").trim());;
        this.grossTotalArea = record[22].isEmpty()? null : Float.parseFloat(record[22]);// if empty make this null
        this.yearBuild= record[23].isEmpty()? null: Integer.parseInt(record[23].replace(",", "").trim());
        this.assessedValue = record[7].isEmpty()? null: Float.parseFloat(record[7].replace(",", "").trim());
    }
}
