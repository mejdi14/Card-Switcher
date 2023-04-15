package com.example.card_switcher

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.card_switcher.data.SwitchedCardsData
import com.example.card_switcher.enums.AnimationDirection
import kotlinx.coroutines.delay

@Composable
fun SwitchedCard(cardsConfiguration: SwitchedCardsData) {
    val cardState = remember { mutableStateOf(0) }
    val zIndexState = remember { mutableStateOf(0) }

    val topCardSwipe = if (cardsConfiguration.enableSwipe) {
        Modifier.pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                val distance = change.position.x
                processSwipe(distance, cardsConfiguration.swipeSensibility) { cardState.value = (cardState.value + 1) % 2 }
            }
        }
    } else {
        Modifier
    }

    val bottomCardSwipe = if (cardsConfiguration.enableSwipe) {
        Modifier.pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                val distance = change.position.x
                processSwipe(distance, 100f) { cardState.value = (cardState.value + 1) % 2 }
            }
        }
    } else {
        Modifier
    }

    val topCardClickable = if (cardsConfiguration.triggerOnClick) {
        Modifier.clickable { cardState.value = (cardState.value + 1) % 2 }
    } else {
        Modifier
    }

    val bottomCardClickable = if (cardsConfiguration.triggerOnClick) {
        Modifier.clickable { cardState.value = (cardState.value + 1) % 2 }
    } else {
        Modifier
    }


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            val separationDistance = animateDpAsState(
                targetValue = if (cardState.value % 2 != 0) 100.dp else 0.dp,
                animationSpec = tween(durationMillis = cardsConfiguration.animationDuration)
            ).value

            val bottomCardOffset = when (cardsConfiguration.animationDirection) {
                AnimationDirection.TopGoesRightBottomGoesLeft -> Modifier.offset(x = separationDistance + 8.dp, y = 0.dp)
                AnimationDirection.TopGoesLeftBottomGoesRight -> Modifier.offset(x = -separationDistance - 8.dp, y = 0.dp)
            }
            val topCardOffset = when (cardsConfiguration.animationDirection) {
                AnimationDirection.TopGoesRightBottomGoesLeft -> Modifier.offset(x = -separationDistance, y = 8.dp)
                AnimationDirection.TopGoesLeftBottomGoesRight -> Modifier.offset(x = separationDistance, y = 8.dp)
            }

            Card(
                modifier = topCardOffset
                    .then(topCardClickable)
                    .then(topCardSwipe)
                    .zIndex(if (zIndexState.value % 2 != 0) 0f else 2f),
                backgroundColor = Color.Red,
            ) {
                Box(cardsConfiguration.cardModifier) {
                    // Invoke the topCardContent lambda function here
                    cardsConfiguration.topCardContent {
                        cardState.value = (cardState.value + 1) % 2
                    }
                }
            }

            Card(
                modifier = bottomCardOffset
                    .then(bottomCardClickable)
                    .then(bottomCardSwipe)
                    .zIndex(if (zIndexState.value % 2 != 0) 2f else 0f),
                backgroundColor = Color.Blue,
            ) {
                Box(cardsConfiguration.cardModifier) {
                    // Invoke the bottomCardContent lambda function here
                    cardsConfiguration.bottomCardContent {
                        cardState.value = (cardState.value + 1) % 2
                    }
                }
            }
        }
    }

    // Check if the animation has completed and reset card state to allow for infinite animation
    LaunchedEffect(cardState.value) {
        if (cardState.value == 1 || cardState.value == 3) {
            cardsConfiguration.listener?.onAnimationStart()
            delay(cardsConfiguration.timeBetweenAnimations.toLong())
            zIndexState.value = (zIndexState.value + 1) % 2
            cardState.value = (cardState.value + 1) % 2
            cardsConfiguration.listener?.onAnimationEnd()
        }
    }
}

private fun processSwipe(distance: Float, threshold: Float, triggerAnimation: () -> Unit) {
    if (distance < -threshold) {
        triggerAnimation()
    } else if (distance > threshold) {
        triggerAnimation()
    }
}


