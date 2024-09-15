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
import androidx.navigation.navigation
import com.bellogatecaliphate.core.model.Route
import com.bellogatecaliphate.create_post.navigation.createPostNavGraph
import com.bellogatecaliphate.quickreels.ui.theme.QuickReelsTheme
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
                            TemporaryScreen {
                                navController.navigate(Route.CreatePost)
                            }
                        }
                        createPostNavGraph()
                    }
                }
            }
        }
    }
}

@Composable
fun TemporaryScreen(onPostClicked: () -> Unit) {
    Column {
        Button(onClick = onPostClicked) {
            Text(text = "Home")
        }

        Button(onClick = onPostClicked) {
            Text(text = "Post")
        }

        Button(onClick = onPostClicked) {
            Text(text = "Account")
        }
    }
}