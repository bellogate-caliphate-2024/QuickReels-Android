package com.bellogatecaliphate.core.ui.comments.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.bellogatecaliphate.core.ui.comments.model.Comment

internal class CommentAndRepliesPreviewParameter : PreviewParameterProvider<CommentAndReplies> {
	override val values: Sequence<CommentAndReplies> = sequenceOf(
		CommentAndReplies(comment, replies)
	)
}

private val comment = Comment(
	"119992299222",
	"jeffemuveyan@gmail.com",
	"This is a test caption about a movie written by peter jackson. The movie is so good. It was written by Jeff in 2024",
	"2024-12-31",
	34,
)

private val replies = listOf(
	Comment(
		"119992299222",
		"jeffemuveyan@gmail.com",
		"This is a test caption about a movie written by peter jackson. The movie is so good. It was written by Jeff in 2024",
		"2024-12-31",
		null,
		isReply = true
	),
	Comment(
		"119992299222",
		"jeffemuveyan@gmail.com",
		"This is a test caption about a movie written by peter jackson. The movie is so good. It was written by Jeff in 2024",
		"2024-12-31",
		null,
		isReply = true
	),
	Comment(
		"119992299222",
		"jeffemuveyan@gmail.com",
		"This is a test caption about a movie written by peter jackson. The movie is so good. It was written by Jeff in 2024",
		"2024-12-31",
		null,
		isReply = true
	)

)

internal data class CommentAndReplies(val comment: Comment, val replies: List<Comment>)
