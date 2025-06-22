package com.bellogatecaliphate.quickreels.ui.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.bellogatecaliphate.core.model.routes.Route
import com.bellogatecaliphate.quickreels.R
import com.bellogatecaliphate.quickreels.model.Screen
import com.bellogatecaliphate.quickreels.util.getXAxisToSlideTo
import kotlin.math.roundToInt

private val menuItems = listOf(
	Screen.Timeline,
	Screen.CreatePost,
	Screen.Account
)

@Preview(showBackground = true)
@Composable
internal fun BottomAppBar(
	modifier: Modifier = Modifier,
	currentUserProfilePictureUrl: String? = null,
	items: List<Screen> = menuItems,
	onMenuItemClicked: (Route) -> Unit = {}
) {
	
	val screenWidth = LocalConfiguration.current.screenWidthDp
	val sliderWidth = (screenWidth / menuItems.size).dp
	var xAxisPointOnScreenToSlideTo by remember { mutableIntStateOf(0) }
	var indexOfCurrentSelectedMenuItem by remember { mutableIntStateOf(0) }
	
	Column(modifier.background(Color.White)) {
		Slider(sliderWidth, xAxisPointOnScreenToSlideTo)
		Spacer(modifier = Modifier.height(12.dp))
		Row(
			horizontalArrangement = Arrangement.Absolute.SpaceAround,
			modifier = Modifier.fillMaxWidth()
		) {
			repeat(menuItems.size) {
				MenuItem(
					isSelected = menuItems[it].route == menuItems[indexOfCurrentSelectedMenuItem].route,
					selectedImageId = menuItems[it].selectedImageId,
					unSelectedImageId = menuItems[it].unSelectedImageId,
					title = menuItems[it].title,
					currentUserProfilePictureUrl = currentUserProfilePictureUrl
				) {
					indexOfCurrentSelectedMenuItem = it
					xAxisPointOnScreenToSlideTo =
							getXAxisToSlideTo(sliderWidth.value.toInt(), screenWidth, it)
					onMenuItemClicked(menuItems[it].route)
				}
			}
		}
		Spacer(modifier = Modifier.height(16.dp))
	}
}

@Preview(showBackground = true)
@Composable
private fun Slider(sliderWidth: Dp = 10.dp, destinationXAxis: Int = 0) {
	
	val destinationAlongTheXAxisToMoveTo = with(LocalDensity.current) {
		destinationXAxis.dp.toPx().roundToInt()
	}
	
	val offset by animateIntOffsetAsState(
		targetValue = IntOffset(destinationAlongTheXAxisToMoveTo, 0),
		label = "offset"
	)
	
	Box(
		modifier = Modifier
			.offset { offset }
			.background(colorResource(id = com.bellogatecaliphate.core.R.color.quickreels_purple))
			.size(sliderWidth, 4.dp)
			.clickable(
				interactionSource = remember { MutableInteractionSource() },
				indication = null
			) {}
	)
}

@Preview(showBackground = true)
@Composable
private fun MenuItem(
	isSelected: Boolean = false,
	@DrawableRes selectedImageId: Int = R.drawable.code,
	@DrawableRes unSelectedImageId: Int = R.drawable.code,
	@StringRes title: Int = R.string.home,
	currentUserProfilePictureUrl: String? = null,
	onClick: () -> Unit = {}
) {
	val context = LocalContext.current
	val selectedColor = colorResource(id = com.bellogatecaliphate.core.R.color.quickreels_purple)
	val unselectedColor = colorResource(id = R.color.default_ash)
	val isAccountMenuItem = title == R.string.account
	val imageId = if (isSelected) selectedImageId else unSelectedImageId
	
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier.clickable {
			onClick()
		}
	) {
		if (isAccountMenuItem) {
			val profilePicture = if (currentUserProfilePictureUrl.isNullOrEmpty()) {
				com.bellogatecaliphate.core.R.drawable.google
			} else currentUserProfilePictureUrl
			AsyncImage(
				modifier = Modifier
					.size(24.dp)
					.clip(CircleShape),
				model = ImageRequest.Builder(context)
					.data(profilePicture)
					.crossfade(2_000) // enables fade animation
					.build(),
				contentDescription = "Profile Image",
				contentScale = ContentScale.Crop,
			)
			Spacer(modifier = Modifier.height(1.dp))
		} else {
			Image(
				painter = painterResource(id = imageId),
				contentDescription = ""
			)
			Spacer(modifier = Modifier.height(4.dp))
		}
		Text(
			modifier = Modifier.testTag(stringResource(id = title)),
			color = if (isSelected) selectedColor else unselectedColor,
			fontSize = 12.sp,
			text = stringResource(id = title)
		)
	}
}