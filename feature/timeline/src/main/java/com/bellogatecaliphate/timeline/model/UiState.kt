package com.bellogatecaliphate.timeline.model

import com.bellogatecaliphate.core.model.dto.Advert
import com.bellogatecaliphate.core.model.dto.Comment

internal data class UiState(
	val isLoadingReplies: Boolean = false,
	val openCommentsBottomSheet: Boolean = false,
	val networkError: Boolean = false,
	val refresh: Boolean = false,
	val totalNumberOfComments: Int = 0,
	val listOfCommentReplies: List<Comment> = emptyList(),
	val repliesPageNumber: Int? = null,
	val canLoadMoreReplies: Boolean = false,
	val adVert: Advert? = null,
	val firstVisibleItemIndex: Int? = null,
	val firstVisibleItemScrollOffset: Int? = null,
	val loggedInUserEmail: String? = null,
	val commentDeletedSuccessfully: Boolean? = null,
	val deletedComments: MutableList<String> = mutableListOf(),
	val likedContents: MutableMap<String, Boolean?> = mutableMapOf(),
)