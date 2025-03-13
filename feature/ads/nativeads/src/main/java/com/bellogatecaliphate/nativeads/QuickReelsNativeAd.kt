package com.bellogatecaliphate.nativeads

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.bellogatecaliphate.nativeads.nativeadstemplates.databinding.QuickReelsNativeAdViewBinding
import com.google.android.gms.ads.nativead.NativeAd

@Composable
fun QuickReelsNativeAd(modifier: Modifier, adLoader: QuickReelsNativeAdLoader) {
	var isAdLoadedSuccessfully by remember { mutableStateOf(false) }
	var cachedLoadedAd: NativeAd? by remember { mutableStateOf(null) }
	
	AndroidViewBinding(QuickReelsNativeAdViewBinding::inflate, modifier) {
		if (isAdLoadedSuccessfully && cachedLoadedAd != null) {
			nativeAdTemplate.setNativeAd(cachedLoadedAd)
		} else {
			adLoader.loadAd(
				{ nativeAd ->
					isAdLoadedSuccessfully = true
					cachedLoadedAd = nativeAd
					nativeAdTemplate.setNativeAd(nativeAd)
				}, {
					// do nothing on error
				}
			)
		}
	}
}