package com.bellogatecaliphate.nativeads

import android.util.Log
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class QuickReelsNativeAdLoader @Inject constructor(
	private val adLoaderBuilder: AdLoader.Builder,
	private val adRequest: AdRequest
) {
	
	internal suspend fun loadAd(): NativeAd? = suspendCancellableCoroutine { continuation ->
		val adListener = object : AdListener() {
			// Note: forNativeAd is the primary callback for successful native ad loads.
			// onAdLoaded in AdListener is more general and might not be strictly needed here
			// if you only care about the NativeAd itself.
			override fun onAdLoaded() {
				super.onAdLoaded()
				// You could log here if needed, but the native ad itself is delivered via forNativeAd
				Log.d("QuickReelsNativeAdLoader", "AdListener.onAdLoaded() called.")
			}
			
			override fun onAdFailedToLoad(adError: LoadAdError) {
				Log.e(
					"QuickReelsNativeAdLoader",
					"Ad failed to load: ${adError.message}, code: ${adError.code}"
				)
				if (continuation.isActive) { // Check if coroutine is still active
					continuation.resume(null)
				}
			}
			
			// You can override other AdListener methods if needed (onAdClicked, onAdOpened, etc.)
			// override fun onAdOpened() { ... }
			// override fun onAdClicked() { ... }
			// override fun onAdClosed() { ... }
			// override fun onAdImpression() { ... }
		}
		
		adLoaderBuilder
			.forNativeAd { nativeAd: NativeAd ->
				Log.d("QuickReelsNativeAdLoader", "Native ad loaded successfully.")
				if (continuation.isActive) { // Check if coroutine is still active
					continuation.resume(nativeAd)
				}
			}
			.withAdListener(adListener) // Use the AdListener for failure and other events
			.withNativeAdOptions(NativeAdOptions.Builder().build()) // Sensible defaults
		
		val adLoader = adLoaderBuilder.build()
		
		// Handle coroutine cancellation
		continuation.invokeOnCancellation {
			Log.e("QuickReelsNativeAdLoader", "Ad loading coroutine was cancelled.")
			// The AdLoader API doesn't provide a direct method to cancel an in-flight ad request.
			// This block ensures that if the coroutine is cancelled, we don't try to resume it.
			// If there were any local resources to clean up specific to this call, you'd do it here.
		}
		
		// Initiate the ad loading process
		Log.d("QuickReelsNativeAdLoader", "Requesting ad load...")
		adLoader.loadAd(adRequest)
	}
}