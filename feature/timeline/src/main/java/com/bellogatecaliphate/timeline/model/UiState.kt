package com.bellogatecaliphate.timeline.model

import androidx.paging.PagingData
import com.bellogatecaliphate.core.model.dto.Comment
import com.bellogatecaliphate.core.model.dto.Content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

internal data class UiState(
	val isLoading: Boolean = false,
	val isLoadingReplies: Boolean = false,
	val openCommentsBottomSheet: Boolean = false,
	val networkError: Boolean = false,
	val refresh: Boolean = false,
	val totalNumberOfComments: Int = 0,
	val listOfPaginatedContents: Flow<PagingData<Content>> = emptyFlow(),
	val listOfPaginatedComments: Flow<PagingData<Comment>> = emptyFlow(),
	val listOfCommentReplies: List<Comment> = emptyList(),
	val repliesPageNumber: Int? = null,
	val canLoadMoreReplies: Boolean = false
)