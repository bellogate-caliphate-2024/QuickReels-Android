package com.bellogatecaliphate.account.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.bellogatecaliphate.account.ui.AccountScreen
import com.bellogatecaliphate.account.ui.screens.profile_detail.ProfileDetailScreen
import com.bellogatecaliphate.core.model.routes.account.AccountNavGraphRoute

fun NavGraphBuilder.accountNavGraph(
	navController: NavHostController,
	serverClientId: String,
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
				onBackPressed = { navController.popBackStack() }
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
				ProfileDetailScreen(profileDetail.userEmail) {
					navController.popBackStack()
				}
			}
		)
	}
}