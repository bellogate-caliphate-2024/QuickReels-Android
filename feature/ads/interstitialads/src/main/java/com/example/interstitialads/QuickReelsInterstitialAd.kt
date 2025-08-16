package com.example.interstitialads

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback

object QuickReelsInterstitialAd {
	
	private val adsCache = Cache
	private var adLoader = AdLoader()
	
	fun loadAds(context: Context) {
		Log.i("JEFF", "attempting to load ads")
		adLoader.loadAds(context) { adsCache.cacheAd(it) }
	}
	
	fun showAdOrNot(
		show: Boolean,
		context: Context,
		onAdIsNotReadyTobeShownOrHasBeenSkipped: (() -> Unit),
		onAdDismissed: (() -> Unit)
	) {
		Log.i("JEFF", "attempting to show an ad $show")
		if (show.not()) {
			onAdDismissed()
			return
		}
		
		val latestAd = adsCache.getAd()
		// calling adsCache.getAd() will remove the ad from the cache. So make sure to call it only
		// when necessary.
		when {
			latestAd == null         -> {
				onAdIsNotReadyTobeShownOrHasBeenSkipped()
				loadAds(context)
				return
			}
			
			adsCache.shouldLoadAds() -> {
				loadAds(context)
			}
		}
		
		latestAd?.fullScreenContentCallback =
				object : FullScreenContentCallback() {
					override fun onAdDismissedFullScreenContent() {
						// Called when fullscreen content is dismissed.
						// Don't forget to set the ad reference to null so you
						// don't show the ad a second time.
						onAdDismissed()
					}
					
					override fun onAdFailedToShowFullScreenContent(adError: AdError) {
						// Called when fullscreen content failed to show.
						// Don't forget to set the ad reference to null so you
						// don't show the ad a second time.
					}
					
					override fun onAdShowedFullScreenContent() {
						// Called when fullscreen content is shown.
					}
					
					override fun onAdImpression() {
						// Called when an impression is recorded for an ad.
					}
					
					override fun onAdClicked() {
						// Called when ad is clicked.
					}
				}
		latestAd?.show(context as Activity)
	}
}