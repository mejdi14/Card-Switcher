package com.example.card_switcher.enums

sealed class AnimationDirection {
    object TopGoesRightBottomGoesLeft : AnimationDirection()
    object TopGoesLeftBottomGoesRight : AnimationDirection()
}
