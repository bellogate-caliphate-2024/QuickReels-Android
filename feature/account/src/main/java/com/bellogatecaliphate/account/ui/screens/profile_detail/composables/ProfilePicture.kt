package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.util.PLACEHOLDER_200DP

@Composable
internal fun ProfilePicture(
	profilePictureUrl: String,
	isUploadingProfilePicture: Boolean,
	accountBelongsToLoggedInUser: Boolean,
	profilePictureUploadSuccessful: Boolean?,
	onUploadNewProfilePicture: (newProfilePicture: Bitmap) -> Unit
) {
	val context = LocalContext.current
	var openImagePicker by remember { mutableStateOf(false) }
	var selectedProfilePicture: Bitmap? by remember { mutableStateOf(null) }
	var croppedProfilePicture: Bitmap? by remember { mutableStateOf(null) }
	val message = stringResource(getStatusMessage(profilePictureUploadSuccessful))
	
	LaunchedEffect(profilePictureUploadSuccessful) {
		if (profilePictureUploadSuccessful != null) {
			Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
		}
	}
	
	Box(contentAlignment = Alignment.Center) {
		val profilePicture: Any? = getProfilePicture(
			isUploadingProfilePicture = isUploadingProfilePicture,
			profilePictureUploadSuccessful = profilePictureUploadSuccessful,
			croppedProfilePicture = croppedProfilePicture,
			profilePictureUrl = profilePictureUrl
		)
		AsyncImage(
			model = profilePicture,
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
		QuickReelsCircularProgressBar(show = isUploadingProfilePicture && profilePictureUploadSuccessful == null)
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
				onUploadNewProfilePicture(croppedImage)
			},
			onDismiss = { selectedProfilePicture = null }
		)
	}
}