package com.bellogatecaliphate.timeline.end_to_end_tests

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bellogatecaliphate.timeline.ui.TimeLineScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class TimelineEndToEndTest {
	
	@get:Rule
	val composeTestRule = createAndroidComposeRule<ComponentActivity>()
	
	@get:Rule
	var hiltRule = HiltAndroidRule(this)
	
	@Before
	fun init() {
		hiltRule.inject()
	}
	
	@Test
	fun z() {
		composeTestRule.setContent {
			TimeLineScreen(onOpenAccountDetails = onOpenAccountDetails)
		}
		composeTestRule.onNodeWithText("Continue").performClick()
	}
}