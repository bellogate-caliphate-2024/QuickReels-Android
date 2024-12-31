package com.bellogatecaliphate.quickreels.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.bellogatecaliphate.core.model.routes.Route
import com.bellogatecaliphate.core.model.routes.timeline.TimelineNavGraphRoute
import com.bellogatecaliphate.create_post.navigation.createPostNavGraph
import com.bellogatecaliphate.quickreels.ui.menu.BottomAppBar
import com.bellogatecaliphate.quickreels.ui.theme.QuickReelsTheme
import com.bellogatecaliphate.timeline.navigation.timelineNavGraph
import dagger.hilt.android.AndroidEntryPoint

private const val CONTENT_SECTION = "contentSection"
private const val BOTTOM_NAVIGATION_BAR = "bottomNavigationBar"

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
					QuickReelsScreen()
				}
			}
		}
	}
}

@Composable
private fun QuickReelsScreen(constraintSet: ConstraintSet = decoupledConstraints()) {
	ConstraintLayout(constraintSet, modifier = Modifier.fillMaxSize()) {
		val navController = rememberNavController()
		NavHost(
			navController,
			startDestination = TimelineNavGraphRoute::class,
			Modifier.layoutId(CONTENT_SECTION)
		) {
			timelineNavGraph(navController)
			createPostNavGraph(navController)
		}
		BottomAppBar(Modifier.layoutId(BOTTOM_NAVIGATION_BAR)) { route: Route ->
			navController.navigate(route)
		}
	}
}

private fun decoupledConstraints(): ConstraintSet {
	return ConstraintSet {
		val contentSection = createRefFor(CONTENT_SECTION)
		val bottomNavigationBar = createRefFor(BOTTOM_NAVIGATION_BAR)
		
		constrain(contentSection) {
			top.linkTo(parent.top)
			start.linkTo(parent.start)
			end.linkTo(parent.end)
			bottom.linkTo(bottomNavigationBar.top)
		}
		
		constrain(bottomNavigationBar) {
			start.linkTo(parent.start)
			end.linkTo(parent.end)
			bottom.linkTo(parent.bottom)
		}
	}
}

@Preview
@Composable
private fun PreviewQuickReelsScreen() {
	QuickReelsScreen()
}
