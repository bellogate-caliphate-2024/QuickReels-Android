package com.bellogatecaliphate.domain

import com.bellogatecaliphate.domain.user.GetLoggedInUserEmailUseCase
import javax.inject.Inject

class GenerateIdUseCase @Inject constructor(private val getUserEmailUseCase: GetLoggedInUserEmailUseCase) {
	
	// generates a 10 digit random string with the user's email as a prefix
	operator fun invoke(): String {
		val userEmail = getUserEmailUseCase() ?: ""
		val random10DigitString = (1_000_000_000L .. 9_999_999_999L).random().toString()
		return "$userEmail-$random10DigitString"
	}
}