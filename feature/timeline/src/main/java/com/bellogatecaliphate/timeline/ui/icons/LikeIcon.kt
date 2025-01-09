package com.bellogatecaliphate.timeline.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.bellogatecaliphate.timeline.R
import com.bellogatecaliphate.timeline.util.PLACEHOLDER_8DP
import com.bellogatecaliphate.timeline.util.PLACEHOLDER_ICON_SIZE

@Composable
internal fun LikeIcon(
	contentId: String,
	isAlreadyLiked: Boolean,
	numberOfLikes: String?,
	onCLick: (contentId: String, isLiked: Boolean) -> Unit
) {
	var isLiked by remember { mutableStateOf(isAlreadyLiked) }
	val icon = if (isLiked) R.drawable.icon_heart else R.drawable.icon_heart
	
	Column(
		modifier = Modifier.clickable {
			isLiked = ! isLiked
			onCLick(contentId, isLiked)
		},
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Image(
			modifier = Modifier.size(PLACEHOLDER_ICON_SIZE),
			painter = painterResource(id = icon),
			contentDescription = ""
		)
		if (! numberOfLikes.isNullOrEmpty()) {
			Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
			Text(text = numberOfLikes)
		}
	}
}