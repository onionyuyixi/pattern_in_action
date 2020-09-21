package com.example.pattern_in_action.combinator;

import java.util.function.Function;
import java.util.stream.Stream;

public interface UserValidation extends Function<User, Boolean> {


    static UserValidation nameIsNotEmpty() {
        return user -> !user.name.trim().isEmpty();
    }

    static UserValidation emailContainsAtSign() {
        return user -> user.email.contains("@");
    }


    static UserValidation ageMustGtZero() {
        return user -> user.age > 0;
    }

    default UserValidation and(UserValidation other) {
        return user -> this.apply(user) && other.apply(user);
    }

    static UserValidation all(UserValidation... all) {
        return user -> Stream.of(all).map(a -> a.apply(user)).allMatch(b -> b);
    }


}
