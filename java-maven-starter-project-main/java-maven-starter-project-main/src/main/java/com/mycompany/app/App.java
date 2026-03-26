
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
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.mycompany.app.Backend.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.get;
//import static jdk.internal.jrtfs.JrtFileAttributeView.AttrID.size;

public class App extends Application {

    private MapView mapView;

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        AllProperty data = new AllProperty();

        //load data
        data.getData("Edmonton_Property_Merged_2025.csv");

        //create the filter
        FiltersParameters params = new FiltersParameters();

        FilterResult result = data.filterPropertys(params);
        Map<Integer, Property> propertyData = result.getResults();
        Collection<Property> props = propertyData.values();

        List<Location> locList = new ArrayList<Location>();

        for(Property testProp : props) {
            Location propLoc = testProp.getLocation();
            locList.add(propLoc);
        }
        System.out.println("loc:" + locList);


        // set the title and size of the stage and show it
        stage.setTitle("Edmonton Real Estate");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.show();

        // create a JavaFX scene with a stack pane as the root node and add it to the scene
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);

        // Note: it is not best practice to store API keys in source code.
        // An API key is required to enable access to services, web maps, and web scenes hosted in ArcGIS Online.
        // If you haven't already, go to your developer dashboard to get your API key.
        // Please refer to https://developers.arcgis.com/java/get-started/ for more information
        String yourApiKey = "AAPTavaxvz6P-I7NYTuKAMQyw_A..9lG-4C1D5OcBphUAWYNnB4jMOmMycuHP8Zun3Xwb6-gBnKvyjfX1yoJWzz-5NRM0swUncxKZqs3Kj45IR9klq16C-D2CjfOkZF3ADnG8B62h2tKVm_2B72OmJ_sX-2w_xAZW9EcYD27oXQgAj4DveKklwFCQeV4nxuBRo0hKGwvATfa_ZroWPYoHg-1BibQgzZodCg4gednYqVAnnjiyMztj0D-i7FqPxDPoQf79iuxmPjMmsl_H87O9cg..AT1_QG91yvZ1";
        ArcGISRuntimeEnvironment.setApiKey(yourApiKey);

        // create a MapView to display the map and add it to the stack pane
        mapView = new MapView();
        stackPane.getChildren().add(mapView);

        // create an ArcGISMap with an imagery basemap
        ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_IMAGERY);

        // display the map by setting the map on the map view


        //set map to center around Edmonton
        map.setInitialViewpoint(new Viewpoint(53.5462,-113.4937, 50000));

        mapView.setMap(map);

        int i = 0;

        //plot point
        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();

        for (Location propLoc : locList){
            if(i< 10000) {
                i++;
                Double longitude = propLoc.getLongitude();
                Double latitude = propLoc.getLatitude();
                Point point = new Point(longitude, latitude, SpatialReferences.getWgs84());
                SimpleMarkerSymbol symbol = new SimpleMarkerSymbol(
                        SimpleMarkerSymbol.Style.CIRCLE,
                        Color.RED, 4.0f);
                Graphic graphic = new Graphic(point, symbol);
                graphicsOverlay.getGraphics().add(graphic);
            }
            else{
                break;
            }
        }

        mapView.addViewpointChangedListener(event->{
            double scale = mapView.getMapScale();
            float size;
            if(scale > 50000){
                size = 5.0f;    //map is zoomed out
            }
            else if (scale > 5000){
                size = 3.5f;    //medium amount of zoom
            }
            else{
                size = 2.75f;   //map is zoomed in
            }

        for (Graphic g : graphicsOverlay.getGraphics()) {
            SimpleMarkerSymbol symbol = (SimpleMarkerSymbol) g.getSymbol();
            symbol.setSize(size);
        }
        });

        mapView.getGraphicsOverlays().add(graphicsOverlay);

        /*Point tempPoint = new Point(-113.4938, 53.5461, SpatialReferences.getWgs84());
        SimpleMarkerSymbol tempSymbol = new SimpleMarkerSymbol(
                SimpleMarkerSymbol.Style.CIRCLE,
                        Color.RED, 12.0f);
        Graphic tempGraphic = new Graphic(tempPoint,tempSymbol);
        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        graphicsOverlay.getGraphics().add(tempGraphic);
        mapView.getGraphicsOverlays().add(graphicsOverlay);*/
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
