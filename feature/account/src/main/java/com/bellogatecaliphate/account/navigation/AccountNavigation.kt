package com.bellogatecaliphate.account.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bellogatecaliphate.account.ui.AccountScreen
import com.bellogatecaliphate.core.model.routes.account.Account
import com.bellogatecaliphate.core.model.routes.account.AccountNavGraphRoute

fun NavGraphBuilder.accountNavGraph(navController: NavHostController, serverClientId: String) {
	navigation<AccountNavGraphRoute>(startDestination = Account::class) {
		composable<Account> {
			AccountScreen(serverClientId)
		}
	}
}