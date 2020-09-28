package com.example.pattern_in_action.iomonad;

public interface IOMonad<T> {

    default T run() {
        return null;
    }

}
