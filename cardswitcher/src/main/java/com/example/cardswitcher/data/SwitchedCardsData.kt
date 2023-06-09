package com.example.cardswitcher.data

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cardswitcher.enums.AnimationDirection

data class SwitchedCardsData(
    val animationDuration: Int = 1000,
    val triggerOnClick: Boolean = true,
    val topCardContent: (@Composable (triggerAnimation: () -> Unit) -> Unit) = {},
    val bottomCardContent: (@Composable (triggerAnimation: () -> Unit) -> Unit) = {},
    val cardModifier: Modifier = Modifier.size(200.dp, 150.dp),
    val animationDirection: AnimationDirection = AnimationDirection.TopRightBottomLeft,
    val onAnimationStart: (() -> Unit)? = null
)
