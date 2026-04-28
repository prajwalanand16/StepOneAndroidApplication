package com.prajwal.stepone.ui.screens

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
import com.prajwal.stepone.service.StepCounterService
import com.prajwal.stepone.ui.components.Atmosphere
import com.prajwal.stepone.ui.components.NumberTicker
import com.prajwal.stepone.util.Constants

import androidx.compose.ui.tooling.preview.Preview
import com.prajwal.stepone.ui.theme.StepOneTheme

@Preview(showBackground = true, backgroundColor = 0xFF0F172A)
@Composable
fun DashboardPreview() {
    StepOneTheme {
        DashboardContent(
            steps = 0,
            isPro = false,
            stepGoal = 10000,
            onRemoveAdsClicked = {},
            onStepGoalChanged = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0F172A)
@Composable
fun DashboardActivePreview() {
    StepOneTheme {
        DashboardContent(
            steps = 5432,
            isPro = false,
            stepGoal = 10000,
            onRemoveAdsClicked = {},
            onStepGoalChanged = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0F172A)
@Composable
fun DashboardMilestonePreview() {
    StepOneTheme {
        DashboardContent(
            steps = 12500,
            isPro = true,
            stepGoal = 15000,
            onRemoveAdsClicked = {},
            onStepGoalChanged = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF1E293B)
@Composable
fun SettingsPreview() {
    StepOneTheme {
        Surface(color = Color(0xFF1E293B)) {
            SettingsContent(
                isPro = false,
                stepGoal = 8000,
                onRemoveAdsClicked = {},
                onStepGoalChanged = {}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    isPro: Boolean = false,
    stepGoal: Int = 10000,
    onRemoveAdsClicked: () -> Unit = {},
    onStepGoalChanged: (Int) -> Unit = {}
) {
    val steps by StepCounterService.stepsFlow.collectAsState()
    DashboardContent(
        steps = steps,
        isPro = isPro,
        stepGoal = stepGoal,
        onRemoveAdsClicked = onRemoveAdsClicked,
        onStepGoalChanged = onStepGoalChanged
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(
    steps: Int,
    isPro: Boolean,
    stepGoal: Int,
    onRemoveAdsClicked: () -> Unit,
    onStepGoalChanged: (Int) -> Unit
) {
    val milestone = Constants.getMilestoneForSteps(steps)
    val progress = steps.toFloat() / stepGoal
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
                SettingsContent(
                    isPro = isPro,
                    stepGoal = stepGoal,
                    onRemoveAdsClicked = onRemoveAdsClicked,
                    onStepGoalChanged = onStepGoalChanged
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
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
fun SettingsContent(
    isPro: Boolean = false,
    stepGoal: Int = 10000,
    onRemoveAdsClicked: () -> Unit = {},
    onStepGoalChanged: (Int) -> Unit = {}
) {
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

        Text(
            text = "Daily Step Goal: $stepGoal",
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 16.sp
        )
        Slider(
            value = stepGoal.toFloat(),
            onValueChange = { onStepGoalChanged(it.toInt()) },
            valueRange = 2000f..20000f,
            steps = 17,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.White,
                inactiveTrackColor = Color.White.copy(alpha = 0.2f)
            )
        )

        Spacer(modifier = Modifier.height(32.dp))
        
        if (!isPro) {
            Button(
                onClick = onRemoveAdsClicked,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
            ) {
                Text("Remove Ads (Go Pro)")
            }
            Spacer(modifier = Modifier.height(16.dp))
        } else {
            Text(
                text = "Premium Active ✨",
                color = Color.Cyan,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        val context = androidx.compose.ui.platform.LocalContext.current
        OutlinedButton(
            onClick = {
                StepCounterService.stepsFlow.value = 0
                StepCounterService.addSteps(context, 0) // Trigger persistence
            },
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
