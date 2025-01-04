package com.bellogatecaliphate.chat.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.bellogatecaliphate.chat.ui.SelectUserScreen
import com.bellogatecaliphate.chat.ui.chat_room.ChatRoom
import com.bellogatecaliphate.core.model.routes.chat.ChatNavGraphRoute

fun NavGraphBuilder.chatNavGraph(navController: NavHostController) {
	navigation<ChatNavGraphRoute>(startDestination = ChatNavGraphRoute.SelectUser::class) {
		composable<ChatNavGraphRoute.SelectUser> {
			SelectUserScreen { navController.navigate(ChatNavGraphRoute.Chat(it.email)) }
		}
		composable<ChatNavGraphRoute.Chat> { backStackEntry ->
			val chatUser = backStackEntry.toRoute<ChatNavGraphRoute.Chat>()
			ChatRoom(chatUser.chatUserId)
		}
	}
}