package com.bellogatecaliphate.account.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.account.model.UiState
import com.bellogatecaliphate.account.ui.composables.ContentHistoryGridList
import com.bellogatecaliphate.account.ui.composables.UserDetailsSection
import com.bellogatecaliphate.core.model.dto.Content
import com.bellogatecaliphate.core.model.dto.User
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.util.PLACEHOLDER_2DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP

@Composable
internal fun LoggedInUserAccountScreen(
	uiState: UiState,
	showBackButton: Boolean = false,
	onOpenProfileDetails: (userEmail: String) -> Unit,
	onBackPressed: () -> Unit,
	onOpenContent: (content: Content) -> Unit
) {
	val listOfContentHistory = uiState.listOfContentHistory.collectAsLazyPagingItems()
	val isLoadingInitialListItems = listOfContentHistory.loadState.refresh is LoadState.Loading
	val isLoadingMorePaginatedItems = listOfContentHistory.loadState.append is LoadState.Loading
	val errorLoadingInitialListItems = listOfContentHistory.loadState.refresh is LoadState.Error
	val userHasNoContent = listOfContentHistory.loadState.refresh is LoadState.NotLoading &&
	                       listOfContentHistory.itemCount == 0
	
	Column(
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		when {
			isLoadingInitialListItems    -> LoadingScreen()
			errorLoadingInitialListItems -> ErrorLoadingInitialListItems()
			else                         -> LoggedInUserAccountScreen(
				user = uiState.user,
				listOfContentHistory = listOfContentHistory,
				userHasNoContent = userHasNoContent,
				showBackButton = showBackButton,
				onOpenProfileDetails = onOpenProfileDetails,
				onBackPressed = onBackPressed,
				onOpenContent = onOpenContent
			)
		}
	}
}

@Composable
private fun LoggedInUserAccountScreen(
	user: User?,
	listOfContentHistory: LazyPagingItems<Content>,
	userHasNoContent: Boolean,
	showBackButton: Boolean = false,
	onOpenProfileDetails: (userEmail: String) -> Unit,
	onBackPressed: () -> Unit,
	onOpenContent: (content: Content) -> Unit
) {
	Column {
		if (user == null) return
		UserDetailsSection(
			user = user,
			showBackButton = showBackButton,
			onOpenProfileDetails = onOpenProfileDetails,
			onBackPressed = onBackPressed
		)
		NoContentToShow(Modifier.weight(1f), userHasNoContent)
		Grid(
			userHasNoContent = userHasNoContent,
			listOfContentHistory = listOfContentHistory,
			onOpenContent = onOpenContent
		)
	}
}

@Composable
private fun Grid(
	userHasNoContent: Boolean,
	listOfContentHistory: LazyPagingItems<Content>,
	onOpenContent: (content: Content) -> Unit
) {
	if (userHasNoContent.not()) {
		Spacer(
			modifier = Modifier
				.padding(horizontal = PLACEHOLDER_8DP)
				.background(colorResource(id = R.color.light_ash))
				.height(PLACEHOLDER_2DP)
				.fillMaxWidth()
		)
		ContentHistoryGridList(
			list = listOfContentHistory,
			onOpenContent = onOpenContent
		)
	}
}

@Composable
private fun ErrorLoadingInitialListItems() {
	Text(text = stringResource(R.string.error_loading_list_try_again))
}

@Composable
private fun NoContentToShow(modifier: Modifier, visible: Boolean) {
	if (! visible) return
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier.fillMaxSize()
	) {
		Text(text = stringResource(R.string.your_uploads_will_appear_here))
	}
}

@Composable
private fun LoadingScreen() {
	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		QuickReelsCircularProgressBar()
	}
}
