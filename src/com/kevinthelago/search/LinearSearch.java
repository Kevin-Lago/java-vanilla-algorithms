package com.kevinthelago.search;

import com.kevinthelago.search.linear.ForLoopIntegerList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;

import static com.kevinthelago.Util.*;

/**
 * These search algorithms assume the value exists and will be found.
 * This is just a test to visualize the time complexity of different search methods and data types
 * */
public class LinearSearch extends Application {
    private static Random random = new Random();
    Stage window;
    Scene forLoopInteger, streamIntegerList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Linear Search Results");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of runs");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Time Complexity of Linear Search");

        int n = 100;
        int m = 100000;

        ForLoopIntegerList forLoopIntegerList = new ForLoopIntegerList();
        List<Integer> times = forLoopIntegerList.run(n, m);

        Button forLoopIntegerButton = new Button("For Loop Integer");
        VBox layout = new VBox(2);
        layout.getChildren().addAll(lineChart, forLoopIntegerButton);
        forLoopIntegerButton.setOnAction(e -> primaryStage.setScene(forLoopIntegerList.createScene(times)));
        Scene scene = new Scene(layout, 800, 800);
        scene.getStylesheets().add("com/kevinthelago/style/Chart.css");

        lineChart.setCreateSymbols(false);
        lineChart.getData().add(generateLinearSeries(n, 1));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public interface Algorithm {
        List<Integer> run(int n, int m);

        Scene createScene(List<Integer> times);
    }
}
