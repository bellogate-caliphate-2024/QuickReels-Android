package com.bellogatecaliphate.core.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.bellogatecaliphate.core.model.dto.Content
import com.bellogatecaliphate.core.ui.content.sections.UserDetailsSection
import com.bellogatecaliphate.core.ui.content.sections.VideoSection
import com.bellogatecaliphate.core.ui.content.sections.bottom_section.BottomSection
import com.bellogatecaliphate.core.util.ContentPreviewParameter

@Composable
@Preview(showBackground = true)
fun ContentListItem(
	@PreviewParameter(ContentPreviewParameter::class) content: Content,
	modifier: Modifier = Modifier,
	mapOfLikedAndUnlikedContents: MutableMap<String, Boolean?> = mutableMapOf(),
	contentBelongsToLoggedInUser: Boolean = true,
	deleteContentInProgress: Boolean = false,
	showBackButton: Boolean = false,
	onLikeButtonPressed: (contentId: String, isLiked: Boolean) -> Unit = { _, _ -> },
	onCommentButtonPressed: (contentId: String, totalNumberOfCommentsExpected: Int) -> Unit = { _, _ -> },
	onOpenAccountDetails: (accountUserEmail: String) -> Unit = { _ -> },
	onDeleteContent: () -> Unit = {},
	onEditCaptionClicked: () -> Unit = {},
	onDownloadClicked: () -> Unit = {},
	onClose: () -> Unit = {},
) {
	
	Column(modifier) {
		UserDetailsSection(
			contentBelongsToLoggedInUser = contentBelongsToLoggedInUser,
			userEmail = content.userId,
			userProfilePicture = content.userProfilePicture,
			userName = content.userName,
			showBackButton = showBackButton,
			date = content.date,
			onOpenAccountDetails = onOpenAccountDetails,
			deleteContentInProgress = deleteContentInProgress,
			onDeleteContent = onDeleteContent,
			onClose = onClose
		)
		VideoSection(Modifier.weight(1f), content.videoUrl, content.thumbnailUrl)
		BottomSection(
			contentId = content.id,
			caption = content.caption,
			numberOfLikes = content.numberOfLikes,
			mapOfLikedAndUnlikedContents = mapOfLikedAndUnlikedContents,
			numberOfComments = content.numberOfComments,
			contentBelongsToLoggedInUser = contentBelongsToLoggedInUser,
			redirectUrl = content.redirectUrl,
			isLiked = content.isLiked,
			onLikeButtonPressed = onLikeButtonPressed,
			onCommentButtonPressed = onCommentButtonPressed,
			onEditCaptionClicked = onEditCaptionClicked,
			onDownloadClicked = onDownloadClicked
		)
	}
}