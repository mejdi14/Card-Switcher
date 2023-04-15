package com.example.card_switcher.enums

sealed class AnimationDirection {
    object TopRightBottomLeft : AnimationDirection()
    object TopLeftBottomRight : AnimationDirection()
}
