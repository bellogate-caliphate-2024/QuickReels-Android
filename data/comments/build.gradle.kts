plugins {
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.hilt)
	alias(libs.plugins.serialization)
	id("kotlin-kapt")
}

android {
	namespace = "com.bellogatecaliphate.comments"
	compileSdk = 34
	
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
	
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

dependencies {
	
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	// paging
	implementation(libs.androidx.paging.runtime)
	testImplementation(libs.androidx.paging.common)
	implementation(libs.androidx.paging.compose)
	// hilt
	implementation(libs.hilt)
	kapt(libs.hilt.compiler)
	// retrofit
	implementation(libs.retrofit)
	implementation(libs.retrofit.converter.gson)
	implementation(libs.okhttp)
	implementation(libs.okhttp.logging.interceptor)
	implementation(libs.okhttp.converter.scalars)
}