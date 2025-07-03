package com.bellogatecaliphate.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bellogatecaliphate.core.R

val Typography = Typography(
	// bodyLarge: is typically the android default for main body text ie all Text composable on the app.
	// So you don't need to manually add it to your text styles. They automatically refer to it.
	// The bodyLarge style is often used as the default fallback for regular text.
	// Note: Text composables inside a Button does not use this style. They use labelLarge by default.
	bodyLarge = TextStyle(
		fontFamily = FontFamily(Font(R.font.lato_regular)),
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.5.sp
	),
	bodySmall = TextStyle(
		fontFamily = FontFamily(Font(R.font.lato_regular)),
		fontWeight = FontWeight.Normal,
		fontSize = 14.sp,
		lineHeight = 20.sp,
		letterSpacing = 0.25.sp
	),
	// labelLarge: is typically the android default for Text composables inside Buttons.
	labelLarge = TextStyle(
		fontFamily = FontFamily(Font(R.font.lato_regular)),
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.5.sp
	)
)