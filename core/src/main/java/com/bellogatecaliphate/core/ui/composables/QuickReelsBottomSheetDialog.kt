package com.bellogatecaliphate.core.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_24DP
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickReelsBottomSheetDialog(
	show: Boolean,
	text: Any,
	onConfirmationGiven: (() -> Unit)? = null,
	onDismiss: (() -> Unit)? = null,
) {
	if (show.not()) return
	val sheetState = rememberModalBottomSheetState()
	val scope = rememberCoroutineScope()
	var showBottomSheet by remember { mutableStateOf(false) }
	
	ModalBottomSheet(
		onDismissRequest = {
			showBottomSheet = false
			onDismiss?.invoke()
		},
		sheetState = sheetState
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(PLACEHOLDER_16DP),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = if (text is String) text else stringResource(id = text as Int),
				style = MaterialTheme.typography.bodySmall,
				textAlign = TextAlign.Justify,
			)
			Spacer(modifier = Modifier.height(PLACEHOLDER_24DP))
			if (onConfirmationGiven != null)
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
						color = colorResource(R.color.quickreels_purple),
						fontWeight = FontWeight.Bold
					)
				}
		}
	}
}