package org.example;

import com.mycompany.app.Backend.AllProperty;
import com.mycompany.app.Backend.FilterResult;
import com.mycompany.app.Backend.FiltersParameters;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        AllProperty data = new AllProperty();
        final String filename = "Edmonton_Property_Merged_2025.csv";
        data.getData(filename);
//example of a  invlaid filter
//        Filters filters = new Filters(null, 2000, null, null, null,
//                null, null, null, null, null);

        //example of a valid filter
        FiltersParameters filtersParameters = new FiltersParameters(null, null, null, null, 500000f,
                "westmount", null, null, null, null);

        //exmample of no results
//        FiltersParameters filtersParameters = new FiltersParameters(null, 3000, null, null, null,
//                null, null, null, null, null);
        FilterResult result = data.filterPropertys(filtersParameters);
        System.out.println("this is a test in main");



    }
}