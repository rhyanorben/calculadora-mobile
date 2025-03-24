package com.example.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Interface()
                }
            }
        }

    }
}

@Composable
fun Interface() {
    val display = remember { mutableStateOf(TextFieldValue("0")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            value = display.value,
            onValueChange = { display.value = it },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Black)
                .padding(16.dp),
            textStyle = androidx.compose.ui.text.TextStyle(
                color = Color.White,
                fontSize = 64.sp,
                textAlign = TextAlign.Right
            )
        )

        Column(
            modifier = Modifier.weight(3f)
        ) {
            val buttons = listOf(
                listOf("7", "8", "9", "/"),
                listOf("4", "5", "6", "*"),
                listOf("1", "2", "3", "-"),
                listOf("0", "C", "=", "+")
            )
            buttons.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    row.forEach { buttonText ->
                        CalculatorButton(
                            text = buttonText,
                            onClick = { onButtonClick(buttonText, display)},
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

fun onButtonClick(button: String, display: MutableState<TextFieldValue>) {
    when (button) {
        "=" -> {
            val result = calcularExpressao(display.value.text)
            display.value = TextFieldValue(result.toString())
        }
        "C" -> {
            display.value = TextFieldValue("0")
        }
        else -> {
            if (display.value.text == "0") {
                display.value = TextFieldValue(button)
            } else {
                display.value = TextFieldValue(display.value.text + button)
            }
        }
    }
}

fun calcularExpressao(expressao: String): Double {
    val tokens = expressao.split("").filter { it.isNotEmpty() }
    val numbers = mutableListOf<Double>()
    val operators = mutableListOf<String>()

    for (token in tokens) {
        when (token) {
            "+", "-", "*", "/" -> operators.add(token)
            else -> numbers.add(token.toDouble())
        }
    }

    var result = numbers[0]
    var index = 0

    while (index < operators.size) {
        val operator = operators[index]
        val number = numbers[index + 1]

        result = when (operator) {
            "*" -> result * number
            "/" -> result / number
            "+" -> result + number
            "-" -> result - number
            else -> result
        }
        index++
    }
    return result
}

@Composable
fun CalculatorButton(
    text: String, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(PaddingValues(4.dp))
            .fillMaxSize()
    ) {
        Text(text = text, fontSize = 48.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Interface()
}