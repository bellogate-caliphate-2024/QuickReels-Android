package com.bellogatecaliphate.create_post.ui.create_post.upload_status

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.create_post.R
import java.io.File

val PLACEHOLDER_THUMBNAIL = 40.dp

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UploadStatusCard(
	caption: String = "Caption",
	date: String = "2024-04-12 : 00:00:00",
	uploadProgressPercentage: String = "100",
	thumbnailFilePath: String? = null,
	onClicked: () -> Unit = {}
) {
	Row(Modifier.clickable(onClick = onClicked)) {
		ThumbnailPreview(thumbnailFilePath)
		Details(caption, date, uploadProgressPercentage)
	}
}

@Composable
private fun Details(
	caption: String,
	date: String,
	uploadProgressPercentage: String
) {
	Column {
		Text(text = caption)
		Text(text = date)
		Text(text = "${stringResource(id = R.string.uploading_in_progress)} $uploadProgressPercentage%")
	}
}

@Composable
private fun ThumbnailPreview(
	thumbnailFilePath: String?
) {
	if (thumbnailFilePath == null) {
		Image(
			painter = painterResource(id = R.drawable.broken_image),
			contentDescription = "content description",
			modifier = Modifier.size(PLACEHOLDER_THUMBNAIL)
		)
	} else {
		val bitmap = remember { mutableStateOf<Bitmap?>(null) }
		LaunchedEffect(thumbnailFilePath) {
			val file = File(thumbnailFilePath)
			if (file.exists()) {
				bitmap.value = BitmapFactory.decodeFile(file.absolutePath)
			}
		}
		
		bitmap.value?.let { btm ->
			Image(
				bitmap = btm.asImageBitmap(), "content description",
				modifier = Modifier.size(PLACEHOLDER_THUMBNAIL)
			)
		}
	}
}