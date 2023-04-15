package com.example.card_switcher

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.card_switcher.data.SwitchedCardsData
import com.example.card_switcher.enums.AnimationDirection
import kotlinx.coroutines.delay

@Composable
fun SwitchedCard(cardsConfiguration: SwitchedCardsData) {
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
                    cardsConfiguration.topCardContent
                }
            }

            Card(
                modifier = bottomCardOffset
                    .clickable { cardState.value = (cardState.value + 1) % 2 }
                    .zIndex(if (zIndexState.value % 2 != 0) 2f else 0f),
                backgroundColor = Color.Blue,
            ) {
                Box(cardsConfiguration.cardModifier){
                    cardsConfiguration.bottomCardContent
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
