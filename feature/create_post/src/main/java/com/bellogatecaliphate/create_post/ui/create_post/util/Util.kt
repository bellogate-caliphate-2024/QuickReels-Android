package com.bellogatecaliphate.create_post.ui.create_post.util

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.bellogatecaliphate.create_post.util.video_trimer.utils.TrimVideo

@Composable
internal fun activityLauncher(
	onPostReadyForPreview: (
		videoPath: String,
		videoCaption: String?,
		isReadOnly: Boolean
	) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
	return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
		val data = result.data
		if (result.resultCode == Activity.RESULT_OK && data != null) {
			onPostReadyForPreview(TrimVideo.getTrimmedVideoPath(data), null, false)
		}
	}
}