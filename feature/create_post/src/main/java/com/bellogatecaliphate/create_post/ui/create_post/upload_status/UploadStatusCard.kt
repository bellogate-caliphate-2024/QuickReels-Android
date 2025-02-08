package com.bellogatecaliphate.create_post.ui.create_post.upload_status

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.create_post.R
import java.io.File

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
		ThumbnailPreview(thumbnailFilePath)
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

@Composable
private fun ThumbnailPreview(thumbnailFilePath: String?) {
	val modifier = Modifier
		.size(PLACEHOLDER_THUMBNAIL)
		.border(
			width = 1.dp,
			color = Color.LightGray,
			shape = RoundedCornerShape(50)
		)
		.clip(RoundedCornerShape(50))
	
	if (thumbnailFilePath == null) {
		Image(
			painter = painterResource(id = R.drawable.broken_image),
			contentDescription = "content description",
			modifier = modifier
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
				modifier = modifier
			)
		}
	}
}