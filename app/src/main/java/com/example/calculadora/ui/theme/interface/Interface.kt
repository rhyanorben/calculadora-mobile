package com.example.calculadora.ui.theme.`interface`

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Interface() {
    var displayText by remember { mutableStateOf("0") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = displayText,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.End)
        )

        val buttons = listOf(
            listOf("1", "2", "3", "+"),
            listOf("4", "5", "6", "-"),
            listOf("7", "8", "9", "*"),
            listOf("C", "0", "=", "/")
        )

        for (row in buttons) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (label in row) {
                    CalculatorButton(label) {
                        displayText = label
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(label: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .size(70.dp)
            .background(Color(0xFF8E44AD), shape = RoundedCornerShape(50))
            .clickable() { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}