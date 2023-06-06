package util;

import java.util.Arrays;

public class Util {
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

        return String.format("|%32s|", formattedTime);
    }
}
