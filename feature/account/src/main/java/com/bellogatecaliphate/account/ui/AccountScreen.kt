package com.bellogatecaliphate.account.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.account.model.UiState
import com.bellogatecaliphate.account.ui.content_history_grid_list.ContentHistoryGridList
import com.bellogatecaliphate.account.ui.icons.Likes
import com.bellogatecaliphate.account.ui.icons.Views
import com.bellogatecaliphate.account.ui.user_details_section.UserDetailsSection
import com.bellogatecaliphate.core.model.dto.Content
import com.bellogatecaliphate.core.model.dto.User
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP
import com.spr.jetpack_loading.components.indicators.BallScaleMultipleIndicator

@Composable
internal fun AccountScreen(
	serverClientId: String,
	viewModel: AccountScreenViewModel = hiltViewModel(),
) {
	val state = viewModel.uiState.collectAsStateWithLifecycle().value
	val context = LocalContext.current
	val scope = rememberCoroutineScope()
	AccountScreen(uiState = state, onLogin = {
		viewModel.performLogin {
			viewModel.firebaseAuthentication.performLogin(context, serverClientId)
		}
	})
}

@Composable
private fun AccountScreen(uiState: UiState, onLogin: () -> Unit = {}) {
	if (uiState.isUserLoggedIn) {
		uiState.user?.let {
			LoggedInUserAccountScreen(
				user = it,
				listOfContentHistory = uiState.listOfContentHistory.collectAsLazyPagingItems()
			)
		}
	} else {
		AnonymousUserAccountScreen(uiState, onLogin)
	}
}

@Composable
private fun AnonymousUserAccountScreen(uiState: UiState, onLogin: () -> Unit) {
	Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
		if (uiState.isLoading) {
			BallScaleMultipleIndicator(
				largestBallDiameter = 40f,
				rippleCount = 4,
				animationDuration = 1_000,
				color = colorResource(id = R.color.light_purple),
			)
		} else {
			Button(onClick = onLogin) { Text(text = stringResource(id = R.string.login)) }
		}
	}
}

@Composable
private fun LoggedInUserAccountScreen(user: User, listOfContentHistory: LazyPagingItems<Content>) {
	Column(horizontalAlignment = Alignment.CenterHorizontally) {
		Spacer(
			modifier = Modifier
				.background(colorResource(id = R.color.light_ash))
				.height(PLACEHOLDER_8DP)
				.fillMaxWidth()
		)
		UserDetailsSection(
			userProfilePicture = user.profilePictureUrl,
			userName = user.accountName,
			userEmail = user.email
		)
		Spacer(
			modifier = Modifier
				.background(colorResource(id = R.color.light_ash))
				.height(PLACEHOLDER_8DP)
				.fillMaxWidth()
		)
		Row(
			modifier = Modifier.padding(top = PLACEHOLDER_16DP),
			horizontalArrangement = Arrangement.SpaceEvenly
		) {
			Likes(user.numberOfLikes)
			Spacer(modifier = Modifier.width(PLACEHOLDER_16DP))
			Views(user.numberOfViews)
		}
		ContentHistoryGridList(listOfContentHistory)
	}
}