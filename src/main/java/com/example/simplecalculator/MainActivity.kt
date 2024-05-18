package com.example.simplecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplecalculator.ui.theme.SimpleCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SimpleCalculator()
                }
            }
        }
    }
}

@Composable
fun SimpleCalculator() {
    CalculationInput()
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    SimpleCalculatorTheme {
        SimpleCalculator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculationInput() {

    Column(
        modifier = Modifier.fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        var value1 by remember { mutableStateOf("") }
        var value2 by remember { mutableStateOf("") }

        var selectedOption by remember { mutableStateOf(Operations.Add) }
        var result by remember { mutableStateOf("") }

        val add = Operations.Add
        val subtract = Operations.Subtract
        val multiply = Operations.Multiply
        val divide = Operations.Divide

        // Display the calculation result
        Text (
            text = "$result",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp))

        Text(text ="Simple Calculator")

        // Text field for strand1
        OutlinedTextField(
            value = value1,
            onValueChange = {value1 = it},
            label = {Text("Operand 1")},
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                capitalization = KeyboardCapitalization.None
            )
        )

        // Options for operations
        Row (
            modifier = Modifier.padding(0.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            // Add Button
            RadioButton(
                selected = selectedOption == add,
                onClick = { selectedOption = add}
            )
                Text(text = "+")

            // Subtract Button
            RadioButton(
                selected = selectedOption == subtract,
                onClick = { selectedOption = subtract}
            )
            Text(text = "ー")

            // Multiply Button
            RadioButton(
                selected = selectedOption == multiply,
                onClick = { selectedOption = multiply}
            )
            Text(text = "×")

            // Divide Button
            RadioButton(
                selected = selectedOption == divide,
                onClick = { selectedOption = divide}
            )
            Text(text = "÷")

        }

        // Text field for strand2
        OutlinedTextField(
            value = value2,
            onValueChange = {value2 = it},
            label = {Text("Operand 2")},
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                capitalization = KeyboardCapitalization.None
            )
        )

        // Calculation button
        Button(
            onClick = {
                result = calculateResult(
                    value1.toDouble(), value2.toDouble(), selectedOption)

            }, enabled = value1.isNotEmpty() && value2.isNotEmpty()
                    ) {
                        Text(text = "Calculate")
        }

    }

}

// Show calculation result
fun calculateResult(operand1: Double, operand2: Double, operation: Operations): String {
    val calculator = Calculation(operand1, operand2)
    val result = calculator.performOperation(operation)
    return result.toString()
}