package com.bellogatecaliphate.core.model.ads

sealed class Ads {
	
	open class BannerAds : Ads() {
		data object CommentSectionBannerAd : BannerAds()
	}
}