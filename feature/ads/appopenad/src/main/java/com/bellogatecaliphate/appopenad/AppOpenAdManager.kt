package com.bellogatecaliphate.appopenad

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import java.util.Date

private const val AD_UNIT_ID = "ca-app-pub-3940256099942544/9257395921"

class AppOpenAdManager {
	
	private var appOpenAd: AppOpenAd? = null
	private var isLoadingAd = false
	private var isShowingAd = false
	
	// Keep track of the time an app open ad is loaded to ensure you don't show an expired ad.
	private var loadTime: Long = 0
	
	fun showAdIfAvailable(activity: Activity, onShowAdComplete: () -> Unit) {
		if (isShowingAd) {
			return
		}
		
		if (isAdAvailable().not()) {
			onShowAdComplete()
			loadAd(activity)
			return
		}
		
		appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
			
			override fun onAdDismissedFullScreenContent() {
				appOpenAd = null
				isShowingAd = false
				onShowAdComplete()
				loadAd(activity)
			}
			
			override fun onAdFailedToShowFullScreenContent(adError: AdError) {
				appOpenAd = null
				isShowingAd = false
				onShowAdComplete()
				loadAd(activity)
			}
			
			override fun onAdShowedFullScreenContent() {}
		}
		isShowingAd = true
		appOpenAd?.show(activity)
	}
	
	private fun loadAd(context: Context) {
		if (isLoadingAd || isAdAvailable()) {
			return
		}
		
		isLoadingAd = true
		val request = AdRequest.Builder().build()
		AppOpenAd.load(
			context,
			AD_UNIT_ID,
			request,
			object : AppOpenAd.AppOpenAdLoadCallback() {
				
				override fun onAdLoaded(ad: AppOpenAd) {
					appOpenAd = ad
					isLoadingAd = false
				}
				
				override fun onAdFailedToLoad(loadAdError: LoadAdError) {
					isLoadingAd = false
				}
			})
	}
	
	private fun isAdAvailable(): Boolean {
		return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)
	}
	
	// Utility method to check if ad was loaded more than n hours ago.
	private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
		val dateDifference: Long = Date().time - loadTime
		val numMilliSecondsPerHour: Long = 3600000
		return dateDifference < numMilliSecondsPerHour * numHours
	}
}