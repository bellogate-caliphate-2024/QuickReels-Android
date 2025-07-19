package com.bellogatecaliphate.core.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_24DP
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmationDialog(
	show: Boolean,
	@StringRes text: Int,
	onConfirmationGiven: () -> Unit,
	onDismiss: () -> Unit
) {
	if (show.not()) return
	val sheetState = rememberModalBottomSheetState()
	val scope = rememberCoroutineScope()
	var showBottomSheet by remember { mutableStateOf(false) }
	
	ModalBottomSheet(
		onDismissRequest = {
			showBottomSheet = false
			onDismiss()
		},
		sheetState = sheetState
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = PLACEHOLDER_16DP),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(stringResource(id = text))
			Spacer(modifier = Modifier.height(PLACEHOLDER_24DP))
			TextButton(onClick = {
				scope.launch { sheetState.hide() }.invokeOnCompletion {
					if (! sheetState.isVisible) {
						showBottomSheet = false
					}
				}
				onConfirmationGiven()
			}, modifier = Modifier.fillMaxWidth()) {
				Text(
					stringResource(R.string.yes),
					color = Color.Black,
					fontWeight = FontWeight.Bold
				)
			}
		}
	}
}