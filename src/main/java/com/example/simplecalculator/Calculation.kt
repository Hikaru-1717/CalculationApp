package com.example.simplecalculator

class Calculation (

    // Constructor
    private val operand1: Double,
    private val operand2: Double
) {
    fun performOperation(operation: Operations): Double {
        return when (operation) {
            Operations.Add -> operand1 + operand2
            Operations.Subtract -> operand1 - operand2
            Operations.Multiply -> operand1 * operand2
            Operations.Divide -> operand1 / operand2
        }
    }
}