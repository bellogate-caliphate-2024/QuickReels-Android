package com.bellogatecaliphate.core.ui.comments.dialog_content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.bellogatecaliphate.core.model.dto.Comment
import com.bellogatecaliphate.core.ui.comments.comments_list.CommentsList
import com.bellogatecaliphate.core.ui.comments.no_comment.NoComment
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun Content(
	isLoadingInitialComments: Boolean,
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
		when {
			isLoadingInitialComments     -> {
				CircularProgressIndicator()
			}
			
			noCommentsFound              -> {
				NoComment()
			}
			
			listOfComments.itemCount > 0 -> {
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
		}
		Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
	}
}