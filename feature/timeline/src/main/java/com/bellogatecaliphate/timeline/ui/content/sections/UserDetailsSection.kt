package com.bellogatecaliphate.timeline.ui.content.sections

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
import androidx.compose.ui.res.painterResource
import com.bellogatecaliphate.timeline.R
import com.bellogatecaliphate.timeline.util.PLACEHOLDER_8DP
import com.bellogatecaliphate.timeline.util.PLACEHOLDER_IMAGE_40DP

@Composable
internal fun UserDetailsSection(userProfilePicture: String, userName: String, date: String) {
	Column(
		Modifier
			.fillMaxWidth()
			.padding(PLACEHOLDER_8DP)
	) {
		Row {
			/*
			SINCE WE ARE STILL CODING, WE WILL COMMENT OUT AsyncImage, AND REPLACE IT WITH Image.
			LATER, WE WILL FINISH REMOVE THE Image.
			AsyncImage(
				model = "https://example.com/image.jpg",
				contentDescription = null,
				modifier = Modifier
					.size(PLACEHOLDER_IMAGE_40DP)
					.clip(CircleShape)
			)*/
			Image(
				painter = painterResource(id = R.drawable.placeholder_image),
				contentDescription = "content description",
				modifier = Modifier
					.size(PLACEHOLDER_IMAGE_40DP)
					.clip(CircleShape)
			)
			Spacer(modifier = Modifier.width(PLACEHOLDER_8DP))
			Column {
				Text(text = userName)
				Text(text = date)
			}
		}
	}
}