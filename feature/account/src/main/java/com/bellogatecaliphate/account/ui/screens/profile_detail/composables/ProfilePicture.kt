package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import android.graphics.Bitmap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import coil3.compose.AsyncImage
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.util.PLACEHOLDER_200DP

@Composable
internal fun ProfilePicture(
	profilePictureUrl: String,
	isUploadingProfilePicture: Boolean,
	accountBelongsToLoggedInUser: Boolean,
) {
	var openImagePicker by remember { mutableStateOf(false) }
	var selectedProfilePicture: Bitmap? by remember { mutableStateOf(null) }
	var croppedProfilePicture: Bitmap? by remember { mutableStateOf(null) }
	
	Box(contentAlignment = Alignment.Center) {
		AsyncImage(
			model = croppedProfilePicture ?: profilePictureUrl,
			contentDescription = "content description",
			modifier = Modifier
				.size(PLACEHOLDER_200DP)
				.clip(CircleShape)
				.then(
					if (accountBelongsToLoggedInUser && isUploadingProfilePicture.not()) {
						Modifier.clickable { openImagePicker = true }
					} else {
						Modifier
					}
				)
		)
		QuickReelsCircularProgressBar(show = isUploadingProfilePicture)
		ImageFilePicker(
			visible = openImagePicker,
			onGalleryDismissed = { image ->
				selectedProfilePicture = image
				openImagePicker = false
			},
			onStoragePermissionRationalDialogClosed = {
			
			}
		)
		ImageCropper(
			show = selectedProfilePicture != null,
			image = selectedProfilePicture,
			onConfirmationGiven = { croppedImage ->
				croppedProfilePicture = croppedImage
				selectedProfilePicture = null
			},
			onDismiss = { selectedProfilePicture = null }
		)
	}
}