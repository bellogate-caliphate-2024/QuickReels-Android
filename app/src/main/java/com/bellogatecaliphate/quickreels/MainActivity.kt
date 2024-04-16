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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bellogatecaliphate.create_video.ui.VideoRecorderScreen
import com.bellogatecaliphate.quickreels.model.Route
import com.bellogatecaliphate.quickreels.model.Screen
import com.bellogatecaliphate.quickreels.ui.theme.QuickReelsTheme

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
                    NavHost(navController, startDestination = Screen.Temp.route, Modifier) {
                        composable(Screen.Temp.route) {
                            TemporaryScreen {
                                navController.navigate(Screen.Post.route)
                            }
                        }
                        composable(Screen.Post.route) {
                            VideoRecorderScreen()
                        }
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
            Text(text = "Chat")
        }
    }
}