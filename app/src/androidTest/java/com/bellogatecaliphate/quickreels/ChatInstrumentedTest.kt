package com.bellogatecaliphate.quickreels

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bellogatecaliphate.chat.ui.users.TAG_PAGINATED_LIST_OF_USERS
import com.bellogatecaliphate.core.source.remote.RetrofitClient
import com.bellogatecaliphate.core.source.remote.di.RetrofitModule
import com.bellogatecaliphate.core.util.FileReader
import com.bellogatecaliphate.quickreels.ui.MainActivity
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@UninstallModules(RetrofitModule::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ChatInstrumentedTest {
	
	@get:Rule(order = 1)
	var hiltRule = HiltAndroidRule(this)
	
	@BindValue
	@JvmField
	val retrofit: Retrofit = RetrofitClient.getRetrofit(baseUrl = "http://127.0.0.1:8080/")
	
	@get:Rule(order = 2)
	val composeTestRule = createAndroidComposeRule<MainActivity>()
	
	private val mockWebServer = MockWebServer()
	
	@Before
	fun init() {
		hiltRule.inject()
		mockWebServer.start(8080)
		navigateToChat()
	}
	
	@After
	fun tearDown() {
		mockWebServer.shutdown()
	}
	
	@Test
	fun navigateToChat() {
		composeTestRule.onNodeWithText("Chat").performClick()
		composeTestRule.onNodeWithTag("searchInputField").assertIsDisplayed()
	}
	
	@OptIn(ExperimentalTestApi::class)
	@Test
	fun whenListOfUsersIsReturnedThenAPaginatedListOfTheseUsersIsDisplayed() {
		val response = MockResponse().setResponseCode(200).setBody(
			FileReader.getJsonDataFromAsset(composeTestRule.activity, "list_of_users.json")
		)
		mockWebServer.enqueue(response)
		
		composeTestRule.onNodeWithTag(TAG_PAGINATED_LIST_OF_USERS).assertIsDisplayed()
		//composeTestRule.onNodeWithText("Jeff-1").assertIsDisplayed()
		/*composeTestRule.onNodeWithTag(TAG_PAGINATED_LIST_OF_USERS).performScrollToNode(
			hasText("Jeff-1")
		)*/
		//composeTestRule.onNode(hasText("Jeff-1")).assertExists()
		
		composeTestRule.waitUntilAtLeastOneExists(hasText("Jeff-1"))
		composeTestRule.onNodeWithTag(TAG_PAGINATED_LIST_OF_USERS).performScrollToNode(
			hasText("Jeff-1")
		)
		composeTestRule.onNodeWithText("Jeff-1").assertIsDisplayed()
		composeTestRule.onNodeWithTag(TAG_PAGINATED_LIST_OF_USERS).performScrollToNode(
			hasText("Jeff-10")
		)
	}
}