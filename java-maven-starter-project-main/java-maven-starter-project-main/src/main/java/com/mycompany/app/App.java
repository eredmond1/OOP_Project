
/**
 * Copyright 2019 Esri
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.mycompany.app;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.mycompany.app.Backend.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;

//Devin Addition
import javafx.scene.layout.VBox;
import java.io.IOException;

public class App extends Application {

    private MapView mapView;

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        String yourApiKey = "AAPTavaxvz6P-I7NYTuKAMQyw_A..9lG-4C1D5OcBphUAWYNnB4jMOmMycuHP8Zun3Xwb6-gBnKvyjfX1yoJWzz-5NRM0swUncxKZqs3Kj45IR9klq16C-D2CjfOkZF3ADnG8B62h2tKVm_2B72OmJ_sX-2w_xAZW9EcYD27oXQgAj4DveKklwFCQeV4nxuBRo0hKGwvATfa_ZroWPYoHg-1BibQgzZodCg4gednYqVAnnjiyMztj0D-i7FqPxDPoQf79iuxmPjMmsl_H87O9cg..AT1_QG91yvZ1";
        ArcGISRuntimeEnvironment.setApiKey(yourApiKey);

        stage.setTitle("Edmonton Real Estate");
        stage.setWidth(1000);
        stage.setHeight(700);
        stage.show();


        BorderPane rootPane = new BorderPane();
        Scene scene = new Scene(rootPane);
        stage.setScene(scene);


        mapView = new MapView();
        rootPane.setCenter(mapView);


        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(graphicsOverlay);

        // backend call
        AllProperty allProperty = new AllProperty();
        try {
            allProperty.getData("Edmonton_Property_Merged_2025.csv");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //UI generation
        TextField minYearField = new TextField();
        minYearField.setPromptText("Min Year Built");

        TextField maxYearField = new TextField();
        maxYearField.setPromptText("Max Year Built");

        TextField minLotField = new TextField();
        minLotField.setPromptText("Min Lot Size");

        TextField maxLotField = new TextField();
        maxLotField.setPromptText("Max Lot Size");

        TextField minGrossField = new TextField();
        minGrossField.setPromptText("Min Total Gross Area");

        TextField maxGrossField = new TextField();
        maxGrossField.setPromptText("Max Total Gross Area");

        TextField minAssessedField = new TextField();
        minAssessedField.setPromptText("Min Assessed Value");

        TextField maxAssessedField = new TextField();
        maxAssessedField.setPromptText("Max Assessed Value");

        TextField zoningField = new TextField();
        zoningField.setPromptText("Zoning");

        TextField neighborhoodField = new TextField();
        neighborhoodField.setPromptText("Neighborhood");

        Button applyButton = new Button("Apply Filters");

        VBox filterBox = new VBox(8,
                minYearField, maxYearField,
                minLotField, maxLotField,
                minGrossField, maxGrossField,
                minAssessedField, maxAssessedField,
                zoningField, neighborhoodField,
                applyButton
        );
        filterBox.setPadding(new Insets(10));
        filterBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc;");
        filterBox.setPrefWidth(220);

        rootPane.setLeft(filterBox);


        ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_IMAGERY);
        map.setInitialViewpoint(new Viewpoint(53.5462, -113.4937, 100000));
        mapView.setMap(map);


        applyButton.setOnAction(e -> {
            FiltersParameters params = new FiltersParameters();

            params.setMinYearBuilt(minYearField.getText().isEmpty() ? null : Integer.parseInt(minYearField.getText()));
            params.setMaxYearBuilt(maxYearField.getText().isEmpty() ? null : Integer.parseInt(maxYearField.getText()));
            params.setMinLotSize(minLotField.getText().isEmpty() ? null : Float.parseFloat(minLotField.getText()));
            params.setMaxLotSize(maxLotField.getText().isEmpty() ? null : Float.parseFloat(maxLotField.getText()));
            params.setMinTotalGrossArea(minGrossField.getText().isEmpty() ? null : Float.parseFloat(minGrossField.getText()));
            params.setMaxTotalGrossArea(maxGrossField.getText().isEmpty() ? null : Float.parseFloat(maxGrossField.getText()));
            params.setMinAssessedValue(minAssessedField.getText().isEmpty() ? null : Float.parseFloat(minAssessedField.getText()));
            params.setMaxAssessedValue(maxAssessedField.getText().isEmpty() ? null : Float.parseFloat(maxAssessedField.getText()));
            params.setZoning(zoningField.getText().isEmpty() ? null : zoningField.getText());
            params.setNeighborhood(neighborhoodField.getText().isEmpty() ? null : neighborhoodField.getText());

            // Filter properties
            FilterResult result = allProperty.filterPropertys(params);

            List<Property> filteredList = new ArrayList<>();
            if (result.getResults() != null) {
                filteredList.addAll(result.getResults().values());
            }

            // Clear previous graphics and display filtered properties
            displayProperties(filteredList, graphicsOverlay);
        });


    }


    private void displayProperties(List<Property> properties, GraphicsOverlay overlay) {
        overlay.getGraphics().clear();
        for (Property p : properties) {
            Location loc = p.getLocation();
            if (loc == null || loc.getLatitude() == null || loc.getLongitude() == null) continue;

            Point point = new Point(loc.getLongitude(), loc.getLatitude(), SpatialReferences.getWgs84());


            SimpleMarkerSymbol symbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 4);
            Graphic graphic = new Graphic(point, symbol);

            overlay.getGraphics().add(graphic);
        }
    }

    /**
     * Stops and releases all resources used in application.
     */
    @Override
    public void stop() {

        if (mapView != null) {
            mapView.dispose();
        }
    }
}
