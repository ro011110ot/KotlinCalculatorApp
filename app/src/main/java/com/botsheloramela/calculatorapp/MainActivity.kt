package com.botsheloramela.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.botsheloramela.calculatorapp.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    val buttonRows = listOf(
        listOf("AC", "⌫", "÷"),
        listOf("7", "8", "9", "×"),
        listOf("4", "5", "6", "+"),
        listOf("1", "2", "3", "-"),
        listOf("%", "0", ".", "=")
    )

    val functionButtons = setOf("AC", "⌫", "÷", "×", "+", "-", "%", "=")

    Column (modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {

        Column (modifier = Modifier.weight(1f)) {

        }

        buttonRows.forEach { rowButtons ->
            Row(modifier = modifier.fillMaxWidth()) {
                rowButtons.forEach { buttonText ->
                    val weight = if (buttonText.length == 2) 2f else 1f
                    CalculatorButton(
                        text = buttonText,
                        isFunction = buttonText in functionButtons,
                        modifier = modifier.weight(weight),
                        onClick = {
                            // Handle button click
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    modifier: Modifier = Modifier,
    text: String = "0",
    isFunction: Boolean = false,
    onClick: (String) -> Unit = {}) {
    
    val containerColors = when {
        isFunction && (text == "AC" || text == "=") -> MaterialTheme.colorScheme.primary
        isFunction -> MaterialTheme.colorScheme.secondary
        else -> MaterialTheme.colorScheme.tertiary
    }

    Button(modifier = modifier
        .size(72.dp)
        .padding(4.dp)
        .clip(CircleShape),
        onClick = { onClick(text) },
        colors = ButtonDefaults.buttonColors(containerColor = containerColors)
        ) {
        Text(text = text, style = TextStyle(fontSize = 24.sp))
    }
}

@Preview
@Composable
fun CalculatorButtonPreview() {
    CalculatorButton()
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen()
}