package com.bellogatecaliphate.core.ui.comments

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.LazyPagingItems
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.model.dto.Comment
import com.bellogatecaliphate.core.ui.comments.dialog_content.Content

/**
 * @param visible is true if the CommentsBottomDialog should be visible.
 * after the user clicked on the button to open comments.
 * @param totalNumberOfCommentsExpected is the total number of comments that the content has.
 * @param listOfComments is the paginated list of comments.
 * @param commentDeletedSuccessfully is true when the comment has been deleted successfully.
 * @param isLoadingReplies is true when the ap is loading the list of replies to a comment.
 * @param listOfReplies is a non paginated list of replies to a comment.
 * @param repliesPageNumber is the current page number of replies that has been loaded.
 * @param canLoadMoreReplies is true if the comment has more replies to be loaded.
 * @param onCommentsBottomDialogClosed is called when the user closes the CommentsBottomDialog.
 * @param onSaveReply is called when the user writes a reply to a comment and presses the save button.
 * @param onLoadReplies is called when the user clicks on the button to load replies on a comment.
 * @param onDeleteComment is called when the user gives consent to delete a comment.
 * @param advertContainer this is the composable that should be displayed at the top of the comments.
 *
 * ***/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsBottomDialog(
	visible: Boolean = false,
	contentId: String,
	loggedInUserEmail: String?,
	totalNumberOfCommentsExpected: Int,
	listOfComments: LazyPagingItems<Comment>?,
	isUploadingComment: Boolean = false,
	commentUploadedSuccessfully: Boolean? = false,
	commentDeletedSuccessfully: Boolean? = null,
	listOfDeletedComments: MutableList<String> = mutableListOf(),
	listOfCachedComments: List<Comment> = emptyList(),
	isLoadingReplies: Boolean = false,
	listOfReplies: List<Comment> = emptyList(),
	repliesPageNumber: Int? = null,
	canLoadMoreReplies: Boolean = false,
	onCommentsBottomDialogClosed: () -> Unit = {},
	onSaveReply: ((originalCommentId: String, reply: String) -> Unit)? = null,
	onLoadReplies: (originalCommentId: String, pageNumber: Int) -> Unit = { _, _ -> },
	onDeleteComment: (contentId: String, commentId: String) -> Unit = { _, _ -> },
	onAddComment: (contentId: String, parentCommentId: String?, comment: String) -> Unit = { _, _, _ -> },
	advertContainer: @Composable () -> Unit = {}
) {
	if (visible.not()) return
	
	val context = LocalContext.current
	val sheetState = rememberModalBottomSheetState(
		skipPartiallyExpanded = true,
		confirmValueChange = { value ->
			// Block swipe dismiss: only allow when sheet is Hidden
			value != SheetValue.Hidden
		}
	)
	val noCommentsFound = totalNumberOfCommentsExpected == 0
	
	ModalBottomSheet(
		dragHandle = { BottomSheetDefaults.DragHandle() },
		onDismissRequest = {
			onCommentsBottomDialogClosed()
		},
		sheetState = sheetState
	) {
		Column(modifier = Modifier.fillMaxHeight(0.6f)) { // takes 60% of the screen height
			advertContainer()
			Content(
				contentId = contentId,
				loggedInUserEmail = loggedInUserEmail,
				noCommentsFound = noCommentsFound,
				listOfComments = listOfComments,
				isUploadingComment = isUploadingComment,
				totalNumberOfCommentsExpected = totalNumberOfCommentsExpected,
				commentUploadedSuccessfully = commentUploadedSuccessfully,
				commentDeletedSuccessfully = commentDeletedSuccessfully,
				listOfDeletedComments = listOfDeletedComments,
				listOfCachedComments = listOfCachedComments,
				isLoadingReplies = isLoadingReplies,
				listOfReplies = listOfReplies,
				repliesPageNumber = repliesPageNumber,
				canLoadMoreReplies = canLoadMoreReplies,
				onSaveReply = onSaveReply,
				onLoadReplies = onLoadReplies,
				onDeleteComment = onDeleteComment,
				onAddComment = onAddComment
			)
		}
	}
	
	LaunchedEffect(commentUploadedSuccessfully) {
		if (commentUploadedSuccessfully == false) {
			Toast.makeText(
				context,
				context.getString(R.string.failed_to_add_comment_try_again),
				Toast.LENGTH_SHORT
			).show()
		}
	}
}