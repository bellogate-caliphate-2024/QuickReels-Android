package com.bellogatecaliphate.account.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.bellogatecaliphate.account.ui.AccountScreen
import com.bellogatecaliphate.account.ui.screens.profile_detail.ProfileDetailScreen
import com.bellogatecaliphate.account.ui.screens.view_and_edit_content.EditContentScreen
import com.bellogatecaliphate.core.model.routes.account.AccountNavGraphRoute

fun NavGraphBuilder.accountNavGraph(
	navController: NavHostController,
	serverClientId: String,
	onLogOut: () -> Unit,
	onLoginSuccessFul: (userProfilePictureUrl: String) -> Unit
) {
	navigation<AccountNavGraphRoute>(startDestination = AccountNavGraphRoute.Account::class) {
		composable<AccountNavGraphRoute.Account> { backStackEntry ->
			val account = backStackEntry.toRoute<AccountNavGraphRoute.Account>()
			AccountScreen(
				serverClientId = serverClientId,
				userEmail = account.userEmail,
				onLoginSuccessFul = onLoginSuccessFul,
				onOpenProfileDetails = { userEmail ->
					navController.navigate(
						AccountNavGraphRoute.ProfileDetail(userEmail = userEmail)
					)
				},
				onBackPressed = { navController.popBackStack() },
				onOpenContent = { content ->
					navController.navigate(
						AccountNavGraphRoute.EditContent(
							contentId = content.id,
							userEmail = content.userId,
							contentCaption = content.caption
						)
					)
				}
			)
		}
		composable<AccountNavGraphRoute.ProfileDetail>(
			popEnterTransition = {
				fadeIn(animationSpec = tween(500))
			},
			popExitTransition = {
				fadeOut(animationSpec = tween(500))
			},
			content = { backStackEntry ->
				val profileDetail = backStackEntry.toRoute<AccountNavGraphRoute.ProfileDetail>()
				ProfileDetailScreen(
					userEmailToSearchFor = profileDetail.userEmail,
					onClose = { navController.popBackStack() },
					onLogOut = onLogOut
				)
			}
		)
		composable<AccountNavGraphRoute.EditContent>(
			enterTransition = {
				slideInHorizontally(
					initialOffsetX = { fullWidth -> fullWidth }, // slide from right
					animationSpec = tween(500)
				)
			},
			exitTransition = {
				slideOutHorizontally(
					targetOffsetX = { fullWidth -> - fullWidth }, // slide to left
					animationSpec = tween(500)
				)
			},
			popEnterTransition = {
				slideInHorizontally(
					initialOffsetX = { fullWidth -> - fullWidth }, // coming back from left
					animationSpec = tween(500)
				)
			},
			popExitTransition = {
				slideOutHorizontally(
					targetOffsetX = { fullWidth -> fullWidth }, // exit to right
					animationSpec = tween(500)
				)
			},
			content = { backStackEntry ->
				val editContent = backStackEntry.toRoute<AccountNavGraphRoute.EditContent>()
				EditContentScreen()
			}
		)
	}
}