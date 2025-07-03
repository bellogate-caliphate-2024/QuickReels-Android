package com.bellogatecaliphate.account.ui.screens.profile_detail

import com.bellogatecaliphate.core.model.dto.User

data class UiState(
	val isLoading: Boolean = false,
	val unableToGetUser: Boolean = false,
	val user: User? = null,
	val accountBelongsToLoggedInUser: Boolean = false,
)