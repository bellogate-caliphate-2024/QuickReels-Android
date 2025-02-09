package com.bellogatecaliphate.create_post.ui.create_post

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.bellogatecaliphate.create_post.util.StoragePermissionRationalDialog
import com.bellogatecaliphate.create_post.util.getStorageManifestPermission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun VideoFilePicker(
	visible: Boolean,
	onGalleryDismissed: (uri: String?) -> Unit,
	onStoragePermissionRationalDialogClosed: () -> Unit = {}
) {
	if (visible.not()) return
	
	val storagePermission = rememberPermissionState(getStorageManifestPermission())
	val status = storagePermission.status
	val closeStoragePermissionRationalDialog = remember { mutableStateOf(false) }
	val context = LocalContext.current
	val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
	val uri = Uri.fromParts("package", context.packageName, null)
	intent.setData(uri)
	
	when {
		status.isGranted -> VideoFileGallery(
			onGalleryDismissed
		)
		
		status.shouldShowRationale && closeStoragePermissionRationalDialog.value.not() -> {
			StoragePermissionRationalDialog(
				onDismissRequest = {
					onStoragePermissionRationalDialogClosed()
					closeStoragePermissionRationalDialog.value = true
				},
				onConfirmation = {
					onStoragePermissionRationalDialogClosed()
					closeStoragePermissionRationalDialog.value = true
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
private fun VideoFileGallery(
	onGalleryDismissed: (uri: String?) -> Unit
) {
	
	val selectVideoResultLauncher =
			rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { result: Uri? ->
				onGalleryDismissed(result?.toString())
			}
	
	LaunchedEffect(Unit) {
		selectVideoResultLauncher
			.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
	}
}