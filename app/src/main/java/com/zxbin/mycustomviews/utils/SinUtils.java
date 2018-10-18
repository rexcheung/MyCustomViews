package com.zxbin.mycustomviews.utils;

import java.util.ArrayList;
import java.util.List;

public class SinUtils {

    public static List<Double> getUnitCircle() {
        ArrayList<Double> results = new ArrayList<>();
        for (double i = 0; i <= 360; i += 15) {
            double hoho = i / 180;
            double sin = Math.sin(Math.PI * hoho);
            results.add(sin);
        }

        return results;
    }
}
