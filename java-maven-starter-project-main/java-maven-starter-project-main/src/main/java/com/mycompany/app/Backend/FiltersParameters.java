package com.mycompany.app.Backend;

/**
 * Holds filter criteria used to select property records.
 * Fields may remain null to indicate that a filter is not active.
 */
public class FiltersParameters {


        public String zoning = null;
        public Integer minYearBuilt = null;
        public  Integer maxYearBuilt = null;
        public Float minLotSize;
        public Float MaxLotSize;
        public Float minTotalGrossArea;
        public Float maxTotalGrossArea;


        /**
         * Returns the minimum total gross area filter.
         *
         * @return the minimum total gross area, or null if not set
         */
        public Float getMinTotalGrossArea() {
            return minTotalGrossArea;
        }

        /**
         * Sets the minimum total gross area filter.
         *
         * @param minTotalGrossArea the minimum total gross area to filter by
         */
        public void setMinTotalGrossArea(Float minTotalGrossArea) {
            this.minTotalGrossArea = minTotalGrossArea;
        }

        /**
         * Returns the maximum total gross area filter.
         *
         * @return the maximum total gross area, or null if not set
         */
        public Float getMaxTotalGrossArea() {
            return maxTotalGrossArea;
        }

        /**
         * Sets the maximum total gross area filter.
         *
         * @param maxTotalGrossArea the maximum total gross area to filter by
         */
        public void setMaxTotalGrossArea(Float maxTotalGrossArea) {
            this.maxTotalGrossArea = maxTotalGrossArea;
        }

        /**
         * Returns the minimum lot size filter.
         *
         * @return the minimum lot size, or null if not set
         */
        public Float getMinLotSize() {
            return minLotSize;
        }

        /**
         * Sets the minimum lot size filter.
         *
         * @param minLotSize the minimum lot size to filter by
         */
        public void setMinLotSize(Float minLotSize) {
            this.minLotSize = minLotSize;
        }

        /**
         * Returns the maximum lot size filter.
         *
         * @return the maximum lot size, or null if not set
         */
        public Float getMaxLotSize() {
            return MaxLotSize;
        }

        /**
         * Sets the maximum lot size filter.
         *
         * @param maxLotSize the maximum lot size to filter by
         */
        public void setMaxLotSize(Float maxLotSize) {
            MaxLotSize = maxLotSize;
        }

        /**
         * Returns the minimum year built filter.
         *
         * @return the minimum year built, or null if not set
         */
        public Integer getMinYearBuilt() {
            return minYearBuilt;
        }

        /**
         * Sets the minimum year built filter.
         *
         * @param minYearBuilt the minimum year built to filter by
         */
        public void setMinYearBuilt(Integer minYearBuilt) {
            this.minYearBuilt = minYearBuilt;
        }

        /**
         * Returns the zoning filter.
         *
         * @return the zoning string, or null if not set
         */
        public String getZoning() {
            return zoning;
        }

        /**
         * Sets the zoning filter.
         *
         * @param zoning the zoning value to filter by
         */
        public void setZoning(String zoning) {
            this.zoning = zoning;
        }

        /**
         * Returns the maximum year built filter.
         *
         * @return the maximum year built, or null if not set
         */
        public Integer getMaxYearBuilt() {
            return maxYearBuilt;
        }

        /**
         * Sets the maximum year built filter.
         *
         * @param maxYearBuilt the maximum year built to filter by
         */
        public void setMaxYearBuilt(Integer maxYearBuilt) {
            this.maxYearBuilt = maxYearBuilt;
        }

        /**
         * Returns the minimum assessed value filter.
         *
         * @return the minimum assessed value, or null if not set
         */
        public Float getMinAssessedValue() {
            return minAssessedValue;
        }

        /**
         * Sets the minimum assessed value filter.
         *
         * @param minAssessedValue the minimum assessed value to filter by
         */
        public void setMinAssessedValue(Float minAssessedValue) {
            this.minAssessedValue = minAssessedValue;
        }

        /**
         * Returns the maximum assessed value filter.
         *
         * @return the maximum assessed value, or null if not set
         */
        public Float getMaxAssessedValue() {
            return maxAssessedValue;
        }

        /**
         * Sets the maximum assessed value filter.
         *
         * @param maxAssessedValue the maximum assessed value to filter by
         */
        public void setMaxAssessedValue(Float maxAssessedValue) {
            this.maxAssessedValue = maxAssessedValue;
        }

        /**
         * Returns the neighborhood filter.
         *
         * @return the neighborhood name, or null if not set
         */
        public String getNeighborhood() {
            return neighborhood;
        }

        /**
         * Sets the neighborhood filter.
         *
         * @param neighborhood the neighborhood value to filter by
         */
        public void setNeighborhood(String neighborhood) {
            this.neighborhood = neighborhood;
        }

        public Float minAssessedValue = null;
        public Float maxAssessedValue = null;
        public String neighborhood = null;

        /**
         * Creates an empty FiltersParameters instance with all filters unset.
         */
        public FiltersParameters() {}

        /**
         * Creates a FiltersParameters instance initialized with the provided filter values.
         *
         * @param zoning the zoning filter
         * @param minYearBuilt the minimum year built filter
         * @param maxYearBuilt the maximum year built filter
         * @param minAssessedValue the minimum assessed value filter
         * @param maxAssessedValue the maximum assessed value filter
         * @param neighborhood the neighborhood filter
         * @param minLotSize the minimum lot size filter
         * @param maxLotSize the maximum lot size filter
         * @param minTotalGrossArea the minimum total gross area filter
         * @param maxTotalGrossArea the maximum total gross area filter
         */
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

