package com.example.appincade1.Utils;



public class Calculadora {

    public class Result<T> {
        T value;

        Result(T value) {
            this.value = value;
        }

        String asString() {
            return this.value.toString();
        }

    }

    static double number;

    Calculadora() {

    }

    Calculadora(double numberCalculated) {
        number = numberCalculated;
    }

    Result sum(double num1, double num2) {
        return new Result<Double>(num1 + num2);
    }


}
