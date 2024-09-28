package com.bellogatecaliphate.core.model.util

import java.security.SecureRandom

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