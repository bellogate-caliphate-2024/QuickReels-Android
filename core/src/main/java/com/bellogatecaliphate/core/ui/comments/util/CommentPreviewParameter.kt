package com.bellogatecaliphate.core.ui.comments.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.bellogatecaliphate.core.model.dto.Comment

class CommentPreviewParameter : PreviewParameterProvider<Comment> {
	override val values: Sequence<Comment> = sequenceOf(
		Comment(
			"119992299222",
			"jeffemuveyan@gmail.com",
			"This is a test caption about a movie written by peter jackson. The movie is so good. It was written by Jeff in 2024",
			"2024-12-31",
			34,
		)
	)
}