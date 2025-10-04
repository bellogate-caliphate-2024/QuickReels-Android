package com.bellogatecaliphate.domain

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetDateUseCase @Inject constructor() {
	
	operator fun invoke(showTime: Boolean = true): String {
		val format = if (showTime) {
			"H:mm a dd-MM-yyyy"
		} else {
			"dd-MM-yyyy"
		}
		val currentDate = Date()
		val dateFormat = SimpleDateFormat(format, Locale.getDefault())
		return dateFormat.format(currentDate)
	}
}