package search;

import java.util.Arrays;

import static util.Util.*;

public class BinarySearch {
    public static void main(String[] args) {
        runIterativeBinarySearchNTimes(100000, 100, 1, 100);
        runRecursiveBinarySearchNTimes(100000, 100, 1, 100);
        int[] array = generateRandomIntArray(1000000, 1, 10000);
        int x = array[73];

        long startTime = System.nanoTime();
        int i = iterativeBinarySearch(array, x);
        long endTime = System.nanoTime();
        if (i == -1) System.out.println(x + " was not found in " + formatNanoTime(startTime, endTime));
        else System.out.println(formatMessage("Iterative binary search found int " + x +  " at index " + i + " in: ") + formatNanoTime(startTime, endTime));

        startTime = System.nanoTime();
        recursiveBinarySearch(array, 0, array.length - 1, x);
        endTime = System.nanoTime();
        if (i == -1) System.out.println(x + " was not found in " + formatNanoTime(startTime, endTime));
        System.out.println(formatMessage("Recursive binary search found int " + x + " at index " + i + " in: ") + formatNanoTime(startTime, endTime));
    }

    public static void runIterativeBinarySearchNTimes(int n, int length, int min, int max) {
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

        System.out.println(formatMessage("Iterative binary search ran " + n +  " times in: ") + formatNanoTime(startTime, endTime));
    }

    public static void runRecursiveBinarySearchNTimes(int n, int length, int min, int max) {
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

        System.out.println(formatMessage("Recursive binary search ran " + n +  " times in: ") + formatNanoTime(startTime, endTime));
    }

    public static int iterativeBinarySearch(int[] array, int x) {
        int lowLimit = 0;
        int highLimit = array.length - 1;

        while(lowLimit <= highLimit) {
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

        return - 1;
    }
}
