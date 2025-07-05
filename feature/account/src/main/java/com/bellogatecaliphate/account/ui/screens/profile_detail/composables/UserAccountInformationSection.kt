package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.account.ui.composables.ConfirmationDialog
import com.bellogatecaliphate.core.model.dto.User
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun UserAccountInformationSection(
	show: Boolean,
	isUpdatingUserAccountName: Boolean,
	user: User?,
	onSaveNewAccountName: (userEmail: String, newUserAccountName: String) -> Unit,
	onLogOut: () -> Unit
) {
	if (show.not()) return
	var showLogoutLoading by remember { mutableStateOf(false) }
	var showLogoutBottomSheet by remember { mutableStateOf(false) }
	var showSaveAccountNameEditBottomSheet by remember { mutableStateOf(false) }
	var newAccountName: String? by remember { mutableStateOf(null) }
	val newValueForUserAccountNameIsAvailable = newAccountName != null &&
	                                            newAccountName != user?.accountName
	val showSaveButton = newValueForUserAccountNameIsAvailable && isUpdatingUserAccountName.not()
	
	Column(
		verticalArrangement = Arrangement.SpaceBetween,
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
				enabled = isUpdatingUserAccountName.not(),
				singleLine = true,
				label = { Text(stringResource(R.string.account_name)) },
				shape = RoundedCornerShape(8.dp),
				colors = TextFieldDefaults.colors(
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent
				)
			)
			SaveButton(
				show = showSaveButton,
				onClick = { showSaveAccountNameEditBottomSheet = true }
			)
			QuickReelsCircularProgressBar(show = isUpdatingUserAccountName)
			ConfirmationDialog(
				show = showSaveAccountNameEditBottomSheet,
				text = R.string.save_account_name,
				onConfirmationGiven = {
					showSaveAccountNameEditBottomSheet = false
					onSaveNewAccountName(user?.email ?: "", newAccountName ?: "")
				},
				onDismiss = { showSaveAccountNameEditBottomSheet = false }
			)
			ConfirmationDialog(
				show = showLogoutBottomSheet,
				text = R.string.logout_message,
				onConfirmationGiven = {
					showLogoutBottomSheet = false
					showLogoutLoading = true
					onLogOut()
				},
				onDismiss = {
					showLogoutBottomSheet = false
					showLogoutLoading = false
				}
			)
		}
		LogOutButton(
			showLogoutLoading = showLogoutLoading,
			onOpenLogOutBottomSheet = { showLogoutBottomSheet = true }
		)
	}
}
