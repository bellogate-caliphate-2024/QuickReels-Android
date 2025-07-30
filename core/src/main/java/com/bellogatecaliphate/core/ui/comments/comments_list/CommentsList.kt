package com.bellogatecaliphate.core.ui.comments.comments_list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.model.dto.Comment
import com.bellogatecaliphate.core.ui.Circle
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.ui.comments.util.previewComments
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun CommentsList(
	listOfComments: LazyPagingItems<Comment>,
	loggedInUserEmail: String? = null,
	isLoadingMoreComments: Boolean = false,
	hasLoadedAllComments: Boolean = false,
	commentDeletedSuccessfully: Boolean? = null,
	listOfDeletedComments: MutableList<String> = mutableListOf(),
	isLoadingReplies: Boolean = false,
	listOfReplies: List<Comment> = emptyList(),
	repliesPageNumber: Int? = null,
	canLoadMoreReplies: Boolean = false,
	onSaveReply: ((originalCommentId: String, reply: String) -> Unit)? = null,
	onLoadReplies: (originalCommentId: String, pageNumber: Int) -> Unit = { _, _ -> },
	onDeleteComment: (commentId: String) -> Unit = { _ -> }
) {
	val listState = rememberLazyListState()
	
	LazyColumn(
		Modifier
			.padding(horizontal = PLACEHOLDER_16DP)
			.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		state = listState,
	) {
		items(listOfComments.itemCount) { index ->
			val comment = listOfComments[index]
			comment?.let {
				CommentItem(
					comment = it,
					visible = listOfDeletedComments.contains(it.commentId).not(),
					loggedInUserEmail = loggedInUserEmail,
					commentDeletedSuccessfully = commentDeletedSuccessfully,
					isLoadingReplies = isLoadingReplies,
					listOfReplies = listOfReplies,
					repliesPageNumber = repliesPageNumber,
					canLoadMoreReplies = canLoadMoreReplies,
					onSaveReply = onSaveReply,
					onLoadReplies = onLoadReplies,
					onDeleteComment = onDeleteComment
				)
			}
		}
		item {
			Footer(isLoadingMoreComments, hasLoadedAllComments)
		}
	}
}

@Composable
private fun Footer(
	isLoadingMoreComments: Boolean,
	hasLoadedAllComments: Boolean,
) {
	Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
	if (isLoadingMoreComments) {
		QuickReelsCircularProgressBar()
	}
	if (hasLoadedAllComments) {
		Circle(color = colorResource(id = R.color.ash))
	}
	Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
}

@Preview(showBackground = true)
@Composable
private fun PreviewCommentsList() {
	CommentsList(listOfComments = flowOf(PagingData.from(previewComments)).collectAsLazyPagingItems())
}
