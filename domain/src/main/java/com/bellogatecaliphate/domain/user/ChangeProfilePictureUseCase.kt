package com.bellogatecaliphate.domain.user

import android.graphics.Bitmap
import com.bellogatecaliphate.user.IUserRepository
import javax.inject.Inject

class ChangeProfilePictureUseCase @Inject constructor(
	private val userRepository: IUserRepository,
	private val convertImageToBase64UseCase: ConvertImageToBase64UseCase
) {
	
	suspend operator fun invoke(
		userEmail: String,
		newProfilePicture: Bitmap
	): Boolean {
		val imageAsBase64 = convertImageToBase64UseCase(newProfilePicture)
		return userRepository.changeProfilePicture(
			userEmail = userEmail,
			newProfilePictureBase64Encoded = imageAsBase64
		)
	}
}