package com.bellogatecaliphate.timeline.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.core.model.dto.Content

val PLACEHOLDER_IMAGE_40DP = 40.dp
val PLACEHOLDER_8DP = 8.dp
val PLACEHOLDER_16DP = 16.dp
val PLACEHOLDER_ICON_SIZE = 32.dp

class ContentPreviewParameter : PreviewParameterProvider<Content> {
	override val values: Sequence<Content> = sequenceOf(
		Content(
			"119992299222",
			"",
			"",
			"This is a test caption about a movie written by peter jackson. The movie is so good.",
			"05-05-2023 - 10:30 am",
			"70",
			"23",
			"",
			"Jeff Emuveyan",
			""
		)
	)
}