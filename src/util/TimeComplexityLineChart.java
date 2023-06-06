package util;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import search.BinarySearch;

public class TimeComplexityLineChart extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Result");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of runs");
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Time Complexity");
        XYChart.Series logarithmicSeries = new XYChart.Series();
        logarithmicSeries.setName("Logarithmic Series");
//        XYChart.Series linearSeries = new XYChart.Series();
//        linearSeries.setName("Linear Series");

        for (int i = 0; i < 100; i++) {
            logarithmicSeries.getData().add(new XYChart.Data<>(i, Math.log(i + 1) * 1000));
//            linearSeries.getData().add(new XYChart.Data<>(i, i));
        }

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(logarithmicSeries);
//        lineChart.getData().add(linearSeries);
        lineChart.getData().add(BinarySearch.generateIterativeBinarySearchSeries(100));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
