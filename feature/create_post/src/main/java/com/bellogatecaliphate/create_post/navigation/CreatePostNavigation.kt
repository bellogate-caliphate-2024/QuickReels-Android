package com.bellogatecaliphate.create_post.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.bellogatecaliphate.core.model.Route
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.create_post.ui.confirm_post.UploadPostConfirmationDialog
import com.bellogatecaliphate.create_post.ui.create_post.CreatePostScreen
import com.bellogatecaliphate.create_post.ui.preview_post.PreviewPostScreen
import com.google.gson.Gson

fun NavGraphBuilder.createPostNavGraph(navController: NavHostController) {
	navigation<Route.CreatePostNavGraph>(startDestination = Route.CreatePost::class) {
		composable<Route.CreatePost> {
			CreatePostScreen(
				onPostReadyForPreview = { videoPath, videoCaption, editable ->
					navController.navigate(Route.PreviewPost(videoPath, videoCaption, editable))
				},
				onPostClicked = { post ->
					navController.navigate(
						Route.PreviewPost(post.videoFilePath, post.caption, false)
					)
				}
			)
		}
		composable<Route.PreviewPost> { backStackEntry ->
			val previewPost = backStackEntry.toRoute<Route.PreviewPost>()
			PreviewPostScreen(
				previewPost.videoPath,
				previewPost.videoCaption,
				previewPost.editable,
				{ post ->
					val postAsJsonString = Gson().toJson(post)
					navController.navigate(Route.ConfirmPost(postAsJsonString))
				})
		}
		composable<Route.ConfirmPost> {
			val postAsJsonString = it.toRoute<Route.ConfirmPost>().postAsJsonString
			val post = Gson().fromJson(postAsJsonString, Post::class.java)
			UploadPostConfirmationDialog(post, {
				navController.popBackStack(Route.CreatePost, false)
			})
		}
	}
}