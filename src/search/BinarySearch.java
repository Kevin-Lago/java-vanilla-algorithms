package search;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import util.TimeComplexityLineChart;

import static util.Util.*;

public class BinarySearch extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public static XYChart.Series generateIterativeBinarySearchSeries(int t) {
        XYChart.Series series = new XYChart.Series();
        series.setName("Iterative Binary Search");

        for (int i = 1; i <= t; i++) {
            series.getData().add(new XYChart.Data<>(i, runIterativeBinarySearchNTimes(i, 100, 1, 100)));
        }

        return series;
    }

    public static XYChart.Series generateRecursiveBinarySearchSeries(int t) {
        XYChart.Series series = new XYChart.Series();
        series.setName("Recursive Binary Search");

        for (int i = 1; i <= t; i++) {
            series.getData().add(new XYChart.Data<>(i, runRecursiveBinarySearchNTimes(i, 100, 1, 100)));
        }

        return series;
    }

    public static long runIterativeBinarySearchNTimes(int n, int length, int min, int max) {
        int[][] arrays = new int[n][length];
        int[] xs = new int[n];
        int range = max - min + 1;

        for (int i = 0; i < n; i++) {
            arrays[i] = generateRandomIntArray(length, min, max);
            xs[i] = (int) (Math.random() * range) + min;
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            iterativeBinarySearch(arrays[i], xs[i]);
        }
        long endTime = System.nanoTime();
        System.out.println(formatMessage("Iterative binary search ran " + n + " times in: ") + formatNanoTime(startTime, endTime));
        return (endTime - startTime);
    }

    public static long runRecursiveBinarySearchNTimes(int n, int length, int min, int max) {
        int[][] arrays = new int[n][length];
        int[] xs = new int[n];
        int range = max - min + 1;

        for (int i = 0; i < n; i++) {
            arrays[i] = generateRandomIntArray(length, min, max);
            xs[i] = (int) (Math.random() * range) + min;
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < n; i++) {
            recursiveBinarySearch(arrays[i], 0, length - 1, xs[i]);
        }
        long endTime = System.nanoTime();
        System.out.println(formatMessage("Recursive binary search ran " + n + " times in: ") + formatNanoTime(startTime, endTime));
        return (endTime - startTime);
    }

    public static int iterativeBinarySearch(int[] array, int x) {
        int lowLimit = 0;
        int highLimit = array.length - 1;

        while (lowLimit <= highLimit) {
            int index = lowLimit + (highLimit - lowLimit) / 2;

            if (array[index] == x) return index;
            if (array[index] < x) lowLimit = index + 1;
            else highLimit = index - 1;
        }

        return -1;
    }

    public static int recursiveBinarySearch(int[] array, int lowLimit, int highLimit, int x) {
        if (highLimit >= lowLimit) {
            int index = lowLimit + (highLimit - lowLimit) / 2;

            if (array[index] == x) return index;
            if (array[index] > x) return recursiveBinarySearch(array, lowLimit, index - 1, x);
            else return recursiveBinarySearch(array, index + 1, highLimit, x);
        }

        return -1;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Binary Search Results");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of runs");
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Time Complexity of Binary Search");

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(generateLogarithmicSeries(100, 1000));
        lineChart.getData().add(BinarySearch.generateIterativeBinarySearchSeries(100));
        lineChart.getData().add(BinarySearch.generateRecursiveBinarySearchSeries(100));

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
