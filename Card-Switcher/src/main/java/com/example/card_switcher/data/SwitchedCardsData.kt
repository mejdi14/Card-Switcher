package com.example.card_switcher.data

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.card_switcher.enums.AnimationDirection

data class SwitchedCardsData(
    val animationDuration: Int = 500,
    val timeBetweenAnimations: Int = 500,
    val triggerOnClick: Boolean = true,
    val topCardContent: (@Composable (triggerAnimation: () -> Unit) -> Unit) = {},
    val bottomCardContent: (@Composable (triggerAnimation: () -> Unit) -> Unit) = {},
    val cardModifier: Modifier = Modifier.size(200.dp, 150.dp),
    val animationDirection: AnimationDirection = AnimationDirection.TopRightBottomLeft,
    val onAnimationStart: (() -> Unit)? = null
)
