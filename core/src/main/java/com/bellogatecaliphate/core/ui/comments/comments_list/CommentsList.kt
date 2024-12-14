package com.bellogatecaliphate.core.ui.comments.comments_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.bellogatecaliphate.core.ui.comments.model.Comment
import com.bellogatecaliphate.core.ui.comments.util.CommentsListPreviewParameter
import com.bellogatecaliphate.core.ui.comments.util.getCommentsListHeaderText
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun CommentsList(
	listOfComments: List<Comment>,
	isLoadingReplies: Boolean = false,
	listOfReplies: List<Comment> = emptyList(),
	repliesPageNumber: Int? = null,
	canLoadMoreReplies: Boolean = false,
	onSaveReply: ((originalCommentId: String, reply: String) -> Unit)? = null,
	onLoadReplies: (pageNumber: Int) -> Unit = {},
) {
	val listState = rememberLazyListState()
	
	Column(
		Modifier.padding(horizontal = PLACEHOLDER_16DP),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(text = getCommentsListHeaderText(listOfComments.size))
		Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
		LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
			items(listOfComments.size) { index ->
				CommentItem(
					comment = listOfComments[index],
					isLoadingReplies,
					listOfReplies,
					repliesPageNumber,
					canLoadMoreReplies,
					onSaveReply,
					onLoadReplies
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun PreviewCommentsList(@PreviewParameter(CommentsListPreviewParameter::class) list: List<Comment>) {
	CommentsList(listOfComments = list)
}
