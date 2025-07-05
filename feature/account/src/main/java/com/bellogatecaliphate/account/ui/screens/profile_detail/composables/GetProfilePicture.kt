package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import android.graphics.Bitmap

internal fun getProfilePicture(
	isUploadingProfilePicture: Boolean,
	profilePictureUploadSuccessful: Boolean?,
	croppedProfilePicture: Bitmap?,
	profilePictureUrl: String
): Any? {
	val image: Any? = when {
		isUploadingProfilePicture || profilePictureUploadSuccessful == true -> croppedProfilePicture
		profilePictureUploadSuccessful == false                             -> profilePictureUrl
		else                                                                -> profilePictureUrl
	}
	return image
}