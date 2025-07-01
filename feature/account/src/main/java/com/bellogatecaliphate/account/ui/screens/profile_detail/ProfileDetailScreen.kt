package com.bellogatecaliphate.account.ui.screens.profile_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.core.model.dto.User
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_200DP

@Composable
internal fun ProfileDetailScreen(userEmail: String, onClose: () -> Unit) {
	val viewModel: ProfileDetailViewModel = hiltViewModel()
	val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
	
	LaunchedEffect(userEmail) { viewModel.getUserInfo(userEmail) }
	ProfileDetailScreen(uiState, onClose) {
		viewModel.getUserInfo(userEmail)
	}
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
			UserInfoScreen(uiState.user)
			RetryScreen(unableToGetUserInfo, onRetry)
		}
	}
}

@Composable
private fun UserInfoScreen(user: User?) {
	if (user == null) return
	Column(
		modifier = Modifier
			.background(color = Color.White)
			.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		AsyncImage(
			model = user.profilePictureUrl,
			contentDescription = "content description",
			modifier = Modifier
				.size(PLACEHOLDER_200DP)
				.clip(CircleShape)
		)
		CountSectionList(
			numberOfFollowers = user.numberOfFollowers,
			numberOfFollowing = user.numberOfFollowing,
			numberOfViews = user.numberOfViews
		)
	}
}

@Composable
private fun RetryScreen(unableToGetUserInfo: Boolean, onRetry: () -> Unit) {
	if (unableToGetUserInfo.not()) return
	Column(
		modifier = Modifier
			.background(color = Color.White)
			.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text(
			stringResource(R.string.unable_to_get_user_info),
			modifier = Modifier.clickable(onClick = onRetry),
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

@Composable
internal fun CountSectionList(
	numberOfFollowers: String,
	numberOfFollowing: String,
	numberOfViews: String
) {
	Row {
		CountSection(stringResource(R.string.followers), numberOfFollowers)
		CountSection(stringResource(R.string.following), numberOfFollowing)
		CountSection(stringResource(R.string.views), numberOfViews)
	}
}

@Composable
internal fun CountSection(title: String, count: String) {
	Column(
		modifier = Modifier.padding(PLACEHOLDER_16DP),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			fontWeight = FontWeight.Bold,
			text = title
		)
		Text(count)
	}
}

