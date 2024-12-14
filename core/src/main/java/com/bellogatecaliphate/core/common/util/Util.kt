package com.bellogatecaliphate.core.common.util

import androidx.compose.ui.unit.dp
import java.security.SecureRandom

val PLACEHOLDER_IMAGE_40DP = 40.dp
val PLACEHOLDER_4DP = 4.dp
val PLACEHOLDER_8DP = 8.dp
val PLACEHOLDER_16DP = 16.dp
val PLACEHOLDER_24DP = 24.dp
val PLACEHOLDER_ICON_SIZE = 32.dp
val PLACEHOLDER_CONTENT_HISTORY_WIDTH = 100.dp
val PLACEHOLDER_CONTENT_HISTORY_HEIGHT = 150.dp

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