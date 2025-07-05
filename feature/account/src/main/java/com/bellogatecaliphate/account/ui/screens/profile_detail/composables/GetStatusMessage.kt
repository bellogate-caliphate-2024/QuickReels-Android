package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import com.bellogatecaliphate.account.R

fun getStatusMessage(profilePictureUploadSuccessful: Boolean?): Int {
	val successMessage = R.string.profile_picture_uploaded_successfully
	val errorMessage = R.string.profile_picture_uploaded_failed
	return when (profilePictureUploadSuccessful) {
		true  -> {
			successMessage
		}
		
		false -> {
			errorMessage
		}
		
		else  -> {
			errorMessage
		}
	}
}