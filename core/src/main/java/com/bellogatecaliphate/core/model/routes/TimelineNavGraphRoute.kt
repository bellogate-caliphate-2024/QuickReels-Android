package com.bellogatecaliphate.core.model.routes

import kotlinx.serialization.Serializable

@Serializable
open class TimelineNavGraphRoute {
	
	@Serializable
	data object Timeline : TimelineNavGraphRoute()
	
	@Serializable
	data object Comments : TimelineNavGraphRoute()
}