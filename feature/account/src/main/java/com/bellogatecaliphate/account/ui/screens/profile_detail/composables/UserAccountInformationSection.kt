package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.core.model.dto.User
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun UserAccountInformationSection(
	show: Boolean,
	user: User?,
) {
	if (show.not()) return
	Column(
		modifier = Modifier.fillMaxWidth()
	) {
		Text(
			modifier = Modifier
				.fillMaxWidth()
				.padding(PLACEHOLDER_16DP),
			text = user?.email ?: "",
			softWrap = true,
			color = Color.Gray
		)
		Spacer(modifier = Modifier.height(PLACEHOLDER_16DP))
		TextField(
			modifier = Modifier
				.fillMaxWidth()
				.padding(PLACEHOLDER_16DP),
			value = user?.accountName ?: "",
			onValueChange = {},
			shape = RoundedCornerShape(8.dp),
			colors = TextFieldDefaults.colors(
				focusedIndicatorColor = Color.Transparent,
				unfocusedIndicatorColor = Color.Transparent
			)
		)
	}
}