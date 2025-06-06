package com.bellogatecaliphate.create_post.ui.create_post.upload_status

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.bellogatecaliphate.create_post.ui.delete_post.DeletePostConfirmationDialog

@Composable
internal fun UploadStatusScreen(
	modifier: Modifier,
	numberOfUploadsInProgressToDisplay: Int,
	uploadsInProgress: List<Post>,
	onPostClicked: (Post) -> Unit,
	onCancelClicked: (Post) -> Unit
) {
	if (uploadsInProgress.isEmpty()) return
	Content(
		modifier,
		numberOfUploadsInProgressToDisplay,
		uploadsInProgress,
		onPostClicked,
		onCancelClicked
	)
}

@Composable
private fun Content(
	modifier: Modifier,
	numberOfUploadsInProgressToDisplay: Int,
	uploadsInProgress: List<Post>,
	onPostClicked: (Post) -> Unit,
	onCancelClicked: (Post) -> Unit
) {
	if (numberOfUploadsInProgressToDisplay == 1) {
		SingleUploadStatusScreen(uploadsInProgress.first(), onPostClicked, onCancelClicked)
	} else {
		MultipleUploadsStatusScreen(modifier, uploadsInProgress, onPostClicked)
	}
}

@Composable
private fun SingleUploadStatusScreen(
	uploadInProgress: Post,
	onPostClicked: (Post) -> Unit,
	onCancelClicked: (Post) -> Unit
) {
	var showCancelUploadBottomSheetDialog by remember { mutableStateOf(false) }
	Scaffold(
		topBar = {
			SingleUploadStatusScreenTopBar { showCancelUploadBottomSheetDialog = true }
		}
	) { innerPadding ->
		SingleUploadStatusScreenContent(
			modifier = Modifier.padding(innerPadding),
			uploadInProgress,
			onPostClicked
		)
	}
	DeletePostConfirmationDialog(
		isVisible = showCancelUploadBottomSheetDialog,
		onConfirmationGiven = {
			showCancelUploadBottomSheetDialog = false
			onCancelClicked(uploadInProgress)
		},
		onDismiss = { showCancelUploadBottomSheetDialog = false }
	)
}

@Composable
private fun SingleUploadStatusScreenTopBar(onCancelClicked: () -> Unit) {
	Box(
		modifier = Modifier
			.background(Color.White)
			.padding(PLACEHOLDER_16DP)
			.fillMaxWidth(),
		contentAlignment = Alignment.CenterEnd
	) {
		Image(
			painterResource(R.drawable.ic_cancel),
			contentDescription = "",
			modifier = Modifier.clickable(onClick = onCancelClicked),
		)
	}
}

@Composable
private fun SingleUploadStatusScreenContent(
	modifier: Modifier,
	uploadInProgress: Post,
	onPostClicked: (Post) -> Unit
) {
	Column(
		modifier = modifier
			.fillMaxSize()
			.background(color = Color.White),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Box(
			modifier = Modifier.clickable(onClick = { onPostClicked(uploadInProgress) }),
			contentAlignment = Alignment.Center
		) {
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