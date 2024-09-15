package com.bellogatecaliphate.create_post.ui.preview_post

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PreviewPost() {
    Column {
        VideoPreview()
        VideoDescriptionSection()
    }
}

@Composable
private fun VideoPreview() {
    Text(text = "Preview of button")
}


@Composable
private fun VideoDescriptionSection() {

}

@Preview
@Composable
private fun PreviewPostPreview() {
    PreviewPost()
}
