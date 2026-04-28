package com.stepone.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun NumberTicker(
    value: Int,
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {
    val digits = value.toString().toList()
    
    Row(modifier = modifier) {
        digits.forEachIndexed { index, digit ->
            AnimatedContent(
                targetState = digit,
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInVertically { it } + fadeIn() togetherWith slideOutVertically { -it } + fadeOut()
                    } else {
                        slideInVertically { -it } + fadeIn() togetherWith slideOutVertically { it } + fadeOut()
                    }.using(SizeTransform(clip = false))
                },
                label = "DigitAnimation"
            ) { char ->
                Text(
                    text = char.toString(),
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            }
        }
    }
}
