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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bellogatecaliphate.core.R
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_IMAGE_40DP

@Composable
internal fun UserDetailsSection(
	contentBelongsToLoggedInUser: Boolean,
	userEmail: String,
	userProfilePicture: String,
	userName: String,
	date: String,
	onOpenAccountDetails: (accountUserEmail: String) -> Unit
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
		DeleteContentButton(visible = contentBelongsToLoggedInUser)
	}
}

@Composable
private fun DeleteContentButton(
	visible: Boolean
) {
	if (visible.not()) return
	Image(
		painterResource(R.drawable.outline_delete),
		contentDescription = "",
		modifier = Modifier
			.clip(RoundedCornerShape(40.dp))
			.clickable(onClick = {
			
			}),
	)
}
