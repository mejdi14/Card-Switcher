package com.example.cardswitcher.enums

sealed class AnimationDirection {
    object TopRightBottomLeft : AnimationDirection()
    object TopLeftBottomRight : AnimationDirection()
}
