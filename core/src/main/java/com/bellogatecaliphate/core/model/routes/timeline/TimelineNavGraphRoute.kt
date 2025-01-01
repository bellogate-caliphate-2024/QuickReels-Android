package com.bellogatecaliphate.core.model.routes.timeline

import com.bellogatecaliphate.core.model.routes.Route
import kotlinx.serialization.Serializable

/**
 * We are using the style of creating routes explained here https://developer.android.com/guide/navigation/design/nested-graphs#compose
 * **/

// Route for nested graph
@Serializable
data object TimelineNavGraphRoute : Route() {
	// Routes inside nested graph
	@Serializable
	data object Timeline
	
	@Serializable
	data object Comments
}