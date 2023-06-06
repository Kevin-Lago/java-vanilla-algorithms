package util;

import javafx.scene.chart.XYChart;

import java.util.Arrays;

public class Util {
    public static XYChart.Series generateLogarithmicSeries(int t, int m) {
        XYChart.Series logarithmicSeries = new XYChart.Series();
        logarithmicSeries.setName("Logarithmic Series");

        for (int i = 0; i < t; i++) {
            logarithmicSeries.getData().add(new XYChart.Data<>(i, Math.log(i + 1) * m));
        }

        return logarithmicSeries;
    }

    public static int[] generateRandomIntArray(int length, int min, int max) {
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
