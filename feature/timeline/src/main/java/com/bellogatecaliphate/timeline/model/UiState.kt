package com.bellogatecaliphate.timeline.model

import androidx.paging.compose.LazyPagingItems
import com.bellogatecaliphate.core.model.dto.Content

internal data class UiState(
	val loading: Boolean = false,
	val networkError: Boolean = false,
	val refresh: Boolean = false,
	val listOfContents: LazyPagingItems<Content>? = null
)