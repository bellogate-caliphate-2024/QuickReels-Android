package com.bellogatecaliphate.nativeads

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import javax.inject.Inject

class QuickReelsNativeAdLoader @Inject constructor(
	private val adLoaderBuilder: AdLoader.Builder,
	private val adRequest: AdRequest
) {
	
	internal fun loadAd(onAdLoaded: (NativeAd) -> Unit, onAdFailedToLoad: () -> Unit) {
		adLoaderBuilder
			.forNativeAd { ad: NativeAd ->
				onAdLoaded(ad)
			}
			.withAdListener(object : AdListener() {
				override fun onAdFailedToLoad(adError: LoadAdError) {
					onAdFailedToLoad()
				}
			})
			.withNativeAdOptions(NativeAdOptions.Builder().build())
		val adLoader = adLoaderBuilder.build()
		adLoader.loadAd(adRequest)
	}
}