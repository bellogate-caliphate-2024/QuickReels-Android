package com.bellogatecaliphate.core.ui.comments.dialog_content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.bellogatecaliphate.core.model.dto.Comment
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.ui.comments.comments_list.CommentsList
import com.bellogatecaliphate.core.ui.comments.no_comment.NoComment
import com.bellogatecaliphate.core.ui.comments.util.getCommentsListHeaderText
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun Content(
	loggedInUserEmail: String?,
	noCommentsFound: Boolean,
	listOfComments: LazyPagingItems<Comment>,
	totalNumberOfCommentsExpected: Int,
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
	Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
		val isLoadingFirstSetOfComments =
				listOfComments.loadState.source.refresh is LoadState.Loading
		val isLoadingMoreComments =
				listOfComments.loadState.source.append is LoadState.Loading
		val hasLoadedAllComments =
				listOfComments.loadState.source.append is LoadState.NotLoading
		
		if (noCommentsFound) {
			NoComment()
			Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
			return
		}
		
		if (isLoadingFirstSetOfComments) {
			QuickReelsCircularProgressBar()
			Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
		}
		
		if (listOfComments.itemCount > 0) {
			Text(
				text = getCommentsListHeaderText(totalNumberOfCommentsExpected),
				style = MaterialTheme.typography.bodySmall
			)
			Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
			CommentsList(
				listOfComments,
				loggedInUserEmail,
				isLoadingMoreComments,
				hasLoadedAllComments,
				commentDeletedSuccessfully,
				listOfDeletedComments,
				isLoadingReplies,
				listOfReplies,
				repliesPageNumber,
				canLoadMoreReplies,
				onSaveReply,
				onLoadReplies,
				onDeleteComment
			)
		}
	}
}