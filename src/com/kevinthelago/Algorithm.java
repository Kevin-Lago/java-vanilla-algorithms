package com.kevinthelago;

import javafx.scene.Scene;

import java.util.List;
import java.util.concurrent.Callable;

public interface Algorithm {
    List<Long> run(int n);

    Scene createScene(List<Long> times);
}
