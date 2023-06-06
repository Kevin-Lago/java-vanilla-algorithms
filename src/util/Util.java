package util;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Util {
    public static XYChart.Series generateLogarithmicSeries(int t, int m) {
        XYChart.Series logarithmicSeries = new XYChart.Series();
        logarithmicSeries.setName("Logarithmic Series");

        for (int i = 0; i < t; i++) {
            logarithmicSeries.getData().add(new XYChart.Data<>(i, Math.log(i + 1) * m));
        }

        return logarithmicSeries;
    }

    public static XYChart.Series generateLinearSeries(int n, int m) {
        XYChart.Series linearSeries = new XYChart.Series();
        linearSeries.setName("Linear Series");

        for (int i = 0; i < n; i++) {
            linearSeries.getData().add(new XYChart.Data<>(i, i * m));
        }

        return linearSeries;
    }

    public static Integer[] generateIntegerArray(int n) {
        Integer[] integers = new Integer[n];

        for (int i = 0; i < n; i++) {
            integers[i] = i;
        }

        return integers;
    }

    public static int[][] generateNSortedUniqueIntArrays(int n) {
        List<List<Integer>> intLists = new ArrayList<>();
        int[][] arrays = new int[n][n];

        for (int i = 0; i < n; i++) {
            intLists.add(new ArrayList<>());
        }

        return arrays;
    }

//    public static List<Integer> generateIntegerList(int start, int end) {
//        List<Integer> range = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
//    }

    public static int[][] generateNUnsortedUniqueIntArrays(int n, int length) {
        int[][] arrays = new int[n][length];

        for (int i = 0; i < n; i++) {
            arrays[i] = generateUnsortedUniqueIntArray(length);
        }

        return arrays;
    }

    public static int[] generateUnsortedUniqueIntArray(int length) {
        List<Integer> ints = new ArrayList<>();
//        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            ints.add(i);
        }

        Collections.shuffle(ints);
        return ints.stream().mapToInt(i -> i).toArray();
    }

    public static int[][] generateNSortedUniqueIntArrays(int n, int length) {
        int[][] arrays = new int[n][length];

        for (int i = 0; i < n; i++) {
            arrays[i] = generateSortedUniqueIntArray(length);
        }

        return arrays;
    }

    public static int[] generateSortedUniqueIntArray(int length) {
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = i;
        }

        return array;
    }

    public static int[][] generateNUnsortedRandomIntArrays(int n, int length, int min, int max) {
        int[][] arrays = new int[n][length];

        for (int i = 0; i < n; i++) {
            arrays[i] = generateUnsortedRandomIntArray(length, min, max);
        }

        return arrays;
    }

    public static int[] generateUnsortedRandomIntArray(int length, int min, int max) {
        int[] array = new int[length];
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * range) + min;
        }

        return array;
    }

    public static int[][] generateNSortedRandomIntArrays(int n, int length, int min, int max) {
        int[][] arrays = new int[n][length];

        for (int i = 0; i < n; i++) {
            arrays[i] = generateSortedRandomIntArray(length, min, max);
        }

        return arrays;
    }

    public static int[] generateSortedRandomIntArray(int length, int min, int max) {
        int[] array = new int[length];
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * range) + min;
        }

        Arrays.sort(array);
        return array;
    }

    public static String formatMillisTime(long startTime, long endTime) {
        long duration = (endTime - startTime);

        return String.format("%d Milliseconds", duration);
    }

    public static String formatMessage(String message) {
        return String.format("| %-53s", message);
    }

    public static String formatNanoTime(long startTime, long endTime) {
        long duration = (endTime - startTime);
        String formattedTime = "";

        if (duration > 1000000) formattedTime = String.format("%.5f Milliseconds", (double) duration / 1000000);
        else formattedTime = String.format("%d Nanoseconds", duration);

        return String.format("%32s|", formattedTime);
    }
}
