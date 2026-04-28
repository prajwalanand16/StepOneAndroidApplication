package com.stepone.util

import org.junit.Assert.assertEquals
import org.junit.Test

class ConstantsTest {

    @Test
    fun `getMilestoneForSteps returns correct milestone for 0 steps`() {
        val result = Constants.getMilestoneForSteps(0)
        assertEquals("Deep Space", result.name)
    }

    @Test
    fun `getMilestoneForSteps returns correct milestone for midway steps`() {
        val result = Constants.getMilestoneForSteps(1200)
        assertEquals("Royal Purple", result.name)
    }

    @Test
    fun `getMilestoneForSteps returns last milestone for very high steps`() {
        val result = Constants.getMilestoneForSteps(20000)
        assertEquals("Celestial", result.name)
    }
}
