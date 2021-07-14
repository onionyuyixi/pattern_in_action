package com.example.pattern_in_action.handleerror;

import lombok.SneakyThrows;

public class DemoTest {


    public static void use(int mod, final ThrowException<Integer, Throwable> throwEx) throws Throwable {
        try {
            throwEx.action(mod);
        } finally {
            System.err.println("okay");
        }
    }

    public static void main(String[] args) throws Throwable {
        use(0, integer -> {
            int i = 1 / integer;
        });
    }
}
