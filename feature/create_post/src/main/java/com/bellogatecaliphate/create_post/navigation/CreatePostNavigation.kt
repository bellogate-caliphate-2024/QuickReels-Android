package com.bellogatecaliphate.create_post.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.bellogatecaliphate.core.model.Route
import com.bellogatecaliphate.create_post.ui.confirm_post.UploadPostConfirmationDialog
import com.bellogatecaliphate.create_post.ui.create_post.CreatePostScreen
import com.bellogatecaliphate.create_post.ui.preview_post.PreviewPost

fun NavGraphBuilder.createPostNavGraph(navController: NavHostController) {
	navigation<Route.CreatePostNavGraph>(startDestination = Route.CreatePost::class) {
		composable<Route.CreatePost> {
			CreatePostScreen { videoPath ->
				navController.navigate(Route.PreviewPost(videoPath))
			}
		}
		composable<Route.PreviewPost> { backStackEntry ->
			val previewPost = backStackEntry.toRoute<Route.PreviewPost>()
			PreviewPost(previewPost.videoPath, { post ->
				navController.navigate(Route.ConfirmPost(post))
			})
		}
		composable<Route.ConfirmPost> {
			val post = it.toRoute<Route.ConfirmPost>().post
			UploadPostConfirmationDialog(post, {
				navController.popBackStack(Route.CreatePost, false)
			})
		}
	}
}