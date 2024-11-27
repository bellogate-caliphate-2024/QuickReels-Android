package com.bellogatecaliphate.timeline.ui.timeline

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.bellogatecaliphate.core.common.ui.ProgressBar
import com.bellogatecaliphate.timeline.model.UiState
import com.bellogatecaliphate.timeline.ui.timeline.content.Contents

@Composable
fun TimeLineScreen(viewModel: TimeLineScreenViewModel = hiltViewModel()) {
	
	val contents = viewModel.getContents().collectAsLazyPagingItems()
	viewModel.setContents(contents)
	
	val uiState = viewModel.uiState.collectAsStateWithLifecycle()
	TimeLineScreen(uiState.value)
}

@Composable
private fun TimeLineScreen(uiState: UiState) {
	Column {
		ProgressBar(uiState.loading)
		Contents(uiState.listOfContents)
	}
}