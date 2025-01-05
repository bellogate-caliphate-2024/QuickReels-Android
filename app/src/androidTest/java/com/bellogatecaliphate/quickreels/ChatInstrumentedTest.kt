package com.bellogatecaliphate.quickreels

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bellogatecaliphate.quickreels.ui.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ChatInstrumentedTest {
	
	@get:Rule(order = 1)
	var hiltRule = HiltAndroidRule(this)
	
	@get:Rule(order = 2)
	val composeTestRule = createAndroidComposeRule<MainActivity>()
	
	@Before
	fun init() {
		hiltRule.inject()
		navigateToChat()
	}
	
	@Test
	fun navigateToChat() {
		composeTestRule.onNodeWithText("Chat").performClick()
	}
	
	@Test
	fun whenListOfUsersIsReturnedThenAPaginatedListOfTheseUsersIsDisplayed() {
		composeTestRule.onNodeWithTag("searchInputField").assertIsDisplayed()
	}
}