package com.bellogatecaliphate.create_post.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.core.model.routes.CreatePostNavGraphRoute
import com.bellogatecaliphate.create_post.ui.confirm_post.UploadPostConfirmationDialog
import com.bellogatecaliphate.create_post.ui.create_post.CreatePostScreen
import com.bellogatecaliphate.create_post.ui.preview_post.PreviewPostScreen
import com.google.gson.Gson

fun NavGraphBuilder.createPostNavGraph(navController: NavHostController) {
	navigation<CreatePostNavGraphRoute>(startDestination = CreatePostNavGraphRoute.CreatePost::class) {
		composable<CreatePostNavGraphRoute.CreatePost> {
			CreatePostScreen(
				onPostReadyForPreview = { videoPath, videoCaption, editable ->
					navController.navigate(
						CreatePostNavGraphRoute.PreviewPost(
							videoPath,
							videoCaption,
							editable
						)
					)
				},
				onPostClicked = { post ->
					navController.navigate(
						CreatePostNavGraphRoute.PreviewPost(post.videoFilePath, post.caption, false)
					)
				}
			)
		}
		composable<CreatePostNavGraphRoute.PreviewPost> { backStackEntry ->
			val previewPost = backStackEntry.toRoute<CreatePostNavGraphRoute.PreviewPost>()
			PreviewPostScreen(
				previewPost.videoPath,
				previewPost.videoCaption,
				previewPost.editable,
				{ post ->
					val postAsJsonString = Gson().toJson(post)
					navController.navigate(CreatePostNavGraphRoute.ConfirmPost(postAsJsonString))
				})
		}
		composable<CreatePostNavGraphRoute.ConfirmPost> {
			val postAsJsonString =
					it.toRoute<CreatePostNavGraphRoute.ConfirmPost>().postAsJsonString
			val post = Gson().fromJson(postAsJsonString, Post::class.java)
			UploadPostConfirmationDialog(post, {
				navController.popBackStack(CreatePostNavGraphRoute.CreatePost, false)
			})
		}
	}
}