package com.kevinthelago.search.linear;

import com.kevinthelago.Algorithm;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.kevinthelago.Util.*;

public class ForLoopIntegerList implements Algorithm {
    public static int search(List<Integer> integers, Integer x) {
        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i).equals(x)) {
                return i;
            }
        }

        return -1;
    }

    public static int search(List<Integer> integers, int x) {
        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i).equals(x)) {
                return i;
            }
        }

        return -1;
    }

    public List<Long> run(int n) {
        List<Long> data = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<List<Integer>> integerListsList = new ArrayList<>();
            List<Integer> integers = new ArrayList<>();

            for (int j = 0; j < i; j++) {
                integerListsList.add(IntStream.rangeClosed(0, j).boxed().collect(Collectors.toList()));
                integers.add(random.nextInt(integerListsList.get(j).size()));
            }

            long startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                search(integerListsList.get(j), integers.get(j));
            }
            long endTime = System.nanoTime();

            System.out.println(formatMessage("Linear search ran " + i + " time(s) in: ") + formatNanoTime(startTime, endTime));
            data.add((endTime - startTime));
        }

        return data;
    }

    @Override
    public Scene createScene(List<Long> times) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of runs");
        yAxis.setLabel("Figure out how to display time (scaled)");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setCreateSymbols(false);
        lineChart.setTitle("Time Complexity of Linear Search");

        List<Long> estimated = linearRegressionAnalysis(times);
        lineChart.getData().add(generateSeries("Estimated Values", estimated));
        lineChart.getData().add(generateSeries("Uncleaned Data", times));

        Scene scene = new Scene(lineChart, 800, 600);
        scene.getStylesheets().add("com/kevinthelago/style/Chart.css");

        return scene;
    }
}
