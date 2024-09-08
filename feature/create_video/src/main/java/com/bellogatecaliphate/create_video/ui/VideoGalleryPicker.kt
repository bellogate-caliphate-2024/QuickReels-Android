package com.bellogatecaliphate.create_video.ui

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.bellogatecaliphate.create_video.util.StoragePermissionRationalDialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun VideoGalleryPicker(
    storagePermission: PermissionState,
    requestPermission:() -> Unit,
    onPermissionRationaleDismissed:() -> Unit) {

    val status = storagePermission.status
    val openAlertDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", context.packageName, null)
    intent.setData(uri)

    when {
        status.isGranted -> {
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
        !status.isGranted -> requestPermission()
    }
}