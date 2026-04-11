
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
import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.IdentifyGraphicsOverlayResult;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.mycompany.app.Backend.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

//Devin Addition
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.Map;

/**
 * Main JavaFX application for displaying Edmonton property data on an ArcGIS map.
 * The application loads property records, renders property markers, and provides
 * filter controls and property detail interaction for users.
 */
public class App extends Application {

    //Presentation notes:
    //Create all label field for righ side lookup box

    private MapView mapView;

    private Label results;

    private Label ID;
    private Label yearField;
    private Label lotField;
    private Label grossField;
    private Label assessField;
    private Label zoneField;
    private Label addressField;
    private Label neighborField;

    //Presentation notes:
    //Design limitation: I had to turn this into a map using the acc't number cause the display kept breaking
    //when I tried to add functionality to display house details on click
    private Map<Integer, Property> propertyMap = new HashMap<>();

    /**
     * Application entry point that launches the JavaFX runtime.
     *
     * @param args command-line arguments passed to the application (unused)
     */
    public static void main(String[] args) {

        Application.launch(args);
    }

    /**
     * Initializes the JavaFX application window, configures the ArcGIS map view,
     * sets up the filter UI, and attaches interaction handlers for selecting properties.
     *
     * @param stage the primary stage for the application window
     */
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

        //Presentation notes:
        //Inverted scaling to shrink dots as you zoom out, avoids turning the map into a giant red splotch
        mapView.addViewpointChangedListener(event->{
            double scale = mapView.getMapScale();
            float size;
            if(scale > 50000){
                size = 3.5f;    //map is zoomed out
            }
            else if (scale > 5000){
                size = 7.0f;    //medium amount of zoom
            }
            else{
                size = 14.0f;   //map is zoomed in
            }

            for (Graphic g : graphicsOverlay.getGraphics()) {
                SimpleMarkerSymbol symbol = (SimpleMarkerSymbol) g.getSymbol();
                symbol.setSize(size);
            }
        });

        //Presentation notes:
        //run when user clicks, get pixel coordinates within 20 pixel radius

