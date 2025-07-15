package com.bellogatecaliphate.core.ui.content.sections

//import coil3.compose.AsyncImage
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.bellogatecaliphate.core.util.PLACEHOLDER_8DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_IMAGE_40DP

@Composable
internal fun UserDetailsSection(
	userEmail: String,
	userProfilePicture: String,
	userName: String,
	date: String,
	onOpenAccountDetails: (accountUserEmail: String) -> Unit
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.background(Color.White)
			.fillMaxWidth()
			.clip(RoundedCornerShape(40.dp))
			.padding(PLACEHOLDER_8DP)
			.clickable { onOpenAccountDetails(userEmail) }
	) {
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
}