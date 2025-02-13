package com.yeyosystem.recipe.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.yeyosystem.recipe.MainActivity
import org.junit.Rule
import org.junit.Test

class RecipeNavigationTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNavigationToDetailScreen() {
        onView(withText("Recipe App")).check(matches(isDisplayed()))
        onView(withText("Sushi")).perform(click())
        onView(withText("Description:")).check(matches(isDisplayed()))
    }
}