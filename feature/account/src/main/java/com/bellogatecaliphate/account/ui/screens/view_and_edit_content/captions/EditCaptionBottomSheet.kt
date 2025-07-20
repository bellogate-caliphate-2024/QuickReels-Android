package com.bellogatecaliphate.account.ui.screens.view_and_edit_content.captions

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_24DP
import com.bellogatecaliphate.core.R as CoreR

@Composable
internal fun EditCaptionBottomSheet(
	show: Boolean,
	caption: String,
	onSavedSuccessfully: () -> Unit,
	onDismiss: () -> Unit,
) {
	if (show.not()) return
	val viewModel: EditCaptionViewModel = hiltViewModel()
	val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
	Content(
		uiState = uiState,
		caption = caption,
		onSave = { newCaption ->
			viewModel.editCaption(caption, newCaption)
		},
		onSavedSuccessfully = onSavedSuccessfully,
		onDismiss = onDismiss
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
	uiState: UiState,
	caption: String,
	onSave: (newCaption: String) -> Unit,
	onSavedSuccessfully: () -> Unit,
	onDismiss: () -> Unit,
) {
	val context = LocalContext.current
	val sheetState = rememberModalBottomSheetState()
	var showBottomSheet by remember { mutableStateOf(false) }
	var latestCaption by remember { mutableStateOf("") }
	
	LaunchedEffect(uiState) {
		showStatusMessage(context, uiState.savedSuccessfully)
		if (uiState.savedSuccessfully == true) onSavedSuccessfully()
	}
	
	ModalBottomSheet(
		onDismissRequest = {
			showBottomSheet = false
			onDismiss.invoke()
		},
		sheetState = sheetState
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(PLACEHOLDER_16DP),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			CaptionEditTextField(
				caption = caption,
				latestCaption = latestCaption,
				onValueChange = { latestCaption = it }
			)
			Spacer(modifier = Modifier.height(PLACEHOLDER_24DP))
			QuickReelsCircularProgressBar(show = uiState.isLoading)
			val showEditButton = uiState.isLoading.not() && uiState.savedSuccessfully == null
			if (showEditButton)
				TextButton(
					onClick = {
						onSave(latestCaption)
					},
					modifier = Modifier.fillMaxWidth()
				) {
					Text(
						stringResource(R.string.save_edit),
						color = colorResource(CoreR.color.quickreels_purple),
						fontWeight = FontWeight.Bold
					)
				}
		}
	}
}

private fun showStatusMessage(context: Context, success: Boolean?) {
	if (success == null) return
	val text = if (success) R.string.caption_saved else R.string.caption_saved_failed
	Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

@Composable
private fun CaptionEditTextField(
	caption: String,
	latestCaption: String,
	onValueChange: (String) -> Unit
) {
	TextField(
		modifier = Modifier
			.fillMaxWidth(),
		value = latestCaption.ifBlank { caption },
		onValueChange = {
			onValueChange(it)
		},
		shape = RoundedCornerShape(8.dp),
		colors = TextFieldDefaults.colors(
			focusedIndicatorColor = Color.Transparent,
			unfocusedIndicatorColor = Color.Transparent
		)
	)
}