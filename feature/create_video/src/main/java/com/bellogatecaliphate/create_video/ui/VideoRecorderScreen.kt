package com.bellogatecaliphate.create_video.ui

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellogatecaliphate.create_video.model.UiState
import com.bellogatecaliphate.create_video.util.StoragePermissionRationalDialog
import com.bellogatecaliphate.create_video.util.getStorageManifestPermission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@Composable
fun VideoRecorderScreen(viewModel: VideoRecorderScreenViewModel = hiltViewModel()) {
    VideoRecorderScreen(viewModel.state.collectAsStateWithLifecycle().value, {
        viewModel.requestPermissionAndOpenGallery()
    },{
        viewModel.resetToDefault()
    })
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun VideoRecorderScreen(uiState: UiState, openGallery:() -> Unit, onPermissionRationaleDismissed:() -> Unit) {
    val storagePermission = rememberPermissionState(getStorageManifestPermission())
    when(uiState) {
        UiState.Default -> VideoRecorderScreenDefaultState(openGallery)
        UiState.RequestStoragePermissionAndOpenGallery -> VideoGalleryPicker(
            storagePermission, {
            storagePermission.launchPermissionRequest()
        }, onPermissionRationaleDismissed)
        else -> VideoRecorderScreenDefaultState(openGallery)
    }
}

@Composable
private fun VideoRecorderScreenDefaultState(openGallery:() -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(10.dp)
            .align(Alignment.BottomCenter)) {
                TextButton(onClick = openGallery, modifier = Modifier.align(Alignment.BottomCenter)) {
                    Text("Select Video", fontSize = 20.sp)
                }
        }
    }
}

@PreviewScreenSizes
@Preview(showBackground = true)
@Composable
private fun Preview() {
    VideoRecorderScreen()
}