package com.unittesting.defaultClasses;

public class AgeValidator {
    public void validate(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be 18 or above");
        }
    }
}
