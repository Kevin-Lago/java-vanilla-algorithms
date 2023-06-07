package search;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static util.Util.*;

/**
 * These search algorithms assume the value exists and will be found.
 * This is just a test to visualize the time complexity of different search methods and data types
 * */
public class LinearSearch extends Application {
    private static Random random = new Random();

    public static void main(String[] args) {
        launch(args);
    }

    public static int linearIntegerListForLoopSearch(List<Integer> integers, Integer x) {
        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i).equals(x)) {
                return i;
            }
        }

        return -1;
    }

    public static int linearIntegerListStreamFilterSearch(List<Integer> integers, Integer x) {
        Integer result = integers.stream().filter(integer -> {
            return integer.equals(x);
        }).findFirst().orElse(null);

        return 0;
    }

    public static int linearIntegerArrayForLoopSearch(Integer[] integers, Integer x) {
        for (int i = 0; i < integers.length; i++) {
            if (integers[i].equals(x)) {
                return i;
            }
        }

        return -1;
    }

    public static int linearIntegerArrayStreamSearch(Integer[] integers, Integer x) {
        return Arrays.stream(integers).filter(integer -> {
            return integer.equals(x);
        }).findFirst().orElse(null);
    }

    public static int linearIntArrayForLoopSearch(int[] ints, int x) {
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == x) {
                return i;
            }
        }

        return -1;
    }

    public static List<Long> runLinearIntegerListForLoopSearchForN(int n) {
        List<Long> data = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            List<List<Integer>> integerListsList = new ArrayList<>();
            List<Integer> integers = new ArrayList<>();

            for (int j = 0; j < i; j++) {
                integerListsList.add(IntStream.rangeClosed(0, j).boxed().collect(Collectors.toList()));
                integers.add(random.nextInt(integerListsList.get(j).size()));
            }

            long startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                linearIntegerListForLoopSearch(integerListsList.get(j), integers.get(j));
            }
            long endTime = System.nanoTime();

            System.out.println(formatMessage("Linear search ran " + i + " time(s) in: ") + formatNanoTime(startTime, endTime));
            data.add((endTime - startTime));
        }

        return data;
    }

    public static XYChart.Series generatelinearIntegerListStreamFilterSearchSeries(int n) {
        XYChart.Series series = new XYChart.Series();
        series.setName("Integer List For Loop");

        for (int i = 1; i <= n; i++) {
            List<List<Integer>> integerListsList = new ArrayList<>();
            List<Integer> integers = new ArrayList<>();

            for (int j = 0; j < i; j++) {
                integerListsList.add(IntStream.rangeClosed(0, j).boxed().collect(Collectors.toList()));
                integers.add(random.nextInt(integerListsList.get(j).size()));
            }

            long startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                linearIntegerListForLoopSearch(integerListsList.get(j), integers.get(j));
            }
            long endTime = System.nanoTime();

            System.out.println(formatMessage("Linear search ran " + i + " time(s) in: ") + formatNanoTime(startTime, endTime));
            series.getData().add(new XYChart.Data<>(i, (endTime - startTime)));
        }

        return series;
    }

    public static XYChart.Series generateLinearIntegerListForLoopSearchSeries(int n) {
        XYChart.Series series = new XYChart.Series();
        series.setName("Integer List For Loop");

        for (int i = 1; i <= n; i++) {
            List<List<Integer>> integerListsList = new ArrayList<>();
            List<Integer> integers = new ArrayList<>();

            for (int j = 0; j < i; j++) {
                integerListsList.add(IntStream.rangeClosed(0, j).boxed().collect(Collectors.toList()));
                integers.add(random.nextInt(integerListsList.get(j).size()));
            }

            long startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                linearIntegerListStreamFilterSearch(integerListsList.get(j), integers.get(j));
            }
            long endTime = System.nanoTime();

            System.out.println(formatMessage("Linear search ran " + i + " time(s) in: ") + formatNanoTime(startTime, endTime));
            series.getData().add(new XYChart.Data<>(i, (endTime - startTime)));
        }

        return series;
    }

    public static XYChart.Series generateLinearIntegerArrayForLoopSearchSeries(int n) {
        XYChart.Series series = new XYChart.Series();
        series.setName("Integer Array For Loop");

        for (int i = 0; i <= n; i++) {
            List<Integer[]> integerArraysList = new ArrayList<>();
            List<Integer> integers = new ArrayList<>();

            for (int j = 0; j < i; j++) {
                integerArraysList.add(IntStream.rangeClosed(0, i).boxed().toArray(Integer[]::new));
                integers.add(random.nextInt(integerArraysList.get(j).length));
            }

            long startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                linearIntegerArrayForLoopSearch(integerArraysList.get(j), integers.get(j));
            }
            long endTime = System.nanoTime();

            System.out.println(formatMessage("Linear search ran " + i + " time(s) in: ") + formatNanoTime(startTime, endTime));
            series.getData().add(new XYChart.Data<>(i, (endTime - startTime)));
        }

        return series;
    }

    public static long runLinearListSearchForN(int n) {
        List<List<Integer>> integerListsList = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            integerListsList.add(IntStream.rangeClosed(0, i).boxed().collect(Collectors.toList()));
            integers.add(random.nextInt(integerListsList.get(i).size()));
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            linearIntegerListForLoopSearch(integerListsList.get(i), integers.get(i));
        }
        long endTime = System.nanoTime();

        System.out.println(formatMessage("Linear search ran " + n + " time(s) in: ") + formatNanoTime(startTime, endTime));
        return (endTime - startTime);
    }

    public static long runLinearArraySearchForN(int n) {
        List<Integer[]> integerArraysList = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            integerArraysList.add(IntStream.rangeClosed(0, i).boxed().toArray(Integer[]::new));
            integers.add(random.nextInt(integerArraysList.get(i).length));
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            linearIntegerArrayForLoopSearch(integerArraysList.get(i), integers.get(i));
        }
        long endTime = System.nanoTime();

        System.out.println(formatMessage("Linear search ran " + n + " time(s) in: ") + formatNanoTime(startTime, endTime));
        return (endTime - startTime);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Linear Search Results");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of runs");
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Time Complexity of Linear Search");
        int n = 1000;


        Scene scene = new Scene(lineChart, 800, 600);
//        runLinearIntegerListStreamFilterSearchSeries(n);
        List<Long> data = runLinearIntegerListForLoopSearchForN(n);
        List<Long> estimated = linearRegressionAnalysis(data);
        lineChart.getData().add(generateSeries("Estimated Values", estimated));
        lineChart.getData().add(generateSeries("Uncleaned Data", data));
//        lineChart.getData().add(generateSeries("Cleaned Data", cleanData(data)));
//        lineChart.getData().add(generateLinearSeries(n, 500));
//        lineChart.getData().add(generateLinearIntegerListForLoopSearchSeries(n));
//        lineChart.getData().add(generateLinearIntegerArrayForLoopSearchSeries(n));
//        lineChart.getData().add(generatelinearIntegerListStreamFilterSearchSeries(n));

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
