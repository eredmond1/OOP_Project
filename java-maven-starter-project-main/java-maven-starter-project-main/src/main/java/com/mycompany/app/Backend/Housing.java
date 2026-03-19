package com.mycompany.app.Backend;

public class Housing {
    String zoning;
    Float lotSize;
    Float grossTotalArea;
    Integer yearBuild;

    public String getZoning() {
        return zoning;
    }

    public void setZoning(String zoning) {
        this.zoning = zoning;
    }

    public Float getLotSize() {
        return lotSize;
    }

    public void setLotSize(Float lotSize) {
        this.lotSize = lotSize;
    }

    public Float getGrossTotalArea() {
        return grossTotalArea;
    }

    public void setGrossTotalArea(Float grossTotalArea) {
        this.grossTotalArea = grossTotalArea;
    }

    public Integer getYearBuild() {
        return yearBuild;
    }

    public void setYearBuild(Integer yearBuild) {
        this.yearBuild = yearBuild;
    }

    public Float getAssessedValue() {
        return assessedValue;
    }

    public void setAssessedValue(Float assessedValue) {
        this.assessedValue = assessedValue;
    }

    Float assessedValue;



    public Housing(String[] record){
        this.zoning = record[20].isEmpty()? null: record[20];
        this.lotSize = record[21].isEmpty()? null: Float.parseFloat(record[21].replace(",", "").trim());;
        this.grossTotalArea = record[22].isEmpty()? null : Float.parseFloat(record[22]);// if empty make this null
        this.yearBuild= record[23].isEmpty()? null: Integer.parseInt(record[23].replace(",", "").trim());
        this.assessedValue = record[7].isEmpty()? null: Float.parseFloat(record[7].replace(",", "").trim());
    }
}
