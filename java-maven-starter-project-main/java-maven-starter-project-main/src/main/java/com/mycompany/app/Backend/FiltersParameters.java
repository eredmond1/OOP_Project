package com.mycompany.app.Backend;

public class FiltersParameters {


        public String zoning = null;
        public Integer minYearBuilt = null;
        public  Integer maxYearBuilt = null;
        public Float minLotSize;
        public Float MaxLotSize;
        public Float minTotalGrossArea;
        public Float maxTotalGrossArea;


        public Float getMinTotalGrossArea() {
            return minTotalGrossArea;
        }

        public void setMinTotalGrossArea(Float minTotalGrossArea) {
            this.minTotalGrossArea = minTotalGrossArea;
        }

        public Float getMaxTotalGrossArea() {
            return maxTotalGrossArea;
        }

        public void setMaxTotalGrossArea(Float maxTotalGrossArea) {
            this.maxTotalGrossArea = maxTotalGrossArea;
        }

        public Float getMinLotSize() {
            return minLotSize;
        }

        public void setMinLotSize(Float minLotSize) {
            this.minLotSize = minLotSize;
        }

        public Float getMaxLotSize() {
            return MaxLotSize;
        }

        public void setMaxLotSize(Float maxLotSize) {
            MaxLotSize = maxLotSize;
        }

        public Integer getMinYearBuilt() {
            return minYearBuilt;
        }

        public void setMinYearBuilt(Integer minYearBuilt) {
            this.minYearBuilt = minYearBuilt;
        }

        public String getZoning() {
            return zoning;
        }

        public void setZoning(String zoning) {
            this.zoning = zoning;
        }

        public Integer getMaxYearBuilt() {
            return maxYearBuilt;
        }

        public void setMaxYearBuilt(Integer maxYearBuilt) {
            this.maxYearBuilt = maxYearBuilt;
        }

        public Float getMinAssessedValue() {
            return minAssessedValue;
        }

        public void setMinAssessedValue(Float minAssessedValue) {
            this.minAssessedValue = minAssessedValue;
        }

        public Float getMaxAssessedValue() {
            return maxAssessedValue;
        }

        public void setMaxAssessedValue(Float maxAssessedValue) {
            this.maxAssessedValue = maxAssessedValue;
        }

        public String getNeighborhood() {
            return neighborhood;
        }

        public void setNeighborhood(String neighborhood) {
            this.neighborhood = neighborhood;
        }

        public Float minAssessedValue = null;
        public Float maxAssessedValue = null;
        public String neighborhood = null;

        public FiltersParameters() {}

        public FiltersParameters(String zoning, Integer minYearBuilt, Integer maxYearBuilt, Float minAssessedValue, Float maxAssessedValue,
                                 String neighborhood, Float minLotSize, Float maxLotSize, Float minTotalGrossArea, Float maxTotalGrossArea){
            this.zoning = zoning;
            this.minYearBuilt = minYearBuilt;
            this.maxYearBuilt = maxYearBuilt;
            this.minAssessedValue = minAssessedValue;
            this.maxAssessedValue = maxAssessedValue;
            this.neighborhood = neighborhood;
            this.minLotSize = minLotSize;
            this.MaxLotSize = maxLotSize;
            this.minTotalGrossArea = minTotalGrossArea;
            this.maxTotalGrossArea = maxTotalGrossArea;

        }
    }

