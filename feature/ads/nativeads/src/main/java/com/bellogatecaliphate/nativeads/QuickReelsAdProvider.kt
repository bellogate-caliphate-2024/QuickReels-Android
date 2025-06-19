package com.bellogatecaliphate.nativeads

import androidx.annotation.VisibleForTesting
import com.google.android.gms.ads.nativead.NativeAd
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * MAXIMUM_NUMBER_OF_ADS_TO_KEEP_IN_CACHE is the maximum number of ads that can be cached in the adsCache.
 * When we load more ads, we make sure that no matter how many ads we load, we don't go beyond the 'MAXIMUM_NUMBER_OF_ADS_TO_KEEP_IN_CACHE' ads in the adsCache.
 * THRESHOLD_FOR_LOADING_MORE_ADS is the smallest number of ads that need to be cached before loading more ads.
 * */
const val MAXIMUM_NUMBER_OF_ADS_TO_KEEP_IN_CACHE = 8
const val THRESHOLD_FOR_LOADING_MORE_ADS = 2

class QuickReelsAdProvider @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher,
	private val quickReelsNativeAdLoader: QuickReelsNativeAdLoader
) {
	
	private val adsCache: MutableList<NativeAd> = mutableListOf()
	private var isLoadingAds = false
	
	suspend fun loadAds() = withContext(ioDispatcher) {
		if (checkIfTosStopLoadingAds()) return@withContext
		isLoadingAds = true
		
		val numberOfIterations = (MAXIMUM_NUMBER_OF_ADS_TO_KEEP_IN_CACHE - adsCache.size)
		val deferredList = (1 .. numberOfIterations).map {
			async {
				quickReelsNativeAdLoader.loadAd({
					adsCache.add(it)
					checkIfTosStopLoadingAds()
				}, {
					checkIfTosStopLoadingAds()
				})
			}
		}
		deferredList.awaitAll()
	}
	
	suspend fun getNextAd(): NativeAd? {
		if (adsCache.isEmpty()) return null
		val latestAd = adsCache.first()
		adsCache.removeAt(0)
		
		if (checkIfToLoadMoreAds()) {
			loadAds()
		}
		
		return latestAd
	}
	
	private fun checkIfToLoadMoreAds(): Boolean {
		val adsCacheIsEmpty = adsCache.isEmpty()
		val thresholdForLoadingMoreAdsReached = adsCache.size == THRESHOLD_FOR_LOADING_MORE_ADS
		return (adsCacheIsEmpty || thresholdForLoadingMoreAdsReached)
	}
	
	private fun checkIfTosStopLoadingAds(): Boolean {
		return isLoadingAds && adsCache.size >= MAXIMUM_NUMBER_OF_ADS_TO_KEEP_IN_CACHE
	}
	
	@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
	fun getCacheSize() = adsCache.size
}