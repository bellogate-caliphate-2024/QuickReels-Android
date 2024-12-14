package com.bellogatecaliphate.core.ui.comments.comments_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.ui.comments.model.Comment
import com.bellogatecaliphate.core.ui.comments.util.CommentAndReplies
import com.bellogatecaliphate.core.ui.comments.util.CommentAndRepliesPreviewParameter
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_IMAGE_40DP

@Composable
internal fun CommentItem(
	comment: Comment,
	isLoadingReplies: Boolean = false,
	listOfReplies: List<Comment> = emptyList(),
	repliesPageNumber: Int? = null,
	canLoadMoreReplies: Boolean = false,
	onSaveReply: ((originalCommentId: String, reply: String) -> Unit)? = null,
	onLoadReplies: (pageNumber: Int) -> Unit = {},
) {
	var openReplyCommentInputField by remember { mutableStateOf(false) }
	val totalListOfReplies =
			remember { mutableStateListOf<Comment>().apply { addAll(listOfReplies) } }
	
	Column(Modifier.fillMaxWidth()) {
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
		Row {
			/*AsyncImage(
				model = "https://example.com/image.jpg",
				contentDescription = null,
				modifier = Modifier
					.size(PLACEHOLDER_IMAGE_40DP)
					.clip(CircleShape)
			)*/
			Image(
				painter = painterResource(id = R.drawable.ic_launcher_background),
				contentDescription = "content description",
				modifier = Modifier
					.size(PLACEHOLDER_IMAGE_40DP)
					.clip(CircleShape)
			)
			Spacer(modifier = Modifier.width(PLACEHOLDER_8DP))
			Column {
				Text(text = comment.text)
				Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
				Row {
					Text(text = comment.date, color = colorResource(id = R.color.ash))
					Spacer(modifier = Modifier.width(PLACEHOLDER_16DP))
					ReplyText(openReplyCommentInputField.not()) {
						openReplyCommentInputField = true
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
			totalListOfReplies,
			repliesPageNumber,
			isLoadingReplies,
			canLoadMoreReplies,
			onLoadReplies
		)
	}
}

@Preview(showBackground = true)
@Composable
private fun PreviewCommentItem(
	@PreviewParameter(CommentAndRepliesPreviewParameter::class) commentAndReplies: CommentAndReplies,
) {
	CommentItem(
		comment = commentAndReplies.comment,
		listOfReplies = commentAndReplies.replies,
		canLoadMoreReplies = true,
	)
}