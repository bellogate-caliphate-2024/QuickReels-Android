package com.bellogatecaliphate.timeline.ui.content.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.bellogatecaliphate.timeline.R
import com.bellogatecaliphate.timeline.ui.icons.CommentsIcon
import com.bellogatecaliphate.timeline.ui.icons.LikeIcon
import com.bellogatecaliphate.timeline.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.timeline.util.PLACEHOLDER_8DP
import com.bellogatecaliphate.timeline.util.PLACEHOLDER_ICON_SIZE

@Composable
internal fun BottomSection(
	contentId: String,
	caption: String,
	numberOfLikes: String,
	numberOfComments: String,
	isLiked: Boolean,
	onLikeButtonPressed: (contentId: String, isLiked: Boolean) -> Unit,
	onCommentButtonPressed: () -> Unit
) {
	Column(Modifier.padding(PLACEHOLDER_8DP)) {
		Row {
			LikeIcon(contentId, isLiked, numberOfLikes, onLikeButtonPressed)
			Spacer(modifier = Modifier.width(PLACEHOLDER_16DP))
			CommentsIcon(numberOfComments, onCommentButtonPressed)
			Spacer(modifier = Modifier.width(PLACEHOLDER_8DP))
			Image(
				modifier = Modifier.size(PLACEHOLDER_ICON_SIZE),
				painter = painterResource(id = R.drawable.icon_next),
				contentDescription = ""
			)
		}
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
		Text(text = caption)
	}
}