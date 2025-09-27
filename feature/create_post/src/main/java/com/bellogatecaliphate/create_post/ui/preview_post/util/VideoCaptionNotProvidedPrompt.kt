package com.bellogatecaliphate.create_post.ui.preview_post.util

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.bellogatecaliphate.create_post.R

@Composable
internal fun VideoCaptionNotProvidedPrompt(show: Boolean) {
	if (show.not()) return
	val context = LocalContext.current
	Toast.makeText(context, R.string.videoCaptionTextIsNotProvided, Toast.LENGTH_LONG)
		.show()
}