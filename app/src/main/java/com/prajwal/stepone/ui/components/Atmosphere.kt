package com.prajwal.stepone.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.prajwal.stepone.util.Constants

@Composable
fun Atmosphere(steps: Int) {
    val milestone = Constants.getMilestoneForSteps(steps)
    val animatedColor by animateColorAsState(
        targetValue = milestone.color,
        animationSpec = tween(durationMillis = 1000),
        label = "AtmosphereColor"
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    animatedColor,
                    animatedColor.copy(alpha = 0.8f)
                )
            )
        )
    }
}
