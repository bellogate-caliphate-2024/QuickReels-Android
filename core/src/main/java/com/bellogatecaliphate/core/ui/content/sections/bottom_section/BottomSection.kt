package com.bellogatecaliphate.core.ui.content.sections.bottom_section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bellogatecaliphate.core.ui.composables.QuickReelsBottomSheetDialog
import com.bellogatecaliphate.core.ui.content.icons.CommentsIcon
import com.bellogatecaliphate.core.ui.content.icons.DownloadButton
import com.bellogatecaliphate.core.ui.content.icons.LikeIcon
import com.bellogatecaliphate.core.ui.content.icons.ShareIcon
import com.bellogatecaliphate.core.ui.content.sections.bottom_section.caption.CaptionText
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_24DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP

@Composable
internal fun BottomSection(
	contentId: String,
	caption: String,
	numberOfLikes: String,
	listOfLikedContents: MutableMap<String, Boolean?>,
	numberOfComments: String,
	contentBelongsToLoggedInUser: Boolean,
	redirectUrl: String,
	isLiked: Boolean,
	onLikeButtonPressed: (contentId: String, isLiked: Boolean) -> Unit,
	onCommentButtonPressed: (contentId: String, totalNumberOfCommentsExpected: Int) -> Unit,
	onEditCaptionClicked: () -> Unit = {},
	onDownloadClicked: () -> Unit = {}
) {
	var showMoreCaption by remember { mutableStateOf(false) }
	
	Column(
		Modifier
			.background(Color.White)
			.padding(vertical = PLACEHOLDER_8DP, horizontal = PLACEHOLDER_16DP)
	) {
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
		Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
			Row {
				LikeIcon(
					contentId = contentId,
					listOfLikedContentsFromLocal = listOfLikedContents,
					likedFromRemote = isLiked,
					existingNumberOfLikes = numberOfLikes,
					onCLick = onLikeButtonPressed
				)
				Spacer(modifier = Modifier.width(PLACEHOLDER_24DP))
				CommentsIcon(contentId, numberOfComments, onCommentButtonPressed)
				Spacer(modifier = Modifier.width(PLACEHOLDER_24DP))
				ShareIcon(redirectUrl)
			}
			DownloadButton(onDownloadClicked)
		}
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
		CaptionText(
			text = caption,
			showEditButton = contentBelongsToLoggedInUser,
			onShowMoreCaptionClicked = { showMoreCaption = true },
			onEditCaptionClicked = onEditCaptionClicked
		)
		QuickReelsBottomSheetDialog(
			show = showMoreCaption,
			text = caption,
			onDismiss = { showMoreCaption = false }
		)
	}
}