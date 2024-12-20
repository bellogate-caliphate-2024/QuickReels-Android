plugins {
	kotlin("kapt")
	alias(libs.plugins.androidLibrary)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.hilt)
	alias(libs.plugins.serialization)
	alias(libs.plugins.compose.compiler)
}

android {
	namespace = "com.bellogatecaliphate.account"
	compileSdk = 34
	
	defaultConfig {
		minSdk = 24
		
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
	
	flavorDimensions += listOf("environment")
	productFlavors {
		create("production") {
			dimension = "environment"
		}
		
		create("staging") {
			dimension = "environment"
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
	
	implementation("com.google.android.exoplayer:exoplayer:2.17.1")
	implementation("com.arthenica:ffmpeg-kit-min:6.0-1")
	implementation(libs.glide)
	annotationProcessor(libs.compiler)
	implementation("com.akexorcist:localization:1.2.9")
	implementation("com.google.code.gson:gson:2.9.0")
	implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
	implementation(libs.androidx.activity.ktx)
	implementation(libs.kotlinx.serialization.json)
	// Exo player:
	implementation(libs.exoplayer)
	implementation(libs.exoplayer.ui)
	implementation(libs.exoplayer.common)
	// Paging:
	implementation(libs.androidx.paging.compose.android)
}