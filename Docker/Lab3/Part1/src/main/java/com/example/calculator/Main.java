package com.example.calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double a = scanner.nextDouble();

        System.out.print("Enter second number: ");
        double b = scanner.nextDouble();

        System.out.println("a = " + a + ", b = " + b);
        System.out.println("Sum: " + calc.sum(a, b));
        System.out.println("Subtract: " + calc.subtract(a, b));
        System.out.println("Multiply: " + calc.multiply(a, b));
        System.out.println("Divide: " + calc.divide(a, b));

        scanner.close();
    }
}
