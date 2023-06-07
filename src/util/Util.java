package util;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Util {
    public static XYChart.Series generateLogarithmicSeries(int n, int m) {
        XYChart.Series logarithmicSeries = new XYChart.Series();
        logarithmicSeries.setName("Logarithmic Series");

        for (int i = 0; i < n; i++) {
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

    public static XYChart.Series generateSeries(String title, List<Long> data) {
        XYChart.Series series = new XYChart.Series();
        series.setName(title);

        for (int i = 0; i < data.size(); i++) {
            series.getData().add(new XYChart.Data<>(i, data.get(i)));
        }

        return series;
    }

    public static List<Long> linearRegressionAnalysis(List<Long> data) {
        int independentMean = summation(data.size()) / data.size();
        List<Integer> independentDistance = new ArrayList<>();

        for (int i = 1; i <= data.size(); i++) {
            independentDistance.add(i - independentMean);
        }

        Long dependentMean = data.stream().reduce(0L, Long::sum) / data.size();
        List<Long> dependentDistance = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            dependentDistance.add(data.get(i) - dependentMean);
        }

        Long sum = dependentDistance.stream().reduce(Long::sum).get(); // Sum not equal to 0
        List<Double> squaredIndependentDistance = independentDistance.stream().map(e -> Math.pow(e, 2)).collect(Collectors.toList());
        List<Double> distanceProduct = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            distanceProduct.add((double) dependentDistance.get(i) * independentDistance.get(i));
        }

        Double squaredIndependentDistanceSum = squaredIndependentDistance.stream().reduce(Double::sum).get();
        Double distanceProductSum = distanceProduct.stream().reduce(Double::sum).get();
        Double b1 = distanceProductSum / squaredIndependentDistanceSum;

        List<Long> result = new ArrayList<>();

        for (int i = 1; i <= data.size(); i++) {
            result.add((long) ((long) i * b1));
        }

        return result;
    }

    public static int summation(int n) {
        int summation = 0;

        for (int i = 1; i <= n; i++) {
            summation += i;
        }

        return summation;
    }

    public static List<Long> cleanData(List<Long> data) {
        Long sum = data.stream().reduce(0L, Long::sum);
        Long mean = sum / data.size();
        Double standardDeviation = 0.0D;

        for (int i = 0; i < data.size(); i++) {
            standardDeviation += Math.pow(data.get(i) - mean, 2);
        }

        standardDeviation = Math.sqrt(standardDeviation / data.size());

        List<Long> cleanedData = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) < mean + 2 * standardDeviation) {
                cleanedData.add(data.get(i));
            } else {
                System.out.println("Removed value: " + data.get(i) + " from position " + i);
            }
        }

        return cleanedData;
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
