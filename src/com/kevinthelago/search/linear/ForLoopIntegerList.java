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

    public List<Integer> run(int n) {
        List<Integer> data = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> list = IntStream.rangeClosed(0, i * 100000).boxed().collect(Collectors.toList());
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
        xAxis.setLabel("Number of runs");
        yAxis.setLabel("Figure out how to display time (scaled)");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setCreateSymbols(false);
        lineChart.setTitle("Time Complexity of Linear Search");

        List<Integer> estimated = linearRegressionAnalysis(times);
        lineChart.getData().add(generateSeries("Estimated Values", estimated));
        lineChart.getData().add(generateSeries("Uncleaned Data", times));

        Scene scene = new Scene(lineChart, 800, 600);
        scene.getStylesheets().add("com/kevinthelago/style/Chart.css");

        return scene;
    }
}
