package com.bellogatecaliphate.create_post.ui.confirm_post

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bellogatecaliphate.core.model.dto.Post
import com.bellogatecaliphate.create_post.R
import kotlinx.coroutines.launch

val PLACEHOLDER_SIZE_24 = 24.dp

@Composable
fun UploadPostConfirmationDialog(
	post: Post,
	onPostQueuedForUpload: () -> Unit,
	viewModel: UploadPostConfirmationViewModel = hiltViewModel()
) {
	
	UploadPostConfirmationDialog {
		viewModel.enQueuePostForUpload(post)
		onPostQueuedForUpload()
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UploadPostConfirmationDialog(onConfirmationGiven: () -> Unit) {
	val sheetState = rememberModalBottomSheetState()
	val scope = rememberCoroutineScope()
	var showBottomSheet by remember { mutableStateOf(false) }
	
	ModalBottomSheet(
		onDismissRequest = {
			showBottomSheet = false
		},
		sheetState = sheetState
	) {
		Column(Modifier.fillMaxWidth()) {
			Text(stringResource(id = R.string.upload_post_confirmation_message))
			Spacer(modifier = Modifier.height(PLACEHOLDER_SIZE_24))
			Button(onClick = {
				scope.launch { sheetState.hide() }.invokeOnCompletion {
					if (! sheetState.isVisible) {
						showBottomSheet = false
					}
				}
				onConfirmationGiven()
			}) {
				Text(stringResource(id = R.string.yes))
			}
		}
	}
}
