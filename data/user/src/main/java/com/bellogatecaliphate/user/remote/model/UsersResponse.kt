package com.bellogatecaliphate.user.remote.model

data class UsersResponse(
	val currentPage: Int?,
	val nextPage: Int?,
	val listOfUsers: List<UserResponse>?,
	val isLastPage: Boolean? // Discuss with BE to return false for this value when there are no more pages to load.
)