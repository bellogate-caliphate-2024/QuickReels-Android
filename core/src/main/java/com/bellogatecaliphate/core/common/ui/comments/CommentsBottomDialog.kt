package com.bellogatecaliphate.core.common.ui.comments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bellogatecaliphate.core.common.ui.comments.comments_list.CommentsList
import com.bellogatecaliphate.core.common.ui.comments.model.Comment
import com.bellogatecaliphate.core.common.ui.comments.no_comment.NoComment
import com.bellogatecaliphate.core.common.util.PLACEHOLDER_16DP

/**
 * @param visible is true if the CommentsBottomDialog should be visible.
 * @param isLoadingInitialComments is true when the app is loading comments for the very first time
 * after the user clicked on the button to open comments.
 * @param noCommentsFound is true if there is no comments for this post.
 * @param listOfComments is the paginated list of comments.
 * @param isLoadingReplies is true when the ap is loading the list of replies to a comment.
 * @param listOfReplies is a non paginated list of replies to a comment.
 * @param repliesPageNumber is the current page number of replies that has been loaded.
 * @param canLoadMoreReplies is true if the comment has more replies to be loaded.
 * @param onSaveReply is called when the user writes a reply to a comment and presses the save button.
 * @param onLoadReplies is called when the user clicks on the button to load replies on a comment.
 *
 * ***/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CommentsBottomDialog(
	visible: Boolean = false,
	isLoadingInitialComments: Boolean = false,
	noCommentsFound: Boolean = false,
	listOfComments: List<Comment>,
	isLoadingReplies: Boolean = false,
	listOfReplies: List<Comment> = emptyList(),
	repliesPageNumber: Int? = null,
	canLoadMoreReplies: Boolean = false,
	onSaveReply: ((originalCommentId: String, reply: String) -> Unit)? = null,
	onLoadReplies: (pageNumber: Int) -> Unit = {},
) {
	if (visible.not()) return
	val sheetState = rememberModalBottomSheetState()
	var showBottomSheet by remember { mutableStateOf(false) }
	
	ModalBottomSheet(
		onDismissRequest = { showBottomSheet = false },
		sheetState = sheetState
	) {
		Content(
			isLoadingInitialComments,
			noCommentsFound,
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

@Composable
private fun Content(
	isLoadingInitialComments: Boolean,
	noCommentsFound: Boolean,
	listOfComments: List<Comment>,
	isLoadingReplies: Boolean = false,
	listOfReplies: List<Comment> = emptyList(),
	repliesPageNumber: Int? = null,
	canLoadMoreReplies: Boolean = false,
	onSaveReply: ((originalCommentId: String, reply: String) -> Unit)? = null,
	onLoadReplies: (pageNumber: Int) -> Unit = {},
) {
	Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
		when {
			isLoadingInitialComments    -> {
				CircularProgressIndicator()
			}
			
			noCommentsFound             -> {
				NoComment()
			}
			
			listOfComments.isNotEmpty() -> {
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