package com.example.pattern_in_action.decorator.fpversion;

import java.awt.*;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class Camera {

    private Function<Color, Color> filter;

    public Color capture(final Color inputColor) {
        return filter.apply(inputColor);
    }

    public void setFilters(final Function<Color, Color>... filters) {
        filter = Arrays.stream(filters).reduce(Function::andThen).orElse(Function.identity());
    }

    public Camera() {
        setFilters();
    }

    public static void main(String[] args) {
        Function<Integer, Integer> a = integer -> integer * 2;
        Function<Integer, Integer> b = integer -> integer + 1;
        System.err.println(a.compose(b).apply(1));  //先执行b 再执行a (1+1)*2
        System.err.println(a.andThen(b).apply(1));  //先执行a 再执行b (1*2)+1

        Camera basic = new Camera();
        final Consumer<String> printCaptured = (filterInfo) ->
                System.out.println(String.format("with %s: %s", filterInfo,
                        basic.capture(new Color(200, 100, 200))));
        printCaptured.accept("no filter basic");

        basic.setFilters(Color::brighter);
        printCaptured.accept("brighter color");
        basic.setFilters(Color::darker);
        printCaptured.accept("darker color");
        basic.setFilters(Color::brighter,Color::darker);
        printCaptured.accept("brighter && darker color");
    }
}
