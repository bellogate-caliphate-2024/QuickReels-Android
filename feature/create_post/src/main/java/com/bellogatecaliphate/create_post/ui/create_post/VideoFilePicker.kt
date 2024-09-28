package com.bellogatecaliphate.create_post.ui.create_post

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.bellogatecaliphate.create_post.util.StoragePermissionRationalDialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun VideoFilePicker(
	storagePermission: PermissionState,
	requestPermission: () -> Unit,
	onPermissionRationaleDismissed: () -> Unit,
	onVideoFileSelected: (String) -> Unit
) {
	
	val status = storagePermission.status
	val openAlertDialog = remember { mutableStateOf(false) }
	val context = LocalContext.current
	val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
	val uri = Uri.fromParts("package", context.packageName, null)
	intent.setData(uri)
	
	when {
		status.isGranted -> {
			VideoFileGallery(onVideoFileSelected)
		}
		
		status.shouldShowRationale -> {
			StoragePermissionRationalDialog(
				onDismissRequest = {
					openAlertDialog.value = false
					onPermissionRationaleDismissed()
				},
				onConfirmation = {
					openAlertDialog.value = false
					context.startActivity(intent)
					onPermissionRationaleDismissed()
				}
			)
		}
		
		! status.isGranted -> requestPermission()
	}
	
	DisposableEffect(Unit) {
		onDispose { if (! status.isGranted) onPermissionRationaleDismissed() }
	}
}

@Composable
private fun VideoFileGallery(onVideoFileSelected: (uri: String) -> Unit) {
	val selectVideoResultLauncher =
			rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { result: Uri? ->
				if (result != null) {
					onVideoFileSelected(result.toString())
				}
			}
	
	LaunchedEffect(Unit) {
		selectVideoResultLauncher
			.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
	}
}