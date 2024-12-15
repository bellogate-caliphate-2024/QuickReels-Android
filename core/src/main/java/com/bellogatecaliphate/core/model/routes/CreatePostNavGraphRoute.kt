package com.bellogatecaliphate.core.model.routes

import kotlinx.serialization.Serializable

@Serializable
open class CreatePostNavGraphRoute {
	
	@Serializable
	data object CreatePost : CreatePostNavGraphRoute()
	
	@Serializable
	data class PreviewPost(
		val videoPath: String,
		val videoCaption: String?,
		val editable: Boolean
	) : CreatePostNavGraphRoute()
	
	@Serializable
	data class ConfirmPost(val postAsJsonString: String) : CreatePostNavGraphRoute()
}