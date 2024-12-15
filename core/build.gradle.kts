plugins {
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.hilt)
	alias(libs.plugins.serialization)
	id("kotlin-kapt")
	alias(libs.plugins.compose.compiler)
}

android {
	namespace = "com.bellogatecaliphate.core"
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
	buildFeatures {
		compose = true
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
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	// serialization
	implementation(libs.kotlinx.serialization.json)
	// Room
	implementation(libs.room.runtime)
	implementation(libs.room.paging)
	annotationProcessor(libs.room.compiler)
	kapt(libs.room.compiler)
	implementation(libs.room.ktx)
	implementation(libs.room.testing)
	// Retrofit
	implementation(libs.retrofit)
	implementation(libs.retrofit.converter.gson)
	implementation(libs.okhttp)
	implementation(libs.okhttp.logging.interceptor)
	implementation(libs.okhttp.converter.scalars)
	// paging
	implementation(libs.androidx.paging.runtime)
	testImplementation(libs.androidx.paging.common)
	implementation(libs.androidx.paging.compose)
	// hilt
	implementation(libs.hilt)
	kapt(libs.hilt.compiler)
}