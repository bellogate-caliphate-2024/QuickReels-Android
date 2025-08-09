package com.example.interstitialads

import android.util.Log
import com.google.android.gms.ads.interstitial.InterstitialAd

internal object Cache {
	
	private val adsCache: MutableList<InterstitialAd> = mutableListOf()
	
	fun getAd(): InterstitialAd? {
		Log.i("JEFF", "Size: ${adsCache.size}")
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