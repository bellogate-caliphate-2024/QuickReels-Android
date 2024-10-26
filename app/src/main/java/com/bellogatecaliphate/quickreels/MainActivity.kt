package com.bellogatecaliphate.quickreels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bellogatecaliphate.core.model.Route
import com.bellogatecaliphate.create_post.navigation.createPostNavGraph
import com.bellogatecaliphate.quickreels.ui.theme.QuickReelsTheme
import com.bellogatecaliphate.timeline.navigation.timelineNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			QuickReelsTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					val navController = rememberNavController()
					NavHost(navController, startDestination = Route.Temporary, Modifier) {
						composable<Route.Temporary> {
							TemporaryScreen { name ->
								when (name) {
									"Timeline"   -> navController.navigate(Route.CreatePostNavGraph)
									"CreatePost" -> navController.navigate(Route.CreatePostNavGraph)
								}
								
							}
						}
						createPostNavGraph(navController)
						timelineNavGraph(navController)
					}
				}
			}
		}
	}
}

@Composable
fun TemporaryScreen(onButtonClicked: (String) -> Unit) {
	Column {
		Button(onClick = { onButtonClicked("Timeline") }) {
			Text(text = "Timeline")
		}
		
		Button(onClick = { onButtonClicked("CreatePost") }) {
			Text(text = "CreatePost")
		}
		
		Button(onClick = { onButtonClicked("Account") }) {
			Text(text = "Account")
		}
	}
}