package com.bellogatecaliphate.timeline.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bellogatecaliphate.core.model.Route

fun NavGraphBuilder.timelineNavGraph(navController: NavHostController) {
	navigation<Route.TimelineRoute>(startDestination = Route.TimelineRoute.Timeline::class) {
		composable<Route.TimelineRoute.Timeline> {
		
		}
	}
}