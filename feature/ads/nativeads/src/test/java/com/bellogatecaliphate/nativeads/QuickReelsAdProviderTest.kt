package com.bellogatecaliphate.nativeads

import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.nativead.NativeAd
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class QuickReelsAdProviderTest {
	
	private val testScheduler = TestCoroutineScheduler()
	
	@Test
	fun `test that ads can be loaded manually and automatically when cache is running low`() =
			runTest(testScheduler) {
				val dispatcher = StandardTestDispatcher(testScheduler)
				val adLoader = FakeQuickReelsNativeAdLoader(mockk(), mockk())
				val adProvider = QuickReelsAdProvider(dispatcher, adLoader)
				
				adProvider.loadAds()
				Assert.assertEquals(8, adProvider.getCacheSize())
				
				adProvider.getNextAd()
				Assert.assertEquals(7, adProvider.getCacheSize())
				adProvider.getNextAd()
				Assert.assertEquals(6, adProvider.getCacheSize())
				adProvider.getNextAd()
				Assert.assertEquals(5, adProvider.getCacheSize())
				adProvider.getNextAd()
				Assert.assertEquals(4, adProvider.getCacheSize())
				adProvider.getNextAd()
				Assert.assertEquals(3, adProvider.getCacheSize())
				adProvider.getNextAd()
				// verify that more ads have been loaded
				Assert.assertEquals(8, adProvider.getCacheSize())
			}
}

private open class FakeQuickReelsNativeAdLoader(
	request: AdRequest,
	builder: AdLoader.Builder,
) : QuickReelsNativeAdLoader(request, builder) {
	
	override fun loadAd(onAdLoaded: (NativeAd) -> Unit, onAdFailedToLoad: () -> Unit) {
		onAdLoaded(mockk<NativeAd>())
	}
}