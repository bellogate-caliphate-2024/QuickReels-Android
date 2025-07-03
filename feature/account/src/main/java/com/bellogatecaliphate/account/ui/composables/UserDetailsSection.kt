package com.bellogatecaliphate.account.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.core.model.dto.User
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_IMAGE_40DP

@Composable
internal fun UserDetailsSection(
	user: User,
	showBackButton: Boolean,
	onOpenProfileDetails: (userEmail: String) -> Unit,
	onBackPressed: () -> Unit
) {
	Column(
		Modifier
			.clickable { onOpenProfileDetails(user.email) }
			.fillMaxWidth()
			.padding(PLACEHOLDER_8DP)
	) {
		Row(verticalAlignment = Alignment.CenterVertically) {
			BackButton(showBackButton, onBackPressed)
			AsyncImage(
				model = user.profilePictureUrl,
				contentDescription = "content description",
				modifier = Modifier
					.size(PLACEHOLDER_IMAGE_40DP)
					.clip(CircleShape)
			)
			Spacer(modifier = Modifier.width(PLACEHOLDER_8DP))
			Column {
				Text(text = user.accountName)
				Row(verticalAlignment = Alignment.CenterVertically) {
					Text(
						style = MaterialTheme.typography.bodySmall,
						text = "followers ${user.numberOfFollowers}",
						color = colorResource(id = R.color.ash)
					)
					Spacer(Modifier.width(PLACEHOLDER_8DP))
					Circle(color = colorResource(id = R.color.ash))
					Spacer(Modifier.width(PLACEHOLDER_8DP))
					Text(
						style = MaterialTheme.typography.bodySmall,
						text = "following ${user.numberOfFollowing}",
						color = colorResource(id = R.color.ash)
					)
				}
			}
		}
	}
}

@Composable
private fun BackButton(show: Boolean, onBackPressed: () -> Unit) {
	if (show.not()) return
	Image(
		painterResource(R.drawable.back_arrow),
		contentDescription = "",
		modifier = Modifier
			.clickable(onClick = onBackPressed),
	)
	Spacer(modifier = Modifier.width(PLACEHOLDER_16DP))
}

@Composable
fun Circle(
	size: Dp = 8.dp,
	color: Color
) {
	Box(
		modifier = Modifier
			.size(size)
			.background(color, shape = CircleShape)
	)
}