package com.bellogatecaliphate.create_post.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.core.model.routes.ConfirmPost
import com.bellogatecaliphate.core.model.routes.CreatePost
import com.bellogatecaliphate.core.model.routes.CreatePostNavGraphRoute
import com.bellogatecaliphate.core.model.routes.PreviewPost
import com.bellogatecaliphate.create_post.ui.confirm_post.UploadPostConfirmationDialog
import com.bellogatecaliphate.create_post.ui.create_post.CreatePostScreen
import com.bellogatecaliphate.create_post.ui.preview_post.PreviewPostScreen
import com.google.gson.Gson

fun NavGraphBuilder.createPostNavGraph(navController: NavHostController) {
	navigation<CreatePostNavGraphRoute>(startDestination = CreatePost::class) {
		composable<CreatePost> {
			CreatePostScreen(
				onPostReadyForPreview = { videoPath, videoCaption, editable ->
					navController.navigate(
						PreviewPost(
							videoPath,
							videoCaption,
							editable
						)
					)
				},
				onPostClicked = { post ->
					navController.navigate(
						PreviewPost(post.videoFilePath, post.caption, false)
					)
				}
			)
		}
		composable<PreviewPost> { backStackEntry ->
			val previewPost = backStackEntry.toRoute<PreviewPost>()
			PreviewPostScreen(
				previewPost.videoPath,
				previewPost.videoCaption,
				previewPost.editable,
				{ post ->
					val postAsJsonString = Gson().toJson(post)
					navController.navigate(ConfirmPost(postAsJsonString))
				})
		}
		composable<ConfirmPost> {
			val postAsJsonString =
					it.toRoute<ConfirmPost>().postAsJsonString
			val post = Gson().fromJson(postAsJsonString, Post::class.java)
			UploadPostConfirmationDialog(post, {
				navController.popBackStack(CreatePost, false)
			})
		}
	}
}