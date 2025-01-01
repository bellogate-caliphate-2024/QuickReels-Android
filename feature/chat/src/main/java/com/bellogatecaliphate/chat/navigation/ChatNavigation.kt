package com.bellogatecaliphate.chat.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bellogatecaliphate.chat.ui.SelectUserScreen
import com.bellogatecaliphate.core.model.routes.chat.ChatNavGraphRoute
import com.bellogatecaliphate.core.model.routes.chat.SelectUser

fun NavGraphBuilder.chatNavGraph(navController: NavHostController) {
	navigation<ChatNavGraphRoute>(startDestination = SelectUser::class) {
		composable<SelectUser> {
			SelectUserScreen()
		}
	}
}