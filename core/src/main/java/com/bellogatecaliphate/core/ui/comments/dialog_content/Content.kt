package com.bellogatecaliphate.core.ui.comments.dialog_content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.bellogatecaliphate.core.model.dto.Comment
import com.bellogatecaliphate.core.ui.comments.comments_list.CommentsList
import com.bellogatecaliphate.core.ui.comments.no_comment.NoComment
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP

@Composable
internal fun Content(
	noCommentsFound: Boolean,
	listOfComments: LazyPagingItems<Comment>,
	isLoadingReplies: Boolean = false,
	listOfReplies: List<Comment> = emptyList(),
	repliesPageNumber: Int? = null,
	canLoadMoreReplies: Boolean = false,
	onSaveReply: ((originalCommentId: String, reply: String) -> Unit)? = null,
	onLoadReplies: (originalCommentId: String, pageNumber: Int) -> Unit = { _, _ -> },
) {
	Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
		val isLoadingFirstSetOfComments =
				listOfComments.loadState.source.refresh is LoadState.Loading
		val isLoadingMoreComments =
				listOfComments.loadState.source.append is LoadState.Loading
		
		if (isLoadingFirstSetOfComments) {
			CircularProgressIndicator()
		}
		
		if (noCommentsFound) {
			NoComment()
		}
		
		if (listOfComments.itemCount > 0) {
			CommentsList(
				listOfComments,
				isLoadingReplies,
				listOfReplies,
				repliesPageNumber,
				canLoadMoreReplies,
				onSaveReply,
				onLoadReplies
			)
		}
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
		if (isLoadingMoreComments) {
			CircularProgressIndicator()
		}
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
	}
}