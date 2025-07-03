package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.bellogatecaliphate.core.model.dto.User
import com.bellogatecaliphate.core.util.PLACEHOLDER_200DP

@Composable
internal fun UserInfoScreen(
	user: User?,
	accountBelongsToLoggedInUser: Boolean,
	isFollowing: Boolean,
	onFollowClicked: () -> Unit,
	onUnfollowClicked: () -> Unit
) {
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
		FollowAndUnfollowButtonsSection(
			show = accountBelongsToLoggedInUser.not(),
			isFollowing = isFollowing,
			onFollowClicked = onFollowClicked,
			onUnfollowClicked = onUnfollowClicked
		)
	}
}

@Preview
@Composable
private fun PreviewUserSection() {
	val user = User(
		email = "jeff@gmail.com",
		accountName = "Jeff Emuveyan",
		profilePictureUrl = "",
		numberOfFollowers = "100",
		numberOfFollowing = "100",
		numberOfViews = "100",
		numberOfLikes = "100"
	)
	UserInfoScreen(
		user,
		accountBelongsToLoggedInUser = false,
		isFollowing = false,
		onFollowClicked = {},
		onUnfollowClicked = {}
	)
}