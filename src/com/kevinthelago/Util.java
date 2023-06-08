package com.kevinthelago;

import javafx.scene.chart.XYChart;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Util {
    public static Random random = new Random();

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

    public static XYChart.Series generateSeries(String title, List<Integer> data) {
        XYChart.Series series = new XYChart.Series();
        series.setName(title);

        for (int i = 0; i < data.size(); i++) {
            series.getData().add(new XYChart.Data<>(i, data.get(i)));
        }

        return series;
    }

    public static List<Integer> linearRegressionAnalysis(List<Integer> data) {
        int independentMean = summation(data.size()) / data.size();
        List<Integer> independentDistance = new ArrayList<>();
        for (int i = 1; i <= data.size(); i++) {
            independentDistance.add(i - independentMean);
        }

        Integer dependentMean = data.stream().reduce(0, Integer::sum) / data.size();
        List<Long> dependentDistance = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            dependentDistance.add((long) data.get(i) - dependentMean);
        }
        Long dependentDistanceSum = dependentDistance.stream().reduce(Long::sum).get();

        List<Long> squaredIndependentDistance = independentDistance.stream().map(e -> (long) Math.pow(e, 2)).collect(Collectors.toList());
        List<Long> distanceProduct = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            distanceProduct.add(dependentDistance.get(i) * independentDistance.get(i));
        }

        Long squaredIndependentDistanceSum = squaredIndependentDistance.stream().reduce(Long::sum).get();
        Long distanceProductSum = distanceProduct.stream().reduce(Long::sum).get();

        Long b1 = distanceProductSum / squaredIndependentDistanceSum;
        Long b0 = dependentMean - (b1 * independentMean);

        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= data.size(); i++) {
            result.add((int) (b0 + (i * b1)));
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
