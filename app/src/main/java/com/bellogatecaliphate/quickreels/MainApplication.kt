package com.bellogatecaliphate.quickreels

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.hilt.work.HiltWorkerFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.work.Configuration
import com.bellogatecaliphate.appopenad.AppOpenAdManager
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application(), Configuration.Provider,
                        Application.ActivityLifecycleCallbacks,
                        LifecycleObserver {
	
	private lateinit var appOpenAdManager: AppOpenAdManager
	private var currentActivity: Activity? = null
	
	@Inject
	lateinit var workerFactory: HiltWorkerFactory
	
	override val workManagerConfiguration: Configuration
		get() = Configuration.Builder()
			.setWorkerFactory(workerFactory)
			.build()
	
	override fun onCreate() {
		super.onCreate()
		registerActivityLifecycleCallbacks(this)
		ProcessLifecycleOwner.get().lifecycle.addObserver(this)
		setUpAds(this)
	}
	
	@OnLifecycleEvent(Lifecycle.Event.ON_START)
	fun onMoveToForeground() {
		// Show the ad (if available) when the app moves to foreground.
		currentActivity?.let {
			appOpenAdManager.showAdIfAvailable(it) {}
		}
	}
	
	private fun setUpAds(context: Context) {
		val backgroundScope = CoroutineScope(Dispatchers.IO)
		backgroundScope.launch { MobileAds.initialize(context) {} }
		appOpenAdManager = AppOpenAdManager()
	}
	
	private fun showAd() {
		currentActivity?.let {
			appOpenAdManager.showAdIfAvailable(it) {}
		}
	}
	
	override fun onActivityStarted(activity: Activity) {
		currentActivity = activity
	}
	
	override fun onActivityCreated(activity: Activity, p1: Bundle?) {}
	
	override fun onActivityResumed(activity: Activity) {}
	
	override fun onActivityPaused(activity: Activity) {}
	
	override fun onActivityStopped(activity: Activity) {}
	
	override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {}
	
	override fun onActivityDestroyed(activity: Activity) {}
}