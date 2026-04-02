package com.mycompany.app.Backend;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllProperty {
    List<Property> propertyList;

    public List<Property> getData (String filename) throws IOException {
        int lines = 0;
//        checkFile(filename);
        FileReader file = new FileReader(filename);
        BufferedReader buffRead = new BufferedReader(file);
        String record = "";
        List<Property>  object_record = new ArrayList<>();
        int counter = 0;
        record = buffRead.readLine(); //handles the headers
        try {
            while ((record = buffRead.readLine()) != null) {
                String[] data = parseCSVLine(record); // this is going to get the line records

                //TODO: We should look at filtering out houses with no address

                Property property = new Property(data); // make the object from the record
                object_record.add(property);//add record to the List of property assisments

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        buffRead.close();
        this.setPropertyList(object_record);
        return object_record;// returns the List of propert assisments so it can be set
    }


    public static String[] parseCSVLine (String record){
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < record.length(); i++) {
            char c = record.charAt(i);

            if (c == '"') {
                if (inQuotes) {
                    if (i + 1 < record.length() && record.charAt(i + 1) == '"') {
                        // Escaped quote ("")
                        current.append('"');
                        i++; // Skip the second quote
                    } else {
                        // Closing quote
                        inQuotes = false;
                    }
                } else {
                    // Opening quote (only valid at start of field)
                    if (!(current.length() == 0)) {
                        throw new IllegalArgumentException("Quote in unquoted field");
                    }
                    inQuotes = true;
                }
            } else if (c == ',' && !inQuotes) {
                // End of field
                fields.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }

        if (inQuotes) {
            // Missing closing quote
            throw new IllegalArgumentException("Unterminated quoted field");
        }

        // Add last field
        fields.add(current.toString());

        // Convert ArrayList to String array.
        return fields.toArray(new String[0]);
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public FilterResult filterPropertys (FiltersParameters filter){
        Map<Integer, Property> dataMap = new HashMap<>();
        Integer count  = 0;
        Boolean maxSize = false;
        for(Property property: this.propertyList){
            if (filterSingle(property, filter)){
                dataMap.put(property.getAccountNumber(), property);
                count++;
            }
            if (count == 10000){
                maxSize = true;
                break;
            }
        }
        if (dataMap.size() == 0){
            FilterResult filterResult = new FilterResult(null, FilterResult.FilterStatus.NO_RESULT);
            return filterResult;

        } else if (maxSize) {
            FilterResult filterResult = new FilterResult(dataMap, FilterResult.FilterStatus.FIRST_10000);
            return filterResult;
        }
        else {
            FilterResult filterResult = new FilterResult(dataMap, FilterResult.FilterStatus.SUCCESS);
            return filterResult;
        }

    }

    public Boolean filterSingle(Property property, FiltersParameters filtersParameters){
        if (filtersParameters.getZoning() != null) {
            String zoning = property.getHousing().getZoning();

            if (zoning == null ||
                    !zoning.equalsIgnoreCase(filtersParameters.getZoning())) {
                return false;
            }
        }
        if(filtersParameters.getMinYearBuilt() != null){
            Integer year = property.housing.getYearBuild();
            if( year == null || year < filtersParameters.getMinYearBuilt() ){
                return false;
            }
        }
        if (filtersParameters.getMaxYearBuilt() != null){
            Integer year = property.housing.getYearBuild();
            if(year == null || year > filtersParameters.getMaxYearBuilt()){
                return false;
            }
        }
        if (filtersParameters.minAssessedValue != null){
            Float value = property.housing.getAssessedValue();
            if( value == null || value < filtersParameters.getMinAssessedValue()){
                return false;
            }
        }
        if (filtersParameters.maxAssessedValue != null){
            Float value = property.housing.getAssessedValue();
            if (value == null || value > filtersParameters.getMaxAssessedValue()){
                return false;
            }
        }

        if (filtersParameters.getNeighborhood() != null) {
            String neighborhood = property.getNeighborhood().getNeighborhood();
            if (neighborhood == null ||
                    !filtersParameters.getNeighborhood().equalsIgnoreCase(neighborhood)) {
                return false;
            }
        }

        if(filtersParameters.minLotSize != null){
            Float size = property.housing.getLotSize();
            if( size == null || size < filtersParameters.getMinLotSize()){
                return false;
            }
        }

        if(filtersParameters.getMaxLotSize() != null){
            Float size = property.housing.getLotSize();
            if(size == null || size> filtersParameters.getMaxLotSize()){
                return false;
            }
        }

        if(filtersParameters.getMinTotalGrossArea() != null){
            Float gross = property.housing.getGrossTotalArea();
            if(gross == null || gross < filtersParameters.getMinTotalGrossArea()){
                return false;
            }
        }

        if(filtersParameters.getMaxTotalGrossArea() != null){
            Float gross = property.housing.getGrossTotalArea();
            if(gross== null || gross > filtersParameters.getMaxTotalGrossArea()){
                return false;
            }
        }
        return true;

    }
}


