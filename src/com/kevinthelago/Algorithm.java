package com.kevinthelago;

import javafx.scene.Scene;

import java.util.List;
import java.util.concurrent.Callable;

public interface Algorithm {
    List<Integer> run(int n);

    Scene createScene(List<Integer> times);
}
