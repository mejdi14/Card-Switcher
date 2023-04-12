package com.example.card_switcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.card_switcher.ui.theme.CardSwitcherTheme
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Card
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardSwitcherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CardSwitcherTheme {
        Greeting("Android")
    }
}

@Composable
fun AnimatedCards() {
    val isSwitched = remember { mutableStateOf(false) }
    val offsetX = remember { mutableStateOf(0.dp) }
    val offsetY = remember { mutableStateOf(0.dp) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxSize()) {
            val topCardOffset = animateOffsetAsState(targetValue = if (isSwitched.value) offsetX.value else offsetY.value)
            val bottomCardOffset = animateOffsetAsState(targetValue = if (isSwitched.value) offsetY.value else offsetX.value)

            Card(
                modifier = Modifier
                    .offset(topCardOffset.value)
                    .clickable {
                        if (isSwitched.value) {
                            offsetX.value = 0.dp
                            offsetY.value = 0.dp
                        } else {
                            offsetX.value = -120.dp
                            offsetY.value = 120.dp
                        }
                        isSwitched.value = !isSwitched.value
                    },
                backgroundColor = if (isSwitched.value) Color.Blue else Color.Red,
            ) {
                // Add your card content here
            }

            Card(
                modifier = Modifier
                    .offset(bottomCardOffset.value),
                backgroundColor = if (isSwitched.value) Color.Red else Color.Blue,
            ) {
                // Add your card content here
            }
        }
    }
}