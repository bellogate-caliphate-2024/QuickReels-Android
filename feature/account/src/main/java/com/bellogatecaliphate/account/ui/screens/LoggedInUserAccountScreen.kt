package com.bellogatecaliphate.account.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.paging.compose.LazyPagingItems
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.account.model.UiState
import com.bellogatecaliphate.account.ui.composables.ContentHistoryGridList
import com.bellogatecaliphate.account.ui.composables.UserDetailsSection
import com.bellogatecaliphate.core.model.dto.Content
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.util.PLACEHOLDER_2DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP

@Composable
internal fun LoggedInUserAccountScreen(
	uiState: UiState,
	showBackButton: Boolean = false,
	listOfContentHistory: LazyPagingItems<Content>,
	onOpenProfileDetails: (userEmail: String) -> Unit,
	onBackPressed: () -> Unit,
	onOpenContent: (content: Content) -> Unit
) {
	Column(horizontalAlignment = Alignment.CenterHorizontally) {
		LoadingScreen(uiState.isLoading)
		val user = uiState.user ?: return
		UserDetailsSection(
			user = user,
			showBackButton = showBackButton,
			onOpenProfileDetails = onOpenProfileDetails,
			onBackPressed = onBackPressed
		)
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
private fun LoadingScreen(show: Boolean) {
	if (show.not()) return
	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		QuickReelsCircularProgressBar(show = show)
	}
}
