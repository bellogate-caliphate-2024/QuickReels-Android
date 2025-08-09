package com.example.interstitialads

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

private const val MAXIMUM_NUMBER_OF_ADS_TO_LOAD = 8

internal class AdLoader {
	
	private val job = Job()
	private val scope = CoroutineScope(Dispatchers.Main + job) // use Main not IO
	
	fun loadAds(context: Context, onAdLoaded: (ad: InterstitialAd) -> Unit) = scope.launch {
		(1 .. MAXIMUM_NUMBER_OF_ADS_TO_LOAD).map {
			async {
				load(context, onAdLoaded)
			}
		}.awaitAll()
	}
	
	private fun load(context: Context, onAdLoaded: (InterstitialAd) -> Unit) {
		InterstitialAd.load(
			context,
			BuildConfig.AD_UNIT_DOWNLOAD_BUTTON_INTERSTITIAL_AD,
			AdRequest.Builder().build(),
			object : InterstitialAdLoadCallback() {
				
				override fun onAdLoaded(ad: InterstitialAd) = onAdLoaded(ad)
				
				override fun onAdFailedToLoad(adError: LoadAdError) {}
			},
		)
	}
}