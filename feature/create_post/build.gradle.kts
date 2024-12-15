plugins {
	kotlin("kapt")
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.hilt)
	alias(libs.plugins.serialization)
	alias(libs.plugins.compose.compiler)
}

android {
	namespace = "com.bellogatecaliphate.create_post"
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
	
	implementation(project(":core"))
	implementation(project(":domain"))
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
	// hilt
	implementation(libs.hilt)
	kapt(libs.hilt.compiler)
	// hilt navigation compose
	implementation(libs.hilt.navigation.compose)
	// viewmodel compose
	implementation(libs.viewmodel.compose)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.permission)
	implementation(libs.gson)
	// navigation graph
	implementation(libs.navigation.graph)
	
	implementation(libs.google.exoplayer)
	implementation(libs.ffmpeg.kit.min)
	implementation(libs.glide)
	annotationProcessor(libs.compiler)
	implementation(libs.localization)
	implementation(libs.gson.v2101)
	implementation(libs.androidx.coordinatorlayout)
	implementation(libs.androidx.activity.ktx)
	implementation(libs.kotlinx.serialization.json)
	// Exo player:
	implementation(libs.exoplayer)
	implementation(libs.exoplayer.ui)
	implementation(libs.exoplayer.common)
}