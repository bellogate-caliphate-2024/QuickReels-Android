package com.bellogatecaliphate.core.ui.content.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
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
	onCommentButtonPressed: (contentId: String, totalNumberOfCommentsExpected: Int) -> Unit,
	onShowMoreCaptionClicked: () -> Unit
) {
	Column(
		Modifier
			.background(Color.White)
			.padding(vertical = PLACEHOLDER_8DP, horizontal = PLACEHOLDER_16DP)
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
		CaptionText(
			text = caption,
			textStyle = MaterialTheme.typography.bodySmall,
			onShowMoreCaptionClicked = onShowMoreCaptionClicked
		)
	}
}

@Composable
private fun CaptionText(
	text: String,
	modifier: Modifier = Modifier,
	textStyle: TextStyle = LocalTextStyle.current,
	onShowMoreCaptionClicked: () -> Unit
) {
	var isOverflowing by remember { mutableStateOf(false) }
	
	Column(modifier = modifier) {
		Text(
			text = text,
			maxLines = 3,
			overflow = TextOverflow.Ellipsis,
			style = textStyle,
			onTextLayout = { layoutResult ->
				isOverflowing = layoutResult.hasVisualOverflow
			},
			modifier = Modifier.fillMaxWidth()
		)
		
		if (isOverflowing) {
			Text(
				text = stringResource(R.string.show_more),
				color = Color.Black,
				fontWeight = FontWeight.Bold,
				modifier = Modifier
					.padding(top = 4.dp)
					.clickable { onShowMoreCaptionClicked() }
			)
		}
	}
}