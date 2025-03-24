package com.example.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculadora.ui.theme.`interface`.Interface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Interface()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Interface()
}