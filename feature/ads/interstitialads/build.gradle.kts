plugins {
	kotlin("kapt")
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.hilt)
	alias(libs.plugins.compose.compiler)
}

android {
	namespace = "com.example.interstitialads"
	compileSdk = 35
	
	defaultConfig {
		minSdk = 28
		
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}
	
	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	
	buildFeatures {
		buildConfig = true
	}
	
	flavorDimensions += listOf("environment")
	productFlavors {
		create("production") {
			dimension = "environment"
			buildConfigField(
				"String",
				"AD_UNIT_DOWNLOAD_BUTTON_INTERSTITIAL_AD",
				"\"ca-app-pub-7286744545990292/2672671811\""
			)
		}
		
		create("staging") {
			dimension = "environment"
			buildConfigField(
				"String",
				"AD_UNIT_DOWNLOAD_BUTTON_INTERSTITIAL_AD",
				"\"ca-app-pub-3940256099942544/1033173712\""
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = "11"
	}
}

dependencies {
	
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.runtime.android)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	// Google Ads sdk
	implementation(libs.play.services.ads)
	implementation(libs.play.services.ads)
	implementation(libs.androidx.constraintlayout)
	implementation(libs.error.prone.annotations)
	// hilt
	implementation(libs.hilt)
	kapt(libs.hilt.compiler)
}