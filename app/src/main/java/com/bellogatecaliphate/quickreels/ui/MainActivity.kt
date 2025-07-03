package com.bellogatecaliphate.quickreels.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bellogatecaliphate.account.navigation.accountNavGraph
import com.bellogatecaliphate.chat.navigation.chatNavGraph
import com.bellogatecaliphate.core.model.routes.Route
import com.bellogatecaliphate.core.model.routes.account.AccountNavGraphRoute
import com.bellogatecaliphate.core.model.routes.timeline.TimelineNavGraphRoute
import com.bellogatecaliphate.core.ui.theme.QuickReelsTheme
import com.bellogatecaliphate.create_post.navigation.createPostNavGraph
import com.bellogatecaliphate.quickreels.R
import com.bellogatecaliphate.quickreels.ui.menu.BottomAppBar
import com.bellogatecaliphate.timeline.navigation.timelineNavGraph
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	
	private val viewModel: MainActivityViewModel by viewModels()
	
	@OptIn(ExperimentalComposeUiApi::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			QuickReelsTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier
						.fillMaxSize()
						.semantics {
							testTagsAsResourceId = true
						},
					color = MaterialTheme.colorScheme.background
				) {
					val uiState = viewModel.uiState.collectAsStateWithLifecycle()
					QuickReelsScreen(
						uiState = uiState.value,
						onProfilePictureChanged = viewModel::onProfilePictureChanged
					)
				}
			}
		}
	}
}

@Composable
private fun QuickReelsScreen(
	uiState: UiState,
	onProfilePictureChanged: (profilePictureUrl: String?) -> Unit = {}
) {
	val systemUiController = rememberSystemUiController()
	val darkTheme = isSystemInDarkTheme()
	val navController = rememberNavController()
	val serverClientId =
			LocalContext.current.getString(R.string.default_web_client_id) // This always shows
	// error as if the string is not found. Just ignore the error and build the app.
	SideEffect {
		if (darkTheme) {
			systemUiController.setSystemBarsColor(
				color = Color.Black
			)
		} else {
			systemUiController.setSystemBarsColor(
				color = Color.White
			)
		}
	}
	Scaffold(
		bottomBar = {
			BottomAppBar(
				currentUserProfilePictureUrl = uiState.user?.profilePictureUrl,
				onMenuItemClicked = { route: Route ->
					navController.navigate(route) {
						popUpTo(navController.graph.id) {
							saveState = true
						}
						restoreState = true
					}
				}
			)
		}
	) { innerPadding ->
		Box(modifier = Modifier.padding(innerPadding)) {
			NavHost(navController, startDestination = TimelineNavGraphRoute::class) {
				timelineNavGraph(
					navController = navController,
					onOpenAccountDetails = { accountUserEmail ->
						navController.navigate(AccountNavGraphRoute.Account(accountUserEmail))
					})
				createPostNavGraph(navController)
				chatNavGraph(navController)
				accountNavGraph(navController, serverClientId) {
					onProfilePictureChanged(it)
				}
			}
		}
	}
}
