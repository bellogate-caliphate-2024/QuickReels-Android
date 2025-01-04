package com.bellogatecaliphate.chat

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bellogatecaliphate.chat.ui.SelectUserScreen
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
	val composeTestRule = createComposeRule()
	
	@Before
	fun init() {
		hiltRule.inject()
	}
	
	@Test
	fun useAppContext() {
		composeTestRule.setContent {
			SelectUserScreen {
			
			}
		}
	}
}