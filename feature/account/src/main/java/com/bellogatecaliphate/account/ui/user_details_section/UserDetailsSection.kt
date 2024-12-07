package com.bellogatecaliphate.account.ui.user_details_section

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.core.common.util.PLACEHOLDER_8DP
import com.bellogatecaliphate.core.common.util.PLACEHOLDER_IMAGE_40DP

@Composable
internal fun UserDetailsSection(userProfilePicture: String, userName: String, userEmail: String) {
	Column(Modifier.fillMaxWidth().padding(PLACEHOLDER_8DP)) {
		Row {
			/*AsyncImage(
				model = "https://example.com/image.jpg",
				contentDescription = null,
				modifier = Modifier
					.size(PLACEHOLDER_IMAGE_40DP)
					.clip(CircleShape)
			)*/
			Image(
				painter = painterResource(id = R.drawable.ic_launcher_background),
				contentDescription = "content description",
				modifier = Modifier
					.size(PLACEHOLDER_IMAGE_40DP)
					.clip(CircleShape)
			)
			Spacer(modifier = Modifier.width(PLACEHOLDER_8DP))
			Column {
				Text(text = userName)
				Text(text = userEmail, color = colorResource(id = R.color.ash))
			}
		}
	}
}