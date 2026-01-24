package com.unittesting;

import com.unittesting.defaultClasses.AgeValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AgeValidatorTest {

    private final AgeValidator ageValidator = new AgeValidator();

    @Test
    void testValidAge(){
        Assertions.assertDoesNotThrow(() -> ageValidator.validate(18));
    }

    @Test
    void testInvalidAge(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> ageValidator.validate(15));
    }
}
/*
AgeValidator class:
*. Test valid age
*. Test invalid age
*. Use assertThrows() if invalid input
 */
