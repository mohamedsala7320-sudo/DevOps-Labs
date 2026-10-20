package com.example.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private final Calculator calc = new Calculator();

    @Test
    public void testSum() {
        assertEquals(15.0, calc.sum(10, 5));
    }

    @Test
    public void testSubtract() {
        assertEquals(5.0, calc.subtract(10, 5));
    }

    @Test
    public void testMultiply() {
        assertEquals(50.0, calc.multiply(10, 5));
    }

    @Test
    public void testDivide() {
        assertEquals(2.0, calc.divide(10, 5));
    }

    @Test
    public void testDivideByZeroThrowsException() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }
}
