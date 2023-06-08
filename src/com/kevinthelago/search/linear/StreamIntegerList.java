//package com.kevinthelago.search.linear;
//
//import com.kevinthelago.Algorithm;
//import javafx.scene.Scene;
//
//import java.util.List;
//
//public class StreamIntegerList implements Algorithm {
//    public static int search(List<Integer> integers, Integer x) {
//        Integer result = integers.stream().filter(integer -> {
//            return integer.equals(x);
//        }).findFirst().orElse(null);
//
//        return 0;
//    }
//
//    @Override
//    public List<Long> run(int n) {
//        return null;
//    }
//
//    @Override
//    public Scene createScene(List<Long> times) {
//        return null;
//    }
//}
