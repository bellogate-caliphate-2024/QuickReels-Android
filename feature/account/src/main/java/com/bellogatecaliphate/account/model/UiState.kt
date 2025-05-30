package com.bellogatecaliphate.account.model

import androidx.paging.PagingData
import com.bellogatecaliphate.core.model.dto.Content
import com.bellogatecaliphate.core.model.dto.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

internal data class UiState(
	val isLoading: Boolean = false,
	val showLoginScreen: Boolean = false,
	val isUserLoggedIn: Boolean = false,
	val user: User? = null,
	val listOfContentHistory: Flow<PagingData<Content>> = emptyFlow()
)