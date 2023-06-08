package com.kevinthelago.search.linear;

import com.kevinthelago.search.LinearSearch;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.kevinthelago.Util.*;

public class ForLoopIntegerList implements LinearSearch.Algorithm {
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

    public List<Integer> run(int n, int m) {
        List<Integer> data = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> list = IntStream.rangeClosed(0, i * m).boxed().collect(Collectors.toList());
            int x = random.nextInt(list.size());
            int result = 0;

            long startTime = System.nanoTime();
            result = search(list, x);
            long endTime = System.nanoTime();

            System.out.println(formatMessage("For Loop linear search searched " + result + " item(s) in a list of " + list.size() + " in: ") + formatNanoTime(startTime, endTime));
            data.add((int) (endTime - startTime));
        }

        return data;
    }

    @Override
    public Scene createScene(List<Integer> times) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of elements");
        yAxis.setLabel("Time in Nanoseconds");
        int multiplier = 10000;

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setCreateSymbols(false);
        lineChart.setTitle("Time Complexity of Linear Search");

        List<Integer> estimated = linearRegressionAnalysis(times);
        lineChart.getData().add(generateSeries("Estimated Values", multiplier, estimated));
        lineChart.getData().add(generateSeries("Uncleaned Data", multiplier, times));

        Scene scene = new Scene(lineChart, 800, 600);
        scene.getStylesheets().add("com/kevinthelago/style/Chart.css");

        return scene;
    }
}
