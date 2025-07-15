package com.bellogatecaliphate.core.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bellogatecaliphate.core.model.dto.Content
import java.security.SecureRandom

val PLACEHOLDER_IMAGE_40DP = 40.dp
val PLACEHOLDER_2DP = 2.dp
val PLACEHOLDER_4DP = 4.dp
val PLACEHOLDER_8DP = 8.dp
val PLACEHOLDER_16DP = 16.dp
val PLACEHOLDER_24DP = 24.dp
val PLACEHOLDER_32DP = 32.dp
val PLACEHOLDER_80DP = 80.dp
val PLACEHOLDER_150DP = 150.dp
val PLACEHOLDER_200DP = 200.dp
val PLACEHOLDER_CONTENT_HISTORY_WIDTH = 100.dp
val PLACEHOLDER_CONTENT_HISTORY_HEIGHT = 150.dp

val PLACEHOLDER_TEXT_SIZE_20 = 20.sp
val PLACEHOLDER_TEXT_SIZE_30 = 30.sp

fun generateRandomNumberWithFixedLength(length: Int): String {
	if (length <= 0) {
		return "0"
	}
	val random = SecureRandom()
	val sb = StringBuilder(length)
	for (i in 0 until length) {
		sb.append(random.nextInt(10))
	}
	return sb.toString()
}

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
			"jeffemuveyan@gmail.com",
			"Jeff Emuveyan",
			"",
			false,
			false
		)
	)
}