package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.bellogatecaliphate.core.model.dto.User

@Composable
internal fun UserInfoScreen(
	user: User?,
	accountBelongsToLoggedInUser: Boolean,
	isUpdatingFollowingStatus: Boolean,
	isUpdatingUserAccountName: Boolean,
	isUploadingProfilePicture: Boolean,
	profilePictureUploadSuccessful: Boolean?,
	isFollowing: Boolean,
	onFollowClicked: () -> Unit,
	onUnfollowClicked: () -> Unit,
	onLogOut: () -> Unit,
	onUploadNewProfilePicture: (newProfilePicture: Bitmap) -> Unit,
	onSaveNewAccountName: (userEmail: String, newUserAccountName: String) -> Unit,
) {
	if (user == null) return
	Column(
		modifier = Modifier
			.background(color = Color.White)
			.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		ProfilePicture(
			isUploadingProfilePicture = isUploadingProfilePicture,
			accountBelongsToLoggedInUser = accountBelongsToLoggedInUser,
			profilePictureUrl = user.profilePictureUrl,
			profilePictureUploadSuccessful = profilePictureUploadSuccessful,
			onUploadNewProfilePicture = onUploadNewProfilePicture
		)
		CountSectionList(
			numberOfFollowers = user.numberOfFollowers,
			numberOfFollowing = user.numberOfFollowing,
			numberOfViews = user.numberOfViews
		)
		FollowAndUnfollowButtonsSection(
			show = accountBelongsToLoggedInUser.not(),
			isUpdatingFollowingStatus = isUpdatingFollowingStatus,
			isFollowing = isFollowing,
			onFollowClicked = onFollowClicked,
			onUnfollowClicked = onUnfollowClicked
		)
		UserAccountInformationSection(
			show = accountBelongsToLoggedInUser,
			isUpdatingUserAccountName = isUpdatingUserAccountName,
			user = user,
			onSaveNewAccountName = onSaveNewAccountName,
			onLogOut = onLogOut
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
		isUpdatingFollowingStatus = false,
		isUpdatingUserAccountName = false,
		isUploadingProfilePicture = false,
		onSaveNewAccountName = { _, _ -> },
		isFollowing = false,
		onFollowClicked = {},
		onUnfollowClicked = {},
		onLogOut = {},
		onUploadNewProfilePicture = {},
		profilePictureUploadSuccessful = null
	)
}