        mapView.setOnMouseClicked(event -> {
            Point2D screenPoint = new Point2D(event.getX(), event.getY());

            ListenableFuture<IdentifyGraphicsOverlayResult> future =
                    mapView.identifyGraphicsOverlayAsync(graphicsOverlay, screenPoint, 20, false);

            //donelistetner only returns when done, stops ui from frrezing as it searches
            future.addDoneListener(() -> {
                try {
                    IdentifyGraphicsOverlayResult result = future.get();
                    if (!result.getGraphics().isEmpty()) {
                        Graphic clickedGraphic = result.getGraphics().get(0);

                        //look up the property by accountNumber in the mmap
                        Integer acctNum = (Integer) clickedGraphic.getAttributes().get("accountNumber");
                        Property p = propertyMap.get(acctNum);

                        if (p != null) {
                            updateHouseCard(p);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        });


        //backend call
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

        //no results on initial opening
        results = new Label(null);


        VBox filterBox = new VBox(8,
                minYearField, maxYearField,
                minLotField, maxLotField,
                minGrossField, maxGrossField,
                minAssessedField, maxAssessedField,
                zoningField, neighborhoodField,
                applyButton, results
        );
        filterBox.setPadding(new Insets(10));
        filterBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc;");
        filterBox.setPrefWidth(200);

        rootPane.setLeft(filterBox);

        Label SelectTitleLabel = new Label("Selected Property");
        SelectTitleLabel.setUnderline(true);
        SelectTitleLabel.setFont(Font.font("System", FontWeight.BOLD, 16));

        ID = new Label("House ID: ");
        yearField = new Label("Year Built: ");
        lotField = new Label("Lot size: ");
        grossField = new Label("Gross Area: ");
        assessField = new Label("Assessed Value: ");
        zoneField = new Label("Zoning: ");
        addressField = new Label("Address: ");
        addressField.setWrapText(true);
        neighborField = new Label("Neighborhood: ");
        neighborField.setWrapText(true);

        VBox houseCard = new VBox(8,SelectTitleLabel,
                ID,yearField,lotField,
                grossField,assessField,
                zoneField,addressField,neighborField);

        houseCard.setPadding(new Insets(10));
        houseCard.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc;");
        houseCard.setPrefWidth(200);

        rootPane.setRight(houseCard);





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


            //Presentation notes:
            //turn the map into a list by extracting only values with getresults
            List<Property> filteredList = new ArrayList<>();
            if (result.getResults() != null) {
                filteredList.addAll(result.getResults().values());
                if (filteredList.size() == 10000) {
                    results.setText("First " + filteredList.size() + " results returned");
                }
                else results.setText(filteredList.size() + " results returned");

            } else if (result.getResults() == null) {
                results.setText("No results returned");

            }


            //System.out.println("Filtered count: " + filteredList.size());

            // Clear previous graphics and display filtered properties
            displayProperties(filteredList, graphicsOverlay);
        });


    }


    /**
     * Displays the provided property list on the map by converting each valid property
     * location into a graphic marker. Existing markers are cleared before rendering new results.
     *
     * @param properties the filtered list of properties to show on the map
     * @param overlay the graphics overlay used to render property markers
     */
    private void displayProperties(List<Property> properties, GraphicsOverlay overlay) {
        overlay.getGraphics().clear();
        propertyMap.clear(); // reset for new filtered list (get rid of old data)


        //Presentation notes:
        //loop through properties, use a location object that skips if no coordinates are found
        //to add an account number for displaying on the map
        for (Property p : properties) {
            Location loc = p.getLocation();
            if (loc == null || loc.getLatitude() == null || loc.getLongitude() == null) continue;

            //add to map for click lookup
            propertyMap.put(p.getAccountNumber(), p);

            //create point and graphic
            Point point = new Point(loc.getLongitude(), loc.getLatitude(), SpatialReferences.getWgs84());
            SimpleMarkerSymbol symbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 4); // bigger marker
            Graphic graphic = new Graphic(point, symbol);

            //store accountNumber for map lookup

            //Presentation notes:
            //again had to do this cause arcgis couldn't handle the data of so many full points
            graphic.getAttributes().put("accountNumber", p.getAccountNumber());

            //finally display on map
            overlay.getGraphics().add(graphic);


            //System.out.println("Adding point: " + loc.getLatitude() + ", " + loc.getLongitude());
        }

        //System.out.println("Total points displayed: " + overlay.getGraphics().size());
    }

    //Presentation notes:
    //extract data from housing, nbhd, address
    /**
     * Updates the right-side details card with the selected property's information.
     * Displays housing, neighborhood, and address fields, using "N/A" for missing values.
     *
     * @param p the selected property whose details should be shown
     */
    private void updateHouseCard(Property p) {

        Housing h = p.getHousing();
        Neighborhood n = p.getNeighborhood();
        Address a = p.getAddress();



        if (h != null) {
            ID.setText("House ID: " + (p.getAccountNumber() != null ? p.getAccountNumber() : "N/A"));
            yearField.setText("Year Built: " + (h.getYearBuild() != null ? h.getYearBuild() : "N/A"));
            lotField.setText("Lot size: " + (h.getLotSize() != null ? h.getLotSize() : "N/A"));
            grossField.setText("Gross Area: " + (h.getGrossTotalArea() != null ? h.getGrossTotalArea() : "N/A"));
            assessField.setText("Assessed Value: " + (h.getAssessedValue() != null ? h.getAssessedValue() : "N/A"));
            zoneField.setText("Zoning: " + (h.getZoning() != null ? h.getZoning() : "N/A"));
        } else {
            ID.setText("House ID: N/A");
            yearField.setText("Year Built: N/A");
            lotField.setText("Lot size: N/A");
            grossField.setText("Gross Area: N/A");
            assessField.setText("Assessed Value: N/A");
            zoneField.setText("Zoning: N/A");
        }

        if (n != null) {
            neighborField.setText("Neighborhood: " +
                    (n.getNeighborhood() != null ? n.getNeighborhood() : "N/A"));
        } else {
            neighborField.setText("Neighborhood: N/A");
        }

        if (a != null) {
            addressField.setText("Address: " + (a.getHouseNumber() +" " + a.getStreetName()));
        } else {
            addressField.setText("Address: N/A");
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
