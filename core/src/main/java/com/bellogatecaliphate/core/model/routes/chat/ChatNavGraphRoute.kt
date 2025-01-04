package com.bellogatecaliphate.core.model.routes.chat

import com.bellogatecaliphate.core.model.routes.Route
import kotlinx.serialization.Serializable

@Serializable
data object ChatNavGraphRoute : Route() {
	@Serializable
	data object SelectUser
	
	@Serializable
	data class Chat(val chatUserId: String)
}

