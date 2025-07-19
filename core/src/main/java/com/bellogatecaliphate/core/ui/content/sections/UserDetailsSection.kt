package com.bellogatecaliphate.core.ui.content.sections

//import coil3.compose.AsyncImage
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.ui.QuickReelsCircularProgressBar
import com.bellogatecaliphate.core.ui.composables.ConfirmationDialog
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_24DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_IMAGE_40DP
import com.bellogatecaliphate.core.util.isFalseOrNull

@Composable
internal fun UserDetailsSection(
	contentBelongsToLoggedInUser: Boolean,
	userEmail: String,
	userProfilePicture: String,
	userName: String,
	date: String,
	deleteContentSuccess: Boolean?,
	onOpenAccountDetails: (accountUserEmail: String) -> Unit,
	onDeleteContent: () -> Unit
) {
	Row(
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.background(Color.White)
			.fillMaxWidth()
			.clip(RoundedCornerShape(40.dp))
			.padding(vertical = PLACEHOLDER_8DP, horizontal = PLACEHOLDER_16DP)
			.clickable { onOpenAccountDetails(userEmail) }
	) {
		Row {
			AsyncImage(
				placeholder = painterResource(R.drawable.placeholder_image),
				model = userProfilePicture,
				contentDescription = null,
				modifier = Modifier
					.size(PLACEHOLDER_IMAGE_40DP)
					.clip(CircleShape)
			)
			Spacer(modifier = Modifier.width(PLACEHOLDER_8DP))
			Column {
				Text(text = userName)
				Text(
					style = MaterialTheme.typography.bodySmall,
					text = date,
					color = Color.LightGray
				)
			}
		}
		DeleteContentButton(
			visible = contentBelongsToLoggedInUser,
			deleteContentSuccess = deleteContentSuccess,
			onDeleteContent = onDeleteContent
		)
	}
}

@Composable
private fun DeleteContentButton(
	visible: Boolean,
	deleteContentSuccess: Boolean?,
	onDeleteContent: () -> Unit
) {
	if (visible.not()) return
	var onDeleteContentClicked by remember { mutableStateOf(false) }
	var onDeleteContentInProgress by remember { mutableStateOf(false) }
	
	if (onDeleteContentInProgress.not() && deleteContentSuccess.isFalseOrNull()) {
		Image(
			painterResource(R.drawable.outline_delete),
			contentDescription = "",
			modifier = Modifier
				.clip(RoundedCornerShape(40.dp))
				.clickable(onClick = {
					onDeleteContentClicked = true
				}),
		)
	}
	QuickReelsCircularProgressBar(show = onDeleteContentInProgress, PLACEHOLDER_24DP)
	ConfirmationDialog(
		show = onDeleteContentClicked,
		text = R.string.delete_this_post,
		onConfirmationGiven = {
			onDeleteContentInProgress = true
			onDeleteContentClicked = false
			onDeleteContent()
		},
		onDismiss = {
			onDeleteContentClicked = false
		}
	)
}
