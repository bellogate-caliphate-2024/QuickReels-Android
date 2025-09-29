package com.bellogatecaliphate.create_post.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.bellogatecaliphate.core.model.routes.create_post.CreatePostNavGraphRoute
import com.bellogatecaliphate.create_post.ui.create_post.CreatePostScreen
import com.bellogatecaliphate.create_post.ui.preview_post.PreviewPostScreen

fun NavGraphBuilder.createPostNavGraph(serverClientId: String, navController: NavHostController) {
	navigation<CreatePostNavGraphRoute>(startDestination = CreatePostNavGraphRoute.CreatePost::class) {
		composable<CreatePostNavGraphRoute.CreatePost> {
			CreatePostScreen(
				serverClientId = serverClientId,
				onPostReadyForPreview = { videoPath, videoCaption, isReadOnly ->
					navController.navigate(
						CreatePostNavGraphRoute.PreviewPost(
							videoPath,
							videoCaption,
							isReadOnly
						)
					)
				},
				onPostClicked = { post ->
					navController.navigate(
						CreatePostNavGraphRoute.PreviewPost(post.videoFilePath, post.caption, true)
					)
				}
			)
		}
		composable<CreatePostNavGraphRoute.PreviewPost>(
			popEnterTransition = {
				fadeIn(animationSpec = tween(500))
			},
			popExitTransition = {
				fadeOut(animationSpec = tween(500))
			},
			content = { backStackEntry ->
				val previewPost = backStackEntry.toRoute<CreatePostNavGraphRoute.PreviewPost>()
				PreviewPostScreen(
					previewPost.videoPath,
					previewPost.videoCaption,
					previewPost.isReadOnly,
					dismiss = { navController.popBackStack() }
				)
			}
		)
	}
}