package com.bellogatecaliphate.chat.model

import androidx.paging.compose.LazyPagingItems

internal data class UiState(
	val isLoading: Boolean = false,
	val networkError: Boolean = false,
	val refresh: Boolean = false,
	val listOfUsers: LazyPagingItems<ChatUser>? = null
)