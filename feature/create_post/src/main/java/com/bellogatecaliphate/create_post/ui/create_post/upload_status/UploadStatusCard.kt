package com.bellogatecaliphate.create_post.ui.create_post.upload_status

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.create_post.R

val PLACEHOLDER_THUMBNAIL = 80.dp

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UploadStatusCard(
	date: String = "2024-04-12 : 00:00:00",
	uploadProgressPercentage: String = "100",
	thumbnailFilePath: String? = null,
	onClicked: () -> Unit = {}
) {
	Row(
		Modifier
			.clickable(onClick = onClicked)
			.fillMaxWidth(),
		verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
	) {
		ThumbnailPreview(thumbnailFilePath, PLACEHOLDER_THUMBNAIL)
		Spacer(Modifier.width(PLACEHOLDER_16DP))
		Details(date, uploadProgressPercentage)
	}
}

@Composable
private fun Details(
	date: String,
	uploadProgressPercentage: String
) {
	Column {
		Text(text = "${stringResource(id = R.string.uploading_in_progress)} $uploadProgressPercentage%")
		Text(text = date, color = Color.LightGray)
	}
}