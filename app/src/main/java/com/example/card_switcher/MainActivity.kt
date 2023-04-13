package com.example.card_switcher

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.card_switcher.ui.theme.CardSwitcherTheme
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay

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
                    StackedCards(StackedCardsConfiguration(cardModifier = Modifier.size(200.dp, 350.dp)))
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

sealed class AnimationDirection {
    object TopRightBottomLeft : AnimationDirection()
    object TopLeftBottomRight : AnimationDirection()
}
data class StackedCardsConfiguration(
    val animationDuration: Int = 1000,
    val blueCardContent: @Composable () -> Unit = {},
    val redCardContent: @Composable () -> Unit = {},
    val cardModifier: Modifier = Modifier.size(200.dp, 150.dp),
    val animationDirection: AnimationDirection = AnimationDirection.TopRightBottomLeft
)

@Composable
fun StackedCards(cardsConfiguration: StackedCardsConfiguration) {
    val cardState = remember { mutableStateOf(0) }
    val zIndexState = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            val separationDistance = animateDpAsState(
                targetValue = if (cardState.value % 2 != 0) 100.dp else 0.dp,
                animationSpec = tween(durationMillis = cardsConfiguration.animationDuration)
            ).value

            val bottomCardOffset = when (cardsConfiguration.animationDirection) {
                AnimationDirection.TopRightBottomLeft -> Modifier.offset(x = separationDistance, y = 0.dp)
                AnimationDirection.TopLeftBottomRight -> Modifier.offset(x = -separationDistance, y = 0.dp)
            }
            val topCardOffset = when (cardsConfiguration.animationDirection) {
                AnimationDirection.TopRightBottomLeft -> Modifier.offset(x = -separationDistance, y = 16.dp)
                AnimationDirection.TopLeftBottomRight -> Modifier.offset(x = separationDistance, y = 16.dp)
            }

            Card(
                modifier = topCardOffset
                    .clickable { cardState.value = (cardState.value + 1) % 2 }
                    .zIndex(if (zIndexState.value % 2 != 0) 0f else 2f),
                backgroundColor = Color.Red,
            ) {
                Box(cardsConfiguration.cardModifier){
                    cardsConfiguration.redCardContent
                }
            }

            Card(
                modifier = bottomCardOffset
                    .clickable { cardState.value = (cardState.value + 1) % 2 }
                    .zIndex(if (zIndexState.value % 2 != 0) 2f else 0f),
                backgroundColor = Color.Blue,
            ) {
                Box(cardsConfiguration.cardModifier){
                    cardsConfiguration.blueCardContent
                }
            }
        }
    }

    // Check if the animation has completed and reset card state to allow for infinite animation
    LaunchedEffect(cardState.value) {
        if (cardState.value == 1 || cardState.value == 3) {
            delay(1000)
            zIndexState.value = (zIndexState.value + 1) % 2
            cardState.value = (cardState.value + 1) % 2
        }
    }
}























