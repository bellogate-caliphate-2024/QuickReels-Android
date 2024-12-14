package com.bellogatecaliphate.core.ui.comments.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.bellogatecaliphate.core.ui.comments.model.Comment

class CommentsListPreviewParameter : PreviewParameterProvider<List<Comment>> {
	override val values: Sequence<List<Comment>> = sequenceOf(
		listOf(
			Comment(
				"119992299222",
				"jeffemuveyan@gmail.com",
				"This is a test caption about a movie written by peter jackson.",
				"2024-12-31",
				34,
			),
			Comment(
				"119992299222",
				"jeffemuveyan@gmail.com",
				"This is a test caption about a movie written by peter jackson. The movie is so good. It was written by Jeff in 2024",
				"2024-12-31",
				0,
			),
			Comment(
				"119992299222",
				"jeffemuveyan@gmail.com",
				"This is a test caption",
				"2024-12-31",
				1,
			),
		)
	)
}