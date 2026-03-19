package com.mycompany.app.Backend;


import java.util.Map;

public class FilterResult {
    public Map<Integer, Property> results;
    public FilterStatus status;

    public FilterResult(Map<Integer, Property> dataMap, FilterStatus status){
        this.results = dataMap;
        this.status = status;
    }
    public enum FilterStatus {
        SUCCESS,
        NO_RESULT,
        TOO_MANY_RESULTS,
        FIRST_10000

    }

    public Map<Integer, Property> getResults() {
        return results;
    }

    public void setResults(Map<Integer, Property> results) {
        this.results = results;
    }

    public FilterStatus getStatus() {
        return status;
    }

    public void setStatus(FilterStatus status) {
        this.status = status;
    }
}
