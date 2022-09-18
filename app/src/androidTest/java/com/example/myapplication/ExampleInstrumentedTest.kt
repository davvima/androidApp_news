package com.example.myapplication

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule(order=1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order=2)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun itemsAddedToScreen() {
        // Context of the app under test.
        composeTestRule.setContent {
            ListScreen(navController = rememberNavController())
        }
        composeTestRule.onNodeWithText("Tesla to build the world's biggest CCS-compatible Supercharger locations with Magic Docks").assertExists()
        composeTestRule.onNodeWithText("Tesla’s battery supplier in China is hanging by a thread, with a ‘factory bubble’ the only way it’s still working").assertExists()
    }
}