package com.bellogatecaliphate.timeline.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.bellogatecaliphate.core.ui.ProgressBar
import com.bellogatecaliphate.timeline.model.UiState
import com.bellogatecaliphate.timeline.ui.content.Contents

@Composable
fun TimeLineScreen(viewModel: TimeLineScreenViewModel = hiltViewModel()) {
	val uiState = viewModel.uiState.collectAsStateWithLifecycle()
	TimeLineScreen(
		uiState.value, { contentId, isLiked ->
			viewModel.likeContent(contentId, isLiked)
		}, {
			viewModel.getComments()
		}
	)
}

@Composable
private fun TimeLineScreen(
	uiState: UiState,
	onLikeButtonPressed: (contentId: String, isLiked: Boolean) -> Unit,
	onCommentButtonPressed: () -> Unit
) {
	Column {
		ProgressBar(uiState.isLoading)
		Contents(
			uiState.listOfContents.collectAsLazyPagingItems(),
			onLikeButtonPressed,
			onCommentButtonPressed
		)
	}
}