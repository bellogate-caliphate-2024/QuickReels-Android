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
import com.bellogatecaliphate.create_post.ui.create_post.VideoTrimmerScreen
import com.bellogatecaliphate.create_post.ui.preview_post.PreviewPostScreen

fun NavGraphBuilder.createPostNavGraph(
	serverClientId: String,
	navController: NavHostController,
	onLoginSuccessFul: (userProfilePictureUrl: String) -> Unit
) {
	navigation<CreatePostNavGraphRoute>(startDestination = CreatePostNavGraphRoute.CreatePost::class) {
		composable<CreatePostNavGraphRoute.CreatePost>(
			enterTransition = { fadeIn(animationSpec = tween(500)) },
			exitTransition = { fadeOut(animationSpec = tween(500)) },
			popEnterTransition = { fadeIn(animationSpec = tween(500)) },
			popExitTransition = { fadeOut(animationSpec = tween(500)) }
		) {
			CreatePostScreen(
				serverClientId = serverClientId,
				onNavigateToTrimmer = { videoUri ->
					navController.navigate(CreatePostNavGraphRoute.VideoTrimmer(videoUri))
				},
				onPostClicked = { post ->
					navController.navigate(
						CreatePostNavGraphRoute.PreviewPost(post.videoFilePath, post.caption, true)
					)
				},
				onLoginSuccessFul = onLoginSuccessFul
			)
		}
		
		composable<CreatePostNavGraphRoute.VideoTrimmer>(
			enterTransition = { fadeIn(animationSpec = tween(500)) },
			exitTransition = { fadeOut(animationSpec = tween(500)) },
			popEnterTransition = { fadeIn(animationSpec = tween(500)) },
			popExitTransition = { fadeOut(animationSpec = tween(500)) }
		) { backStackEntry ->
			val trimmerRoute = backStackEntry.toRoute<CreatePostNavGraphRoute.VideoTrimmer>()
			VideoTrimmerScreen(
				videoUri = trimmerRoute.videoUri,
				onTrimFinished = { trimmedPath ->
					navController.navigate(
						CreatePostNavGraphRoute.PreviewPost(
							videoPath = trimmedPath,
							videoCaption = null,
							isReadOnly = false
						)
					) {
						popUpTo(CreatePostNavGraphRoute.VideoTrimmer::class) { inclusive = true }
					}
				},
				onBack = { navController.popBackStack() }
			)
		}
		
		composable<CreatePostNavGraphRoute.PreviewPost>(
			enterTransition = { fadeIn(animationSpec = tween(500)) },
			exitTransition = { fadeOut(animationSpec = tween(500)) },
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