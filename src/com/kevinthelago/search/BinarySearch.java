package search;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.kevinthelago.Util.*;

/**
 * These search algorithms assume the value exists and will be found, and that the list is sorted.
 * */
public class BinarySearch extends Application {
    private static final int n = 100; // Number of runs
    private static final int m = 100000; // Multiplies the number of elements for each run

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public static int search(List<Integer> integers, Integer x) {
        int lowLimit = 0;
        int highLimit = integers.size() - 1;

        while (lowLimit <= highLimit) {
            int index = lowLimit + (highLimit - lowLimit) / 2;

            if (integers.get(index).equals(x)) return index;
            if (integers.get(index) < x) lowLimit = index + 1;
            else highLimit = index - 1;
        }

        return -1;
    }

    public static int search(List<Integer> integers, Integer x, int lowLimit, int highLimit) {
        if (highLimit >= lowLimit) {
            int index = lowLimit + (highLimit - lowLimit) / 2;

            if (integers.get(index).equals(x)) return index;
            if (integers.get(index) > x) return search(integers, lowLimit, index - 1, x);
            else return search(integers, index + 1, highLimit, x);
        }

        return -1;
    }

    public List<Long> run(int n, int m) {
        List<Long> data = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> list = IntStream.rangeClosed(0, i * m).boxed().sorted().collect(Collectors.toList());
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

        primaryStage.setTitle("Time Complexity of Binary Search");
        xAxis.setLabel("Number of elements");
        lineChart.setTitle("Binary Search Results");
        Scene scene = new Scene(lineChart, 800, 600);
        scene.getStylesheets().add("com/kevinthelago/style/Chart.css");

        List<Long> loopSearchTimes = run(n, m);
//        List<Long> recursiveSearchTimes = run(n, m);

        lineChart.setCreateSymbols(false);
        lineChart.getData().add(generateSeries("Binary Search Result", m, loopSearchTimes));
//        lineChart.getData().add(generateSeries("Estimated Values", m, linearRegressionAnalysis(times)));

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
