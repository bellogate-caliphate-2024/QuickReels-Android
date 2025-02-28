package com.bellogatecaliphate.quickreels

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.hilt.work.HiltWorkerFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
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
                        LifecycleObserver, LifecycleEventObserver {
	
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
	
	override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
		when (event) {
			Lifecycle.Event.ON_START -> {
				showAd()
			}
			
			else                     -> {}
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