package com.bellogatecaliphate.account.ui.screens.view_and_edit_content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.bellogatecaliphate.account.R
import com.bellogatecaliphate.core.util.PLACEHOLDER_16DP

@Composable
internal fun EditContentScreen() {
	val viewModel: ViewAndEditContentScreenViewModel = hiltViewModel()
	//val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
	
	EditContentScreen(onClose = { })
}

@Composable
private fun EditContentScreen(
	onClose: () -> Unit
) {
	Scaffold(
		topBar = {
			Image(
				painterResource(R.drawable.back_arrow),
				contentDescription = "",
				modifier = Modifier
					.clickable(onClick = onClose)
					.padding(PLACEHOLDER_16DP),
			)
		}
	) { innerPadding ->
		Column(
			modifier = Modifier
				.background(color = Color.White)
				.padding(innerPadding)
				.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			Text(text = "Edit Content Screen")
		}
	}
}