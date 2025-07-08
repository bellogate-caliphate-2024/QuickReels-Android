package com.bellogatecaliphate.account.ui.screens.view_and_edit_content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun EditContentScreen(
	contentId: String,
	onClose: () -> Unit
) {
	val viewModel: ViewAndEditContentScreenViewModel = hiltViewModel()
	val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
	
	LaunchedEffect(contentId) { viewModel.getContent(contentId = contentId) }
	EditContentScreen(
		uiState = uiState,
		onClose = onClose,
		onRefresh = { viewModel.getContent(isRefreshing = true, contentId = contentId) }
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditContentScreen(
	uiState: UiState,
	onClose: () -> Unit,
	onRefresh: () -> Unit
) {
	
	PullToRefreshBox(
		isRefreshing = uiState.isRefreshing,
		onRefresh = onRefresh,
		modifier = Modifier.fillMaxSize()
	) {
		Scaffold(
			modifier = Modifier.fillMaxSize(),
			topBar = {
				Image(
					painterResource(R.drawable.back_arrow),
					contentDescription = "",
					modifier = Modifier
						.clickable(onClick = onClose)
						.padding(PLACEHOLDER_16DP),
				)
			}
		) { innerPadding ->
			Column(
				modifier = Modifier
					.background(color = Color.White)
					.padding(innerPadding)
					.fillMaxSize(),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
			) {
				when {
					uiState.isLoadingInitialContent -> QuickReelsCircularProgressBar()
					uiState.errorLoadingContent     -> Text(stringResource(R.string.error_loading_content))
					uiState.errorSavingContent      -> {}
					uiState.errorDeletingContent    -> {}
					uiState.content != null         -> {}
				}
			}
		}
	}
}