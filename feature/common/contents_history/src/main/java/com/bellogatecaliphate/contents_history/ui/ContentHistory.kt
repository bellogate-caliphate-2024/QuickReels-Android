package com.bellogatecaliphate.contents_history.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.bellogatecaliphate.contents_history.model.UiState

@Composable
fun ContentsHistory(viewModel: ContentHistoryViewModel = hiltViewModel()) {
	
	val state = viewModel.uiState.collectAsStateWithLifecycle().value
	ContentsHistory(state)
}

@Composable
fun ContentsHistory(uiState: UiState) {
	val contentList = uiState.content.collectAsLazyPagingItems()
	
}