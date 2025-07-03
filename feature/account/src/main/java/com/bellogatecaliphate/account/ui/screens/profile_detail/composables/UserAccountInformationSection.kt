package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
		modifier = Modifier.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
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
			value = user?.accountName ?: "",
			onValueChange = {},
			label = { Text("Account Name") },
			shape = RoundedCornerShape(8.dp),
			colors = TextFieldDefaults.colors(
				focusedIndicatorColor = Color.Transparent,
				unfocusedIndicatorColor = Color.Transparent
			)
		)
		Text(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = PLACEHOLDER_16DP),
			text = stringResource(R.string.logout),
			softWrap = true,
			color = Color.Red,
			textAlign = TextAlign.Center,
		)
	}
}