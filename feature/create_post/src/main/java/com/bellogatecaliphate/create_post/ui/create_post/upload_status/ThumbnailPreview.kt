package com.bellogatecaliphate.create_post.ui.create_post.upload_status

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.bellogatecaliphate.create_post.R
import java.io.File

@Composable
internal fun ThumbnailPreview(thumbnailFilePath: String?, size: Dp) {
	val aModifier = Modifier
		.clip(CircleShape)
		.size(size)
	
	if (thumbnailFilePath == null) {
		Image(
			painter = painterResource(id = R.drawable.broken_image),
			contentDescription = "content description",
			modifier = aModifier
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
				modifier = aModifier,
				contentScale = ContentScale.FillBounds
			)
		}
	}
}