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
import com.bellogatecaliphate.core.ui.comments.add_coment.AddCommentInput
import com.bellogatecaliphate.core.ui.comments.comments_list.CommentsList
import com.bellogatecaliphate.core.ui.comments.no_comment.NoComment
import com.bellogatecaliphate.core.ui.comments.util.getCommentsListHeaderText
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP

@Composable
internal fun Content(
	contentId: String,
	loggedInUserEmail: String?,
	noCommentsFound: Boolean,
	listOfComments: LazyPagingItems<Comment>?,
	totalNumberOfCommentsExpected: Int,
	isUploadingComment: Boolean = false,
	commentUploadedSuccessfully: Boolean? = false,
	commentDeletedSuccessfully: Boolean? = null,
	listOfDeletedComments: MutableList<String> = mutableListOf(),
	listOfCachedComments: List<Comment> = emptyList(),
	isLoadingReplies: Boolean = false,
	listOfReplies: List<Comment> = emptyList(),
	repliesPageNumber: Int? = null,
	canLoadMoreReplies: Boolean = false,
	onSaveReply: ((originalCommentId: String, reply: String) -> Unit)? = null,
	onLoadReplies: (originalCommentId: String, pageNumber: Int) -> Unit = { _, _ -> },
	onDeleteComment: (contentId: String, commentId: String) -> Unit = { _, _ -> },
	onAddComment: (contentId: String, parentCommentId: String?, comment: String) -> Unit = { _, _, _ -> },
) {
	
	Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
		val isLoadingFirstSetOfComments =
				listOfComments?.loadState?.source?.refresh is LoadState.Loading
		val isLoadingMoreComments =
				listOfComments?.loadState?.source?.append is LoadState.Loading
		val hasLoadedAllComments =
				listOfComments?.loadState?.source?.append is LoadState.NotLoading
		
		if (noCommentsFound) {
			NoComment()
			Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
			return
		}
		
		if (isLoadingFirstSetOfComments) {
			QuickReelsCircularProgressBar()
			Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
		}
		
		if (listOfComments != null && listOfComments.itemCount > 0) {
			Text(
				text = getCommentsListHeaderText(totalNumberOfCommentsExpected),
				style = MaterialTheme.typography.bodySmall
			)
			Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
			CommentsList(
				Modifier.weight(1f),
				contentId,
				listOfComments,
				loggedInUserEmail,
				isLoadingMoreComments,
				hasLoadedAllComments,
				commentUploadedSuccessfully,
				commentDeletedSuccessfully,
				listOfDeletedComments,
				listOfCachedComments,
				isLoadingReplies,
				listOfReplies,
				repliesPageNumber,
				canLoadMoreReplies,
				onSaveReply,
				onLoadReplies,
				onDeleteComment
			)
		}
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
		AddCommentInput(isUploadingComment, commentUploadedSuccessfully) { comment ->
			onAddComment(contentId, null, comment)
		}
	}
}