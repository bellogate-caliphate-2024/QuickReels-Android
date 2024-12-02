package com.bellogatecaliphate.contents_history.model

import androidx.paging.PagingData
import com.bellogatecaliphate.core.model.dto.Content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class UiState(
	val isLoading: Boolean = false,
	val userEmail: String = "",
	val content: Flow<PagingData<Content>> = emptyFlow()
)