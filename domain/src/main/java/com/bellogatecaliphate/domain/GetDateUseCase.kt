package com.bellogatecaliphate.domain

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class GetDateUseCase @Inject constructor() {
	
	operator fun invoke(): String {
		val currentDate = Date()
		val dateFormat = SimpleDateFormat("H:mm a dd-MM-yyyy", Locale.getDefault())
		return dateFormat.format(currentDate)
	}
}