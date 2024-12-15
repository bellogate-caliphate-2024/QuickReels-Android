package com.bellogatecaliphate.core.model

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
	// General routes
	@Serializable
	data object Temporary : Route()
	
	@Serializable
	data object Reels : Route()
	
	@Serializable
	data object Account : Route()
	
	// Create Post routes
	@Serializable
	data object CreatePostNavGraph : Route()
	
	@Serializable
	data object CreatePost : Route()
	
	@Serializable
	data class PreviewPost(
		val videoPath: String,
		val videoCaption: String?,
		val editable: Boolean
	) : Route()
	
	@Serializable
	data class ConfirmPost(val postAsJsonString: String) : Route()
	
	// Timeline routes
	@Serializable
	open class TimelineRoute : Route() {
		
		@Serializable
		data object Timeline : TimelineRoute()
		
		@Serializable
		data object Comments : TimelineRoute()
	}
}
