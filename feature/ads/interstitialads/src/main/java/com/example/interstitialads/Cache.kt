package com.example.interstitialads

import com.google.android.gms.ads.interstitial.InterstitialAd

internal object Cache {
	
	private val adsCache: MutableList<InterstitialAd> = mutableListOf()
	
	fun getAd(): InterstitialAd? {
		if (adsCache.isEmpty()) return null
		val latestAd = adsCache.first()
		adsCache.removeAt(0)
		return latestAd
	}
	
	fun cacheAd(ad: InterstitialAd) {
		adsCache.add(ad)
	}
	
	fun shouldLoadAds(): Boolean = adsCache.size <= 2
}