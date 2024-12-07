package com.bellogatecaliphate.account.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.bellogatecaliphate.core.model.dto.Content

class ContentPreviewParameter : PreviewParameterProvider<Content> {
	override val values: Sequence<Content> = sequenceOf(
		Content(
			"119992299222",
			"",
			"",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-2023 - 10:30 am",
			"100",
			"70",
			"23",
			"",
			"Jeff Emuveyan",
			""
		)
	)
}