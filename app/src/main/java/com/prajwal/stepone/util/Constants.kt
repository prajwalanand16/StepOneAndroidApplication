package com.prajwal.stepone.util

import androidx.compose.ui.graphics.Color

object Constants {
    const val NOTIFICATION_ID = 1
    const val CHANNEL_ID = "step_counter_channel"

    val MILESTONES = listOf(
        Milestone(0, "Deep Space", Color(0xFF0F172A)), // Slate 900
        Milestone(500, "Midnight", Color(0xFF1E1B4B)), // Indigo 950
        Milestone(1000, "Royal Purple", Color(0xFF312E81)), // Indigo 900
        Milestone(1500, "Oceanic", Color(0xFF1E3A8A)), // Blue 900
        Milestone(2000, "Twilight", Color(0xFF3730A3)), // Indigo 800
        Milestone(2500, "Nebula", Color(0xFF4C1D95)), // Violet 900
        Milestone(3000, "Crimson Night", Color(0xFF7F1D1D)), // Red 900
        Milestone(3500, "Emerald Gloom", Color(0xFF064E3B)), // Emerald 900
        Milestone(4000, "Solar Flare", Color(0xFF7C2D12)), // Orange 900
        Milestone(4500, "Deep Magenta", Color(0xFF701A75)), // Fuchsia 900
        Milestone(5000, "Celestial", Color(0xFF111827)) // Gray 900
    )

    data class Milestone(
        val steps: Int,
        val name: String,
        val color: Color
    )

    fun getMilestoneForSteps(steps: Int): Milestone {
        return MILESTONES.lastOrNull { steps >= it.steps } ?: MILESTONES.first()
    }
}
