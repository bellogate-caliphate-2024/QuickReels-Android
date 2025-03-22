package com.bellogatecaliphate.bannerads

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.bellogatecaliphate.bannerads.databinding.QuickReelsBannerAdViewBinding
import com.bellogatecaliphate.core.model.ads.Ads
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun QuickReelsBannerAd(modifier: Modifier, bannerAdType: Ads.BannerAds) {
	val context = LocalContext.current
	val adView = AdView(context)
	adView.adUnitId = getBannerAdUnitId(bannerAdType)
	
	AndroidViewBinding(QuickReelsBannerAdViewBinding::inflate, modifier) {
		adViewContainer.removeAllViews()
		adViewContainer.addView(adView)
		val adRequest = AdRequest.Builder().build()
		adView.setAdSize(
			AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, 360)
		)
		adView.loadAd(adRequest)
	}
}

private fun getBannerAdUnitId(bannerAdType: Ads.BannerAds): String {
	return if (bannerAdType is Ads.BannerAds.CommentSectionBannerAd) {
		BuildConfig.AD_UNIT_COMMENTS_BANNER_AD
	} else {
		throw Exception("You have no created an adunit for the comments banner ad of this flavour")
	}
}