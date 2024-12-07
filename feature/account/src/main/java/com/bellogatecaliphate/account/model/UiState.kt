package com.bellogatecaliphate.account.model

import androidx.paging.PagingData
import com.bellogatecaliphate.core.model.dto.Content
import com.bellogatecaliphate.core.model.dto.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class UiState(
	val isLoading: Boolean = false,
	val user: User? = null,
	val userEmail: String = "",
	val listOfContentHistory: Flow<PagingData<Content>> = emptyFlow()
) {
	fun isUserLoggedIn() = user != null
}