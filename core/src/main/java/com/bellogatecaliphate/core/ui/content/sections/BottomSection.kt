package com.bellogatecaliphate.core.ui.content.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.ui.content.icons.CommentsIcon
import com.bellogatecaliphate.core.ui.content.icons.LikeIcon
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_32DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP

@Composable
internal fun BottomSection(
	contentId: String,
	caption: String,
	numberOfLikes: String,
	numberOfComments: String,
	isLiked: Boolean,
	onLikeButtonPressed: (contentId: String, isLiked: Boolean) -> Unit,
	onCommentButtonPressed: (contentId: String, totalNumberOfCommentsExpected: Int) -> Unit
) {
	Column(
		Modifier
			.background(Color.White)
			.padding(PLACEHOLDER_8DP)
	) {
		Row {
			LikeIcon(contentId, isLiked, numberOfLikes, onLikeButtonPressed)
			Spacer(modifier = Modifier.width(PLACEHOLDER_16DP))
			CommentsIcon(contentId, numberOfComments, onCommentButtonPressed)
			Spacer(modifier = Modifier.width(PLACEHOLDER_8DP))
			Image(
				modifier = Modifier.size(PLACEHOLDER_32DP),
				painter = painterResource(id = R.drawable.icon_next),
				contentDescription = ""
			)
		}
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
		Text(
			text = caption,
			style = MaterialTheme.typography.bodySmall
		)
	}
}