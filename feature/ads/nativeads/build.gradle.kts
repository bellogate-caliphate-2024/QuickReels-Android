plugins {
	kotlin("kapt")
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.hilt)
	alias(libs.plugins.compose.compiler)
}

android {
	namespace = "com.bellogatecaliphate.nativeads"
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
			buildConfigField(
				"String",
				"AD_UNIT_TIMELINE_NATIVE_AD",
				"\"ca-app-pub-xxxxxxxxxxxxxxxx/xxxxxxxxxx\""
			)
		}
		debug {
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
			buildConfigField(
				"String",
				"AD_UNIT_TIMELINE_NATIVE_AD",
				"\"ca-app-pub-3940256099942544/2247696110\""
			)
		}
	}
	buildFeatures {
		buildConfig = true
		compose = true
		viewBinding = true
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
	
	api(project(":feature:ads:nativeads:nativeadstemplates"))
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.runtime.android)
	implementation(libs.androidx.ui.android)
	implementation(libs.androidx.ui.viewbinding)
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