package com.bellogatecaliphate.create_post.util

import android.Manifest
import android.os.Build
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bellogatecaliphate.create_post.R

fun getStorageManifestPermission(): String {
    val isAndroid13AndAbove = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    return if (isAndroid13AndAbove) Manifest.permission.READ_MEDIA_VIDEO else Manifest.permission.READ_EXTERNAL_STORAGE
}

@Preview
@Composable
fun StoragePermissionRationalDialog(onDismissRequest: () -> Unit = {},
                                    onConfirmation: () -> Unit = {}) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(
                onClick = { onConfirmation() }
            ) {
                Text(text = stringResource(id = R.string.go_to_settings))
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismissRequest() }
            ) {
                Text(text = stringResource(id = R.string.close))
            }
        },
        text = { Text(text = stringResource(id = R.string.storage_permission_rationale)) }
    )
}
