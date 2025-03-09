package com.bellogatecaliphate.nativeads

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.bellogatecaliphate.nativeads.nativeadstemplates.databinding.QuickReelsNativeAdViewBinding

@Composable
fun QuickReelsNativeAd(modifier: Modifier, adLoader: QuickReelsNativeAdLoader) {
	AndroidViewBinding(QuickReelsNativeAdViewBinding::inflate, modifier) {
		adLoader.loadAd(
			{ nativeAd ->
				nativeAdTemplate.setNativeAd(nativeAd)
			}, {
				// do nothing on error
			}
		)
	}
}