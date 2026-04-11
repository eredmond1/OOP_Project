package com.mycompany.app.Backend;


import java.util.Map;

/**
 * Encapsulates the output of a property filter operation.
 * Contains the filtered result map and a status that describes the outcome.
 */
public class FilterResult {
    public Map<Integer, Property> results;
    public FilterStatus status;

    /**
     * Constructs a FilterResult containing the matching properties and filter status.
     *
     * @param dataMap the map of filtered properties keyed by account number
     * @param status the status of the filter operation
     */
    public FilterResult(Map<Integer, Property> dataMap, FilterStatus status){
        this.results = dataMap;
        this.status = status;
    }

    /**
     * Describes the outcome of filtering property records.
     */
    public enum FilterStatus {
        SUCCESS,
        NO_RESULT,
        TOO_MANY_RESULTS,
        FIRST_10000

    }

    /**
     * Returns the filtered property results.
     *
     * @return the map of filtered properties keyed by account number
     */
    public Map<Integer, Property> getResults() {
        return results;
    }

    /**
     * Sets the filtered property results.
     *
     * @param results the map of filtered properties to set
     */
    public void setResults(Map<Integer, Property> results) {
        this.results = results;
    }

    /**
     * Returns the status of the filter operation.
     *
     * @return the filter status
     */
    public FilterStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the filter operation.
     *
     * @param status the filter status to set
     */
    public void setStatus(FilterStatus status) {
        this.status = status;
    }
}
