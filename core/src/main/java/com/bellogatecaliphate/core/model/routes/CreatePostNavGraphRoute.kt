package com.bellogatecaliphate.core.model.routes

import kotlinx.serialization.Serializable

/**
 * We are using the style of creating routes explained here https://developer.android.com/guide/navigation/design/nested-graphs#compose
 * **/

// Route for nested graph
@Serializable
data object CreatePostNavGraphRoute

// Routes inside nested graph
@Serializable
data object CreatePost

@Serializable
data class PreviewPost(
	val videoPath: String,
	val videoCaption: String?,
	val editable: Boolean
)

@Serializable
data class ConfirmPost(val postAsJsonString: String)