package com.example.pattern_in_action.combinator;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Optional;

/**
 * 以接口为尊 颇有独立性 完整性 善哉 可以效从
 */
public interface ValidationResult {

    static ValidationResult valid() {
        return ValidationSupport.valid();
    }

    static ValidationResult invalid(String reason) {
        return new Invalid(reason);
    }

    boolean isValid();

    Optional<String> getReason();

    @EqualsAndHashCode()
    @AllArgsConstructor
    final class Invalid implements ValidationResult {

        String reason;

        @Override
        public boolean isValid() {
            return false;
        }

        @Override
        public Optional<String> getReason() {
            return Optional.of(reason);
        }
    }

    final class ValidationSupport {
        private static final ValidationResult valid = new ValidationResult() {
            public boolean isValid() {
                return true;
            }

            public Optional<String> getReason() {
                return Optional.empty();
            }
        };

        static ValidationResult valid() {
            return valid;
        }
    }
}

