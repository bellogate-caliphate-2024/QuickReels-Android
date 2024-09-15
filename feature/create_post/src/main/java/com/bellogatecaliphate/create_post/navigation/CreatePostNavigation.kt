package com.bellogatecaliphate.create_post.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.bellogatecaliphate.core.model.Route
import com.bellogatecaliphate.create_post.ui.CreatePostScreen
import com.bellogatecaliphate.create_post.ui.preview_post.PreviewPost

fun NavGraphBuilder.createPostNavGraph(navController : NavHostController) {
	navigation<Route.CreatePostNavGraph>(startDestination = Route.CreatePost::class) {
		composable<Route.CreatePost> {
			CreatePostScreen { videoPath ->
				navController.navigate(Route.PreviewPost(videoPath))
			}
		}
		composable<Route.PreviewPost> { backStackEntry ->
			val previewPost = backStackEntry.toRoute<Route.PreviewPost>()
			PreviewPost(previewPost.videoPath)
		}
	}
}