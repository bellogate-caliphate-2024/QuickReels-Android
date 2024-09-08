package com.bellogatecaliphate.create_video.util

import android.Manifest
import android.os.Build

fun getStorageManifestPermission(): String {
    val isAndroid13AndAbove = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    return if (isAndroid13AndAbove) Manifest.permission.READ_MEDIA_VIDEO else Manifest.permission.READ_EXTERNAL_STORAGE
}