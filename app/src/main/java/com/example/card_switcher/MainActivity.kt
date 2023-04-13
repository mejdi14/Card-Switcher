package com.example.card_switcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.card_switcher.ui.theme.CardSwitcherTheme
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

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
                    StackedCards()
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
fun StackedCards() {
    val areSeparated = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            val separationDistance = animateDpAsState(targetValue = if (areSeparated.value) 100.dp else 0.dp, animationSpec = tween(durationMillis = 1000)).value
            val blueCardOffset = Modifier.offset(x = separationDistance, y = 0.dp)
            val redCardOffset = Modifier.offset(x = -separationDistance, y = 16.dp)

            Card(
                modifier = redCardOffset,
                backgroundColor = Color.Red,
            ) {
                Box(Modifier.size(200.dp, 150.dp))
            }

            Card(
                modifier = blueCardOffset
                    .clickable {
                        areSeparated.value = !areSeparated.value
                    },
                backgroundColor = Color.Blue,
            ) {
                Box(Modifier.size(200.dp, 150.dp))
            }
        }
    }
}
