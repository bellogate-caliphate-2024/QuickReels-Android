package com.bellogatecaliphate.core.ui.comments.comments_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bellogatecaliphate.core.model.dto.Comment
import com.bellogatecaliphate.core.ui.comments.util.getCommentsListHeaderText
import com.bellogatecaliphate.core.ui.comments.util.previewComments
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun CommentsList(
	listOfComments: LazyPagingItems<Comment>,
	isLoadingReplies: Boolean = false,
	listOfReplies: List<Comment> = emptyList(),
	repliesPageNumber: Int? = null,
	canLoadMoreReplies: Boolean = false,
	onSaveReply: ((originalCommentId: String, reply: String) -> Unit)? = null,
	onLoadReplies: (originalCommentId: String, pageNumber: Int) -> Unit = { _, _ -> },
) {
	val listState = rememberLazyListState()
	
	Column(
		Modifier.padding(horizontal = PLACEHOLDER_16DP),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = getCommentsListHeaderText(listOfComments.itemCount),
			style = MaterialTheme.typography.bodySmall
		)
		Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
		LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
			items(listOfComments.itemCount) { index ->
				val comment = listOfComments[index]
				comment?.let {
					CommentItem(
						comment = it,
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
}

@Preview(showBackground = true)
@Composable
private fun PreviewCommentsList() {
	CommentsList(listOfComments = flowOf(PagingData.from(previewComments)).collectAsLazyPagingItems())
}
