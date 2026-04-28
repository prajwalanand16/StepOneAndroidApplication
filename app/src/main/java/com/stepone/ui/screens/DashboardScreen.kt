package com.stepone.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stepone.service.StepCounterService
import com.stepone.ui.components.Atmosphere
import com.stepone.ui.components.NumberTicker
import com.stepone.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(onRemoveAdsClicked: () -> Unit = {}) {
    val steps by StepCounterService.stepsFlow.collectAsState()
    val milestone = Constants.getMilestoneForSteps(steps)
    val progress = steps.toFloat() / Constants.STEP_GOAL
    val animatedProgress by animateFloatAsState(targetValue = progress, label = "ProgressAnimation")

    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        showSheet = true
                    }
                )
            }
    ) {
        Atmosphere(steps = steps)

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                sheetState = sheetState,
                containerColor = Color(0xFF1E293B) // Dark background
            ) {
                SettingsContent(onRemoveAdsClicked = onRemoveAdsClicked)
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Debug button for simulation
            Button(
                onClick = { StepCounterService.stepsFlow.value += 500 },
                modifier = Modifier.padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f))
            ) {
                Text("Simulate 500 Steps", color = Color.White)
            }

            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = 1f,
                    modifier = Modifier.size(300.dp),
                    color = Color.White.copy(alpha = 0.1f),
                    strokeWidth = 12.dp,
                    strokeCap = StrokeCap.Round
                )
                CircularProgressIndicator(
                    progress = animatedProgress,
                    modifier = Modifier.size(300.dp),
                    color = Color.White,
                    strokeWidth = 12.dp,
                    strokeCap = StrokeCap.Round
                )
                
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    NumberTicker(value = steps)
                    Text(
                        text = milestone.name,
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(64.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(label = "KCAL", value = "${(steps * 0.04).toInt()}")
                StatItem(label = "KM", value = String.format("%.2f", steps * 0.00076))
                StatItem(label = "MIN", value = "${(steps * 0.01).toInt()}")
            }
        }
    }
}

@Composable
fun SettingsContent(onRemoveAdsClicked: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Settings",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = onRemoveAdsClicked,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
        ) {
            Text("Remove Ads (Go Pro)")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedButton(
            onClick = { StepCounterService.stepsFlow.value = 0 },
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.3f))
        ) {
            Text("Reset Daily Steps", color = Color.White)
        }
        
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.5f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Light
        )
    }
}
