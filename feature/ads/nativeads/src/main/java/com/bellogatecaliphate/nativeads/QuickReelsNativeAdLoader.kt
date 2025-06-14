package com.bellogatecaliphate.nativeads

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class QuickReelsNativeAdLoader @Inject constructor(
	private val adLoaderBuilder: AdLoader.Builder,
	private val adRequest: AdRequest
) {
	
	internal suspend fun loadAd(): NativeAd? = suspendCoroutine { continuation ->
		adLoaderBuilder
			.forNativeAd { ad: NativeAd ->
				continuation.resume(ad)
			}
			.withAdListener(object : AdListener() {
				override fun onAdFailedToLoad(adError: LoadAdError) {
					continuation.resume(null)
				}
			})
			.withNativeAdOptions(NativeAdOptions.Builder().build())
		val adLoader = adLoaderBuilder.build()
		adLoader.loadAd(adRequest)
	}
}