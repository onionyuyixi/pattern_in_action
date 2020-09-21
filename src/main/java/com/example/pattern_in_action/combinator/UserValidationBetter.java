package com.example.pattern_in_action.combinator;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.example.pattern_in_action.combinator.ValidationResult.invalid;
import static com.example.pattern_in_action.combinator.ValidationResult.valid;

public interface UserValidationBetter extends Function<User, ValidationResult> {


    static UserValidationBetter nameIsNotEmpty() {
        return holds(user -> !user.name.trim().isEmpty(), "用户姓名为空");
    }

    static UserValidationBetter ageMustGtZero() {
        return holds(user -> user.age > 0, "用户年龄必须大于0");
    }


    static UserValidationBetter emailContainsAtSign() {
        return holds(user -> user.email.contains("@"), "邮箱格式有误");
    }

    private static UserValidationBetter holds(Predicate<User> p, String message) {
        return user -> (p.test(user) ? valid() : invalid(message));
    }

    default UserValidationBetter and(UserValidationBetter other) {
        return user -> {
            ValidationResult result = this.apply(user);
            return result.isValid() ? other.apply(user) : result;
        };
    }

    static UserValidationBetter all(UserValidationBetter... all) {
        return user -> Stream.of(all)
                .map(a -> a.apply(user))
                .filter(b -> !b.isValid())
                .findFirst()
                .orElse(valid());
    }


}
