package com.bellogatecaliphate.core.ui.comments

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.bellogatecaliphate.core.model.dto.Comment
import com.bellogatecaliphate.core.ui.comments.dialog_content.Content

/**
 * @param visible is true if the CommentsBottomDialog should be visible.
 * after the user clicked on the button to open comments.
 * @param totalNumberOfCommentsExpected is the total number of comments that the content has.
 * @param listOfComments is the paginated list of comments.
 * @param isLoadingReplies is true when the ap is loading the list of replies to a comment.
 * @param listOfReplies is a non paginated list of replies to a comment.
 * @param repliesPageNumber is the current page number of replies that has been loaded.
 * @param canLoadMoreReplies is true if the comment has more replies to be loaded.
 * @param onCommentsBottomDialogClosed is called when the user closes the CommentsBottomDialog.
 * @param onSaveReply is called when the user writes a reply to a comment and presses the save button.
 * @param onLoadReplies is called when the user clicks on the button to load replies on a comment.
 * @param footer this is the composable that should be displayed at the bottom of the comments.
 *
 * ***/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsBottomDialog(
	visible: Boolean = false,
	totalNumberOfCommentsExpected: Int,
	listOfComments: LazyPagingItems<Comment>,
	isLoadingReplies: Boolean = false,
	listOfReplies: List<Comment> = emptyList(),
	repliesPageNumber: Int? = null,
	canLoadMoreReplies: Boolean = false,
	onCommentsBottomDialogClosed: () -> Unit = {},
	onSaveReply: ((originalCommentId: String, reply: String) -> Unit)? = null,
	onLoadReplies: (originalCommentId: String, pageNumber: Int) -> Unit = { _, _ -> },
	footer: @Composable () -> Unit = {}
) {
	if (visible.not()) return
	val sheetState = rememberModalBottomSheetState()
	val noCommentsFound = totalNumberOfCommentsExpected == 0
	
	ModalBottomSheet(
		onDismissRequest = {
			onCommentsBottomDialogClosed()
		},
		sheetState = sheetState
	) {
		Column {
			Content(
				noCommentsFound,
				listOfComments,
				isLoadingReplies,
				listOfReplies,
				repliesPageNumber,
				canLoadMoreReplies,
				onSaveReply,
				onLoadReplies
			)
			footer()
		}
	}
}