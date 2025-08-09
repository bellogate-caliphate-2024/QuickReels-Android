package com.example.interstitialads

import android.content.Context
import android.util.Log
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
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

private const val MAXIMUM_NUMBER_OF_ADS_TO_LOAD = 8

internal class AdLoader {
	
	private val scope = CoroutineScope(Dispatchers.Main + Job()) // Use Dispatchers.Main because
	
	// InterstitialAd.load must run on UI thread
	private var activeLoadJob: Job? = null
	
	fun loadAds(context: Context, onAdLoaded: (InterstitialAd) -> Unit) {
		if (activeLoadJob?.isActive == true) {
			Log.i("JEFF", "AdLoader: Already loading ads, skipping new request.")
			return
		}
		
		activeLoadJob = scope.launch {
			val ads = (1 .. MAXIMUM_NUMBER_OF_ADS_TO_LOAD).map {
				async {
					loadSuspend(context)
				}
			}.awaitAll()
			
			ads.filterNotNull().forEach { ad ->
				onAdLoaded(ad)
			}
			activeLoadJob = null
		}
	}
	
	private suspend fun loadSuspend(
		context: Context
	): InterstitialAd? = suspendCancellableCoroutine { cont ->
		InterstitialAd.load(
			context,
			BuildConfig.AD_UNIT_DOWNLOAD_BUTTON_INTERSTITIAL_AD,
			AdRequest.Builder().build(),
			object : InterstitialAdLoadCallback() {
				
				override fun onAdLoaded(ad: InterstitialAd) {
					Log.i("JEFF", "successfully loaded ad!!")
					if (cont.isActive) cont.resume(ad)
				}
				
				override fun onAdFailedToLoad(adError: LoadAdError) {
					Log.i("JEFF", "failed to load ad")
					if (cont.isActive) cont.resume(null)
				}
			}
		)
	}
}