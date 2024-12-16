package com.bellogatecaliphate.timeline.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bellogatecaliphate.core.model.routes.Timeline
import com.bellogatecaliphate.core.model.routes.TimelineNavGraphRoute
import com.bellogatecaliphate.timeline.ui.TimeLineScreen

fun NavGraphBuilder.timelineNavGraph(navController: NavHostController) {
	navigation<TimelineNavGraphRoute>(startDestination = Timeline::class) {
		composable<Timeline> {
			TimeLineScreen()
		}
	}
}