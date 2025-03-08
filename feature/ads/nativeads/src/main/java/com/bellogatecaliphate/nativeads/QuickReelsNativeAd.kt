package com.bellogatecaliphate.nativeads

import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions

class QuickReelsNativeAd(private val context: Context, private val onAdLoaded: (NativeAd) -> Unit) {
	
	fun x() {
		val adLoader = AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
			.forNativeAd { ad: NativeAd ->
				onAdLoaded(ad)
			}
			.withAdListener(object : AdListener() {
				override fun onAdFailedToLoad(adError: LoadAdError) {
					val x = adError
					val y = adError
				}
			})
			.withNativeAdOptions(
				NativeAdOptions.Builder()
					.build()
			)
			.build()
		adLoader.loadAd(AdRequest.Builder().build())
	}
}