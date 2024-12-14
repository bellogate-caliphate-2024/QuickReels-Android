package com.bellogatecaliphate.core.ui.comments.comments_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.ui.comments.model.Comment
import com.bellogatecaliphate.core.ui.comments.util.CommentAndReplies
import com.bellogatecaliphate.core.ui.comments.util.CommentAndRepliesPreviewParameter
import com.bellogatecaliphate.core.util.PLACEHOLDER_24DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP

@Composable
internal fun RepliesList(
	replies: List<Comment>,
	repliesPageNumber: Int? = null,
	isLoadingReplies: Boolean = false,
	canLoadMoreReplies: Boolean = false,
	onLoadMoreReplies: (pageToLoad: Int) -> Unit = {}
) {
	
	Column(
		Modifier.padding(horizontal = PLACEHOLDER_24DP),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		replies.forEach { CommentItem(comment = it) }
		if (canLoadMoreReplies) {
			Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
			if (isLoadingReplies) {
				CircularProgressIndicator()
			} else {
				Text(
					modifier = Modifier.clickable {
						if (repliesPageNumber != null) {
							onLoadMoreReplies(repliesPageNumber + 1)
						}
					},
					text = stringResource(id = R.string.load_more)
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun PreviewRepliesList(
	@PreviewParameter(CommentAndRepliesPreviewParameter::class) commentAndReplies: CommentAndReplies,
) {
	RepliesList(commentAndReplies.replies)
}
