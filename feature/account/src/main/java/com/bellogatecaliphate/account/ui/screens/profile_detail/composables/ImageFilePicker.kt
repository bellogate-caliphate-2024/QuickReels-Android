package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.bellogatecaliphate.core.ui.StoragePermissionRationalDialog
import com.bellogatecaliphate.core.ui.getStorageManifestPermission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImageFilePicker(
	visible: Boolean,
	onGalleryDismissed: (image: Bitmap?) -> Unit,
	onStoragePermissionRationalDialogClosed: () -> Unit = {}
) {
	if (visible.not()) return
	
	val storagePermission = rememberPermissionState(getStorageManifestPermission())
	val status = storagePermission.status
	val context = LocalContext.current
	val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
	val uri = Uri.fromParts("package", context.packageName, null)
	intent.setData(uri)
	
	when {
		status.isGranted -> ImageFileGallery(
			onGalleryDismissed
		)
		
		status.shouldShowRationale -> {
			StoragePermissionRationalDialog(
				onDismissRequest = {
					onStoragePermissionRationalDialogClosed()
				},
				onConfirmation = {
					onStoragePermissionRationalDialogClosed()
					context.startActivity(intent)
				}
			)
		}
		
		! status.isGranted -> {
			LaunchedEffect(Unit) {
				storagePermission.launchPermissionRequest()
			}
		}
	}
}

/**
 * @param onGalleryDismissed: is called anytime the gallery is closed regardless of whether the user
 * selected a video or not. However, if the user did select a video, the uri will not be null.
 * **/
@Composable
private fun ImageFileGallery(
	onGalleryDismissed: (bitmap: Bitmap?) -> Unit
) {
	val context = LocalContext.current
	val selectVideoResultLauncher =
			rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { result: Uri? ->
				if (result != null) {
					val bitmap = loadBitmapFromUri(context, result)
					onGalleryDismissed(bitmap)
				} else {
					onGalleryDismissed(null)
				}
			}
	
	LaunchedEffect(Unit) {
		selectVideoResultLauncher
			.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
	}
}

private fun loadBitmapFromUri(context: Context, uri: Uri): Bitmap? {
	return try {
		val source = ImageDecoder.createSource(context.contentResolver, uri)
		ImageDecoder.decodeBitmap(source)
	}
	catch (e: Exception) {
		e.printStackTrace()
		null
	}
}