package com.bellogatecaliphate.create_post.ui.preview_post.util

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.bellogatecaliphate.create_post.R

@Composable
internal fun VideoCaptionNotProvidedPrompt() {
	val context = LocalContext.current
	LaunchedEffect(key1 = Unit) {
		Toast.makeText(context, R.string.videoCaptionTextIsNotProvided, Toast.LENGTH_LONG)
			.show()
	}
}