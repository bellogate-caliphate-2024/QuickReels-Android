package com.bellogatecaliphate.account.ui.screens.profile_detail

import android.graphics.Bitmap
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
	onClose: () -> Unit,
	onLogOut: () -> Unit,
) {
	val viewModel: ProfileDetailViewModel = hiltViewModel()
	val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
	
	LaunchedEffect(userEmailToSearchFor) { viewModel.getUserInfo(userEmailToSearchFor) }
	ProfileDetailScreen(
		uiState = uiState,
		onClose = onClose,
		onRetry = {
			viewModel.getUserInfo(userEmailToSearchFor)
		},
		onFollowClicked = {
			viewModel.followOrUnfollowUser(userEmailToSearchFor, true)
		},
		onUnfollowClicked = {
			viewModel.followOrUnfollowUser(userEmailToSearchFor, false)
		},
		onLogOut = onLogOut,
		onUploadNewProfilePicture = { newProfilePicture ->
			viewModel.changeProfilePicture(userEmailToSearchFor, newProfilePicture)
		},
		onSaveNewAccountName = { userEmail, newUserAccountName ->
			viewModel.saveNewAccountName(userEmail, newUserAccountName)
		}
	)
}

@Composable
private fun ProfileDetailScreen(
	uiState: UiState,
	onClose: () -> Unit,
	onRetry: () -> Unit,
	onFollowClicked: () -> Unit,
	onUnfollowClicked: () -> Unit,
	onLogOut: () -> Unit,
	onUploadNewProfilePicture: (newProfilePicture: Bitmap) -> Unit,
	onSaveNewAccountName: (userEmail: String, newUserAccountName: String) -> Unit,
) {
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
				isUpdatingFollowingStatus = uiState.isUpdatingFollowingStatus,
				isUpdatingUserAccountName = uiState.isUpdatingUserAccountName,
				isUploadingProfilePicture = uiState.isUploadingProfilePicture,
				profilePictureUploadSuccessful = uiState.successfullyUploadedProfilePicture,
				onSaveNewAccountName = onSaveNewAccountName,
				isFollowing = uiState.isFollowing ?: false,
				onFollowClicked = onFollowClicked,
				onLogOut = onLogOut,
				onUnfollowClicked = onUnfollowClicked,
				onUploadNewProfilePicture = onUploadNewProfilePicture,
			)
			RetryScreen(unableToGetUserInfo, onRetry)
		}
	}
}

