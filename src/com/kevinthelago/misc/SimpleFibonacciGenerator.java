package com.kevinthelago.misc;

public class SimpleFibonacciGenerator {
    public static void main(String[] args) {
        int fn = simpleFibonacciSeries(10);
        System.out.println(fn);
    }

    public static int simpleFibonacciSeries(int n) {
        for (int i = n; i > 0; i--) {
            if (i < 2) {
                return 1;
            }
            return simpleFibonacciSeries(i - 1) + simpleFibonacciSeries(i - 2);
        }
        return 0;
    }

//    public static int memoizedFibonacciSeries(int n) {
//        List<Integer> memos = new ArrayList<>();
//
//        for (int i = n; i > 0; i--) {
//            if () {
//
//            }
//        }
//    }
}
