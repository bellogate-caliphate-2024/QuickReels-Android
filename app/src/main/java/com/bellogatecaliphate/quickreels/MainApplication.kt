package com.bellogatecaliphate.quickreels

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application(), Configuration.Provider {
	
	@Inject
	lateinit var workerFactory: HiltWorkerFactory
	
	override val workManagerConfiguration: Configuration
		get() = Configuration.Builder()
			.setWorkerFactory(workerFactory)
			.build()
	
	override fun onCreate() {
		super.onCreate()
		MobileAds.initialize(this) {}
	}
}