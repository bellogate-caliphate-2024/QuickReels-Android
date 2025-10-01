package com.bellogatecaliphate.core.ui.comments.comments_list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import coil3.compose.AsyncImage
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.model.dto.Comment
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.ui.comments.util.CommentAndReplies
import com.bellogatecaliphate.core.ui.comments.util.CommentAndRepliesPreviewParameter
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_IMAGE_40DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_IMAGE_45DP

@Composable
internal fun CommentItem(
	comment: Comment,
	contentId: String,
	visible: Boolean = true, // we need this visibility so that when the user scrolls down and back up the list, deleted item remain hidden.
	loggedInUserEmail: String? = null,
	commentDeletedSuccessfully: Boolean? = null,
	isLoadingReplies: Boolean = false,
	listOfReplies: List<Comment> = emptyList(),
	repliesPageNumber: Int? = null,
	canLoadMoreReplies: Boolean = false,
	onSaveReply: ((originalCommentId: String, reply: String) -> Unit)? = null,
	onLoadReplies: (originalCommentId: String, pageNumber: Int) -> Unit = { _, _ -> },
	onDeleteComment: (contentId: String, commentId: String) -> Unit = { _, _ -> }
) {
	val commentBelongsTologgedInUser = loggedInUserEmail == comment.userId
	var openReplyCommentInputField by remember { mutableStateOf(false) }
	var isDeletingComment by remember { mutableStateOf(false) }
	val totalListOfReplies = remember {
		mutableStateListOf<Comment>().apply { addAll(listOfReplies) }
	}
	
	CommentDeleteStatusInfo(isDeletingComment, commentDeletedSuccessfully)
	if (visible.not()) return
	if (isDeletingComment && commentDeletedSuccessfully == false) isDeletingComment = false
	
	Column(Modifier.fillMaxWidth()) {
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
		Row {
			Box(contentAlignment = Alignment.Center) {
				AsyncImage(
					model = comment.userProfilePictureUrl,
					contentDescription = "content description",
					modifier = Modifier
						.size(PLACEHOLDER_IMAGE_40DP)
						.clip(CircleShape)
				)
				QuickReelsCircularProgressBar(
					show = isDeletingComment,
					size = PLACEHOLDER_IMAGE_45DP
				)
			}
			Spacer(modifier = Modifier.width(PLACEHOLDER_8DP))
			Column {
				Text(
					text = comment.text,
					style = MaterialTheme.typography.bodySmall,
					textAlign = TextAlign.Justify,
				)
				Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
				Row {
					Text(
						text = comment.date,
						color = colorResource(id = R.color.ash),
						style = MaterialTheme.typography.bodySmall
					)
					Spacer(modifier = Modifier.width(PLACEHOLDER_16DP))
					ReplyText(openReplyCommentInputField.not()) {
						openReplyCommentInputField = true
					}
					Spacer(modifier = Modifier.width(PLACEHOLDER_16DP))
					DeleteLabel(commentBelongsTologgedInUser && isDeletingComment.not()) {
						isDeletingComment = true
						onDeleteComment(contentId, comment.commentId)
					}
				}
				Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
				ReplyCommentInputText(openReplyCommentInputField, {
					onSaveReply?.invoke(comment.commentId, it)
					openReplyCommentInputField = false
				}, {
					openReplyCommentInputField = false
				})
			}
		}
		NumberOfReplies(comment, isLoadingReplies, totalListOfReplies.size, onLoadReplies)
		RepliesList(
			contentId,
			comment.commentId,
			totalListOfReplies,
			repliesPageNumber,
			isLoadingReplies,
			canLoadMoreReplies,
			onLoadReplies
		)
	}
}

@Composable
private fun CommentDeleteStatusInfo(isDeletingComment: Boolean, deletedSuccessfully: Boolean?) {
	if (! isDeletingComment || deletedSuccessfully == null) return
	val context = LocalContext.current
	val message = if (deletedSuccessfully) {
		stringResource(R.string.deleted)
	} else {
		stringResource(R.string.failed_to_delete_try_again)
	}
	
	LaunchedEffect(deletedSuccessfully) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
	}
}

@Preview(showBackground = true)
@Composable
private fun PreviewCommentItem(
	@PreviewParameter(CommentAndRepliesPreviewParameter::class) commentAndReplies: CommentAndReplies,
) {
	CommentItem(
		contentId = "",
		visible = true,
		comment = commentAndReplies.comment,
		listOfReplies = commentAndReplies.replies,
		canLoadMoreReplies = true,
	)
}