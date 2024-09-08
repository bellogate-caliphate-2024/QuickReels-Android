package com.bellogatecaliphate.create_post.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellogatecaliphate.create_post.model.UiState
import com.bellogatecaliphate.create_post.util.getStorageManifestPermission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun CreatePostRoute(viewModel: CreatePostScreenViewModel = hiltViewModel()) {
    CreatePostScreen(viewModel.state.collectAsStateWithLifecycle().value, {
        viewModel.requestPermissionAndOpenGallery()
    },{
        viewModel.resetToDefault()
    })
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun CreatePostScreen(uiState: UiState, openGallery:() -> Unit, onPermissionRationaleDismissed:() -> Unit) {
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
    CreatePostRoute()
}