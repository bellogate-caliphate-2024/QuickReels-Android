package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.core.model.dto.User
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun UserAccountInformationSection(
	show: Boolean,
	user: User?,
) {
	if (show.not()) return
	Column(
		verticalArrangement = Arrangement.SpaceBetween,
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		var newAccountName: String? by remember { mutableStateOf(null) }
		Column {
			Text(
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = PLACEHOLDER_16DP),
				text = user?.email ?: "",
				softWrap = true,
				color = Color.Gray
			)
			TextField(
				modifier = Modifier
					.fillMaxWidth()
					.padding(PLACEHOLDER_16DP),
				value = newAccountName ?: user?.accountName ?: "",
				onValueChange = {
					newAccountName = it
				},
				label = { Text("Account Name") },
				shape = RoundedCornerShape(8.dp),
				colors = TextFieldDefaults.colors(
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent
				)
			)
			val showSaveButton = newAccountName != null && newAccountName != user?.accountName
			SaveButton(show = showSaveButton, onClick = {})
		}
		Text(
			fontWeight = FontWeight.Bold,
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = PLACEHOLDER_16DP)
				.clickable { },
			text = stringResource(R.string.logout),
			softWrap = true,
			color = Color.Red,
			textAlign = TextAlign.Center,
		)
	}
}

@Composable
private fun SaveButton(show: Boolean, onClick: () -> Unit) {
	AnimatedVisibility(
		show,
		enter = fadeIn(),
		exit = fadeOut()
	) {
		Text(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = PLACEHOLDER_16DP)
				.clickable { onClick() },
			text = stringResource(R.string.save),
			softWrap = true,
			color = colorResource(com.bellogatecaliphate.core.R.color.quickreels_purple),
			textAlign = TextAlign.Center,
		)
	}
}
