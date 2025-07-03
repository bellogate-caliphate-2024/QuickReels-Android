package com.bellogatecaliphate.account.ui.screens.profile_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.account.ui.screens.profile_detail.composables.LoadingScreen
import com.bellogatecaliphate.account.ui.screens.profile_detail.composables.RetryScreen
import com.bellogatecaliphate.account.ui.screens.profile_detail.composables.UserInfoScreen
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun ProfileDetailScreen(
	userEmailToSearchFor: String,
	onClose: () -> Unit
) {
	val viewModel: ProfileDetailViewModel = hiltViewModel()
	val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
	
	LaunchedEffect(userEmailToSearchFor) { viewModel.getUserInfo(userEmailToSearchFor) }
	ProfileDetailScreen(uiState, onClose, onRetry = {
		viewModel.getUserInfo(userEmailToSearchFor)
	})
}

@Composable
private fun ProfileDetailScreen(uiState: UiState, onClose: () -> Unit, onRetry: () -> Unit) {
	val unableToGetUserInfo = uiState.unableToGetUser
	
	Scaffold(
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
		) {
			LoadingScreen(uiState.isLoading)
			UserInfoScreen(
				user = uiState.user,
				accountBelongsToLoggedInUser = uiState.accountBelongsToLoggedInUser,
				isFollowing = uiState.isFollowing ?: false,
				onFollowClicked = {},
				onUnfollowClicked = {}
			)
			RetryScreen(unableToGetUserInfo, onRetry)
		}
	}
}

