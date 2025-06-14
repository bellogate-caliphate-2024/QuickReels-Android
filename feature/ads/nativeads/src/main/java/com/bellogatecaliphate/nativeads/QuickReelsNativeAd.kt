package com.bellogatecaliphate.nativeads

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.bellogatecaliphate.nativeads.nativeadstemplates.databinding.QuickReelsNativeAdViewBinding
import com.google.android.gms.ads.nativead.NativeAd

@Composable
fun QuickReelsNativeAd(
	modifier: Modifier,
	ad: NativeAd?,
) {
	AndroidViewBinding(QuickReelsNativeAdViewBinding::inflate, modifier) {
		if (ad == null) {
			nativeAdTemplate.visibility = View.GONE
		} else {
			nativeAdTemplate.visibility = View.VISIBLE
			nativeAdTemplate.setNativeAd(ad)
		}
	}
}