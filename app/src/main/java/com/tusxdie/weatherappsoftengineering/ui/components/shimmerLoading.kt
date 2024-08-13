package com.tusxdie.weatherappsoftengineering.ui.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode

fun Modifier.shimmerLoading(
    enabled: Boolean = true,
    shape: Shape = RectangleShape
): Modifier = composed {
    if (!enabled) {
        return@composed this
    }
    val transition = rememberInfiniteTransition(label = "ShimmerTransition")

    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1024f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000, easing = LinearOutSlowInEasing),
            RepeatMode.Restart
        ),
        label = "ShimmerTranslate",
    )
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.4f),
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(translateAnimation, translateAnimation),
        end = Offset(translateAnimation + 256f, translateAnimation + 256f),
        tileMode = TileMode.Mirror,
    )
    return@composed this.drawWithContent {
        drawRect(
            brush = brush,
            blendMode = BlendMode.SrcAtop
        )
    }
}