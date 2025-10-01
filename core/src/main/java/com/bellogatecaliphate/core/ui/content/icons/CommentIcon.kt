package com.bellogatecaliphate.core.ui.content.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.util.PLACEHOLDER_24DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP

@Composable
internal fun CommentsIcon(
	contentId: String,
	numberOfComments: String?,
	onCLick: (contentId: String, totalNumberOfCommentsExpected: Int) -> Unit,
	listOfContentsAndNewNumberOfComments: MutableMap<String, Int>
) {
	val hasCachedNumberOfComments = listOfContentsAndNewNumberOfComments[contentId] != null
	val latestNumberOfComments = if (hasCachedNumberOfComments) {
		listOfContentsAndNewNumberOfComments[contentId]?.toString()
	} else numberOfComments
	
	Column(
		Modifier.clickable { onCLick(contentId, latestNumberOfComments?.toIntOrNull() ?: 0) },
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Image(
			modifier = Modifier.size(PLACEHOLDER_24DP),
			painter = painterResource(id = R.drawable.icon_chat),
			contentDescription = ""
		)
		if (! latestNumberOfComments.isNullOrEmpty()) {
			Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
			Text(text = latestNumberOfComments, style = MaterialTheme.typography.bodySmall)
		}
	}
}