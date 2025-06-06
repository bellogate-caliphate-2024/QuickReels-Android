package com.bellogatecaliphate.create_post.ui.create_post.upload_status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.util.PLACEHOLDER_150DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_200DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_TEXT_SIZE_30
import com.bellogatecaliphate.create_post.R

@Composable
internal fun UploadStatusScreen(
	modifier: Modifier,
	numberOfUploadsInProgressToDisplay: Int,
	uploadsInProgress: List<Post>,
	onPostClicked: (Post) -> Unit
) {
	if (uploadsInProgress.isEmpty()) return
	if (numberOfUploadsInProgressToDisplay == 1) {
		SingleUploadStatusScreen(uploadsInProgress.first())
	} else {
		MultipleUploadsStatusScreen(modifier, uploadsInProgress, onPostClicked)
	}
}

@Composable
private fun SingleUploadStatusScreen(uploadInProgress: Post) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(color = Color.White),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Box(contentAlignment = Alignment.Center) {
			QuickReelsCircularProgressBar(PLACEHOLDER_200DP)
			ThumbnailPreview(uploadInProgress.thumbnailFilePath, PLACEHOLDER_150DP)
		}
		Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
		Text(
			stringResource(R.string.uploading, uploadInProgress.uploadProgressPercentage),
			fontSize = PLACEHOLDER_TEXT_SIZE_30
		)
	}
}

@Composable
private fun MultipleUploadsStatusScreen(
	modifier: Modifier,
	uploadsInProgress: List<Post>,
	onPostClicked: (Post) -> Unit
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.background(color = Color.White)
			.padding(10.dp)
	) {
		Text(stringResource(R.string.uploads), fontSize = PLACEHOLDER_TEXT_SIZE_30)
		Spacer(modifier = Modifier.height(PLACEHOLDER_8DP))
		LazyColumn(state = rememberLazyListState()) {
			items(uploadsInProgress.size) { index ->
				val post = uploadsInProgress[index]
				UploadStatusCard(
					post.time,
					post.uploadProgressPercentage,
					post.thumbnailFilePath
				) { onPostClicked(post) }
				Spacer(Modifier.height(PLACEHOLDER_16DP))
			}
		}
	}
}