package com.bellogatecaliphate.account.ui.screens.profile_detail.composables

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP
import com.bellogatecaliphate.core.util.PLACEHOLDER_32DP
import com.image.cropview.CropType
import com.image.cropview.EdgeType
import com.image.cropview.ImageCrop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ImageCropper(
	show: Boolean,
	image: Bitmap?,
	onConfirmationGiven: (croppedImage: Bitmap) -> Unit,
	onDismiss: () -> Unit
) {
	if (show.not() || image == null) return
	val context = LocalContext.current
	val imageCrop = ImageCrop(image)
	
	BasicAlertDialog(
		onDismissRequest = {
			onDismiss()
		}
	) {
		Column(
			modifier = Modifier
				.padding(vertical = PLACEHOLDER_16DP)
				.clip(RoundedCornerShape(PLACEHOLDER_32DP))
				.fillMaxWidth(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			imageCrop.ImageCropView(
				modifier = Modifier.weight(1f),
				guideLineColor = colorResource(com.bellogatecaliphate.core.R.color.light_ash),
				guideLineWidth = 2.dp,
				edgeCircleSize = 5.dp,
				showGuideLines = true,
				cropType = CropType.PROFILE_CIRCLE,
				edgeType = EdgeType.CIRCULAR
			)
			
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.background(Color.White)
					.fillMaxWidth()
			) {
				val message = stringResource(R.string.image_cropped)
				Box(
					modifier = Modifier
						.padding(vertical = PLACEHOLDER_32DP)
						.clip(RoundedCornerShape(40.dp))
						.background(Color.LightGray)
						.clickable {
							val croppedImage = imageCrop.onCrop()
							onConfirmationGiven(croppedImage)
							Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
						}
				) {
					Image(
						painter = painterResource(R.drawable.crop),
						contentDescription = "",
						modifier = Modifier
							.padding(PLACEHOLDER_16DP)
					)
				}
			}
		}
	}
}