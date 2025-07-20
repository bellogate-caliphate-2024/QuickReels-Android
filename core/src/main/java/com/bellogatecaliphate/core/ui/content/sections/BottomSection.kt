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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.ui.Circle
import com.bellogatecaliphate.core.ui.composables.QuickReelsBottomSheetDialog
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
	contentBelongsToLoggedInUser: Boolean,
	isLiked: Boolean,
	onLikeButtonPressed: (contentId: String, isLiked: Boolean) -> Unit,
	onCommentButtonPressed: (contentId: String, totalNumberOfCommentsExpected: Int) -> Unit,
) {
	var showMoreCaption by remember { mutableStateOf(false) }
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
			showEditButton = contentBelongsToLoggedInUser,
			onShowMoreCaptionClicked = { showMoreCaption = true }
		)
		QuickReelsBottomSheetDialog(
			show = showMoreCaption,
			text = caption,
			onDismiss = { showMoreCaption = false }
		)
	}
}

@Composable
private fun CaptionText(
	text: String,
	showEditButton: Boolean,
	onShowMoreCaptionClicked: () -> Unit
) {
	var isOverflowing by remember { mutableStateOf(false) }
	
	Column {
		Text(
			text = text,
			maxLines = 3,
			overflow = TextOverflow.Ellipsis,
			style = MaterialTheme.typography.bodySmall,
			onTextLayout = { layoutResult ->
				isOverflowing = layoutResult.hasVisualOverflow
			},
			textAlign = TextAlign.Justify,
			modifier = Modifier.fillMaxWidth()
		)
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
		Row(verticalAlignment = Alignment.CenterVertically) {
			if (isOverflowing)
				Text(
					text = stringResource(R.string.show_more),
					color = colorResource(R.color.quickreels_purple),
					fontWeight = FontWeight.Bold,
					modifier = Modifier
						.clickable { onShowMoreCaptionClicked() }
				)
			Demarcation(isOverflowing && showEditButton)
			if (showEditButton)
				Text(
					text = stringResource(R.string.edit),
					color = colorResource(R.color.quickreels_purple),
					fontWeight = FontWeight.Bold,
					modifier = Modifier
						.padding(top = 4.dp)
						.clickable { onShowMoreCaptionClicked() }
				)
		}
	}
}

@Composable
private fun Demarcation(visible: Boolean) {
	if (visible.not()) return
	Spacer(modifier = Modifier.width(PLACEHOLDER_16DP))
	Circle(color = colorResource(id = R.color.ash))
	Spacer(modifier = Modifier.width(PLACEHOLDER_16DP))
}