package com.bellogatecaliphate.create_post.ui.create_post.upload_status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_TEXT_SIZE_20
import com.bellogatecaliphate.core.util.PLACEHOLDER_TEXT_SIZE_30
import com.bellogatecaliphate.create_post.R

@Composable
internal fun UploadStatusCardHolder(
	uploadsInProgress: List<Post>,
	openGallery: () -> Unit,
	onPostClicked: (Post) -> Unit
) {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(color = Color.Black)
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.background(color = Color.White)
				.padding(10.dp)
				.align(Alignment.TopCenter)
		) {
			Column {
				Text(stringResource(R.string.uploads), fontSize = PLACEHOLDER_TEXT_SIZE_30)
				Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
				uploadsInProgress.forEach { post ->
					UploadStatusCard(
						post.caption,
						post.time,
						post.uploadProgressPercentage,
						post.thumbnailFilePath
					) { onPostClicked(post) }
				}
			}
		}
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.background(color = Color.White)
				.padding(10.dp)
				.align(Alignment.BottomCenter)
		) {
			TextButton(onClick = openGallery, modifier = Modifier.align(Alignment.BottomCenter)) {
				Text(stringResource(R.string.select_video), fontSize = PLACEHOLDER_TEXT_SIZE_20)
			}
		}
	}
}