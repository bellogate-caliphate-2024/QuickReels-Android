package com.bellogatecaliphate.create_post.ui.create_post.upload_status

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.create_post.R
import com.bellogatecaliphate.create_post.util.getPlaceHolderBase64String

val PLACEHOLDER_THUMBNAIL = 40.dp

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun UploadStatusCard(
	caption: String = "Caption",
	date: String = "2024-04-12 : 00:00:00",
	uploadProgressPercentage: String = "Uploading... 60%",
	thumbnailBase64String: String? = getPlaceHolderBase64String(),
	onClicked: () -> Unit = {}
) {
	Row(Modifier.clickable(onClick = onClicked)) {
		ImagePreview(thumbnailBase64String)
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
private fun ImagePreview(
	thumbnailBase64String: String?
) {
	if (thumbnailBase64String == null) {
		Image(
			painter = painterResource(id = R.drawable.broken_image),
			contentDescription = "content description",
			modifier = Modifier.size(PLACEHOLDER_THUMBNAIL)
		)
	} else {
		val imageBytes = Base64.decode(thumbnailBase64String, Base64.DEFAULT)
		val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
		
		Image(
			bitmap = bitmap.asImageBitmap(), "content description",
			modifier = Modifier.size(PLACEHOLDER_THUMBNAIL)
		)
	}
}