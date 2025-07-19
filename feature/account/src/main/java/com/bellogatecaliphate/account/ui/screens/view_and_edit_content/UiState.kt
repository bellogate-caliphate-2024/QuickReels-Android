package com.bellogatecaliphate.account.ui.screens.view_and_edit_content

import com.bellogatecaliphate.core.model.dto.Content

data class UiState(
	val isLoadingInitialContent: Boolean = false,
	val isRefreshing: Boolean = false,
	val errorLoadingContent: Boolean = false,
	val errorSavingContent: Boolean = false,
	val deleteContentSuccess: Boolean? = null,
	val content: Content? = null,
	val contentBelongsToLoggedInUser: Boolean = false
)