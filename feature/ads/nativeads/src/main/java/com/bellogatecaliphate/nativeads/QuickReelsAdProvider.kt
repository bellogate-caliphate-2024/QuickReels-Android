package com.bellogatecaliphate.nativeads

import com.google.android.gms.ads.nativead.NativeAd
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val NUMBER_OF_ADS_TO_LOAD_PER_REQUEST = 8
const val THRESHOLD_FOR_LOADING_MORE_ADS = 2

class QuickReelsAdProvider @Inject constructor(
	private val ioDispatcher: CoroutineDispatcher,
	private val quickReelsNativeAdLoader: QuickReelsNativeAdLoader
) {
	
	private val adsCache: MutableList<NativeAd?> = mutableListOf()
	
	suspend fun loadAds() = withContext(ioDispatcher) {
		val list = coroutineScope {
			val deferredList = (1 .. NUMBER_OF_ADS_TO_LOAD_PER_REQUEST).map {
				async { quickReelsNativeAdLoader.loadAd() }
			}
			deferredList.awaitAll()
		}
		adsCache.addAll(list)
	}
	
	suspend fun getNextAd(): NativeAd? {
		if (checkIfToLoadMoreAds()) {
			loadAds()
		}
		
		val latestAd = adsCache.first()
		adsCache.removeAt(0)
		return latestAd
	}
	
	private fun checkIfToLoadMoreAds(): Boolean {
		val adsCacheIsEmpty = adsCache.isEmpty()
		val thresholdForLoadingMoreAdsReached = adsCache.size == THRESHOLD_FOR_LOADING_MORE_ADS
		return (adsCacheIsEmpty || thresholdForLoadingMoreAdsReached)
	}
}