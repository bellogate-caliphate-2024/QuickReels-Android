package com.bellogatecaliphate.chat.model

import androidx.paging.PagingData
import com.bellogatecaliphate.core.model.dto.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

internal data class UiState(
	val isLoading: Boolean = false,
	val networkError: Boolean = false,
	val refresh: Boolean = false,
	val listOfUsers: Flow<PagingData<User>> = emptyFlow()
)