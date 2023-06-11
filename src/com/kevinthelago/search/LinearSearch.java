package com.kevinthelago.search;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.kevinthelago.Util.*;
import static com.kevinthelago.Util.random;

/**
 * This search algorithm assume the value exists and will be found.
 * */
public class LinearSearch extends Application {
    private static final int n = 100; // Number of runs
    private static final int m = 100000; // Multiplies the number of elements for each run

    public static void main(String[] args) {
        launch(args);
    }

    public static int search(List<Integer> integers, Integer x) {
        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i).equals(x)) {
                return i;
            }
        }

        return -1;
    }

    public List<Long> run(int n, int m) {
        List<Long> data = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> list = IntStream.rangeClosed(0, i * m).boxed().collect(Collectors.toList());
            int x = random.nextInt(list.size());

            long startTime = System.nanoTime();
            int result = search(list, x);
            long endTime = System.nanoTime();

            System.out.println(formatMessage("For Loop linear search searched " + result + " item(s) in a list of " + list.size() + " in: ") + formatNanoTime(startTime, endTime));
            data.add((endTime - startTime));
        }

        return data;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        primaryStage.setTitle("Time Complexity of Linear Search");
        xAxis.setLabel("Number of elements");
        lineChart.setTitle("Linear Search Results");
        Scene scene = new Scene(lineChart, 800, 600);
        scene.getStylesheets().add("com/kevinthelago/style/Chart.css");

        List<Long> times = run(n, m);

        lineChart.setCreateSymbols(false);
        lineChart.getData().add(generateSeries("Linear Search Result", m, times));
        lineChart.getData().add(generateSeries("Estimated Values", m, linearRegressionAnalysis(times)));

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
