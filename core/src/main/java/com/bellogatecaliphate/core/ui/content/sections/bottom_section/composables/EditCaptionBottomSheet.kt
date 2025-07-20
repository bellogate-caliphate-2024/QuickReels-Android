package com.bellogatecaliphate.core.ui.content.sections.bottom_section.composables

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_24DP
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EditCaptionBottomSheet(
	show: Boolean,
	caption: String,
	onConfirmationGiven: (newCaption: String) -> Unit,
	onDismiss: () -> Unit,
) {
	if (show.not()) return
	val sheetState = rememberModalBottomSheetState()
	val scope = rememberCoroutineScope()
	var showBottomSheet by remember { mutableStateOf(false) }
	var latestCaption by remember { mutableStateOf("") }
	
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
			TextField(
				modifier = Modifier
					.fillMaxWidth(),
				value = latestCaption.ifBlank { caption },
				onValueChange = {
					latestCaption = it
				},
				shape = RoundedCornerShape(8.dp),
				colors = TextFieldDefaults.colors(
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent
				)
			)
			Spacer(modifier = Modifier.height(PLACEHOLDER_24DP))
			TextButton(
				onClick = {
					scope.launch { sheetState.hide() }.invokeOnCompletion {
						if (! sheetState.isVisible) {
							showBottomSheet = false
						}
					}
					onConfirmationGiven(latestCaption)
				}, modifier = Modifier.fillMaxWidth()
			) {
				Text(
					stringResource(R.string.save_edit),
					color = colorResource(R.color.quickreels_purple),
					fontWeight = FontWeight.Bold
				)
			}
		}
	}
}