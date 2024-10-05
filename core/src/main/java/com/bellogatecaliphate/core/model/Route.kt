package com.bellogatecaliphate.core.model

import androidx.annotation.DrawableRes
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.model.dto.Post
import kotlinx.serialization.Serializable

@Serializable
sealed class Route(@DrawableRes val icon: Int) {
	// General routes
	@Serializable
	data object Temporary : Route(R.drawable.ic_launcher_foreground)
	
	@Serializable
	data object Reels : Route(R.drawable.ic_launcher_foreground)
	
	@Serializable
	data object Account : Route(R.drawable.ic_launcher_foreground)
	
	// Create Post routes
	@Serializable
	data object CreatePostNavGraph : Route(R.drawable.ic_launcher_foreground)
	
	@Serializable
	data object CreatePost : Route(R.drawable.ic_launcher_foreground)
	
	@Serializable
	data class PreviewPost(
		val videoPath: String,
		val videoCaption: String?,
		val editable: Boolean
	) : Route(R.drawable.ic_launcher_foreground)
	
	@Serializable
	data class ConfirmPost(val post: Post) : Route(R.drawable.ic_launcher_foreground)
}
