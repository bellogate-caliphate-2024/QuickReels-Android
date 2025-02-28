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
	compileSdk = 35
	
	defaultConfig {
		minSdk = 28
		
		testInstrumentationRunner = "com.bellogatecaliphate.chat.runner.CustomTestRunner"
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
	// hilt test
	kaptTest(libs.hilt.compiler)
	androidTestImplementation(libs.hilt.android.testing)
	kaptAndroidTest(libs.hilt.compiler)
	// viewmodel compose
	implementation(libs.viewmodel.compose)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.permission)
	implementation(libs.gson)
	// navigation graph
	implementation(libs.navigation.graph)
	
	implementation(libs.glide)
	annotationProcessor(libs.compiler)
	implementation("com.akexorcist:localization:1.2.9")
	implementation("com.google.code.gson:gson:2.9.0")
	implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
	implementation(libs.androidx.activity.ktx)
	implementation(libs.kotlinx.serialization.json)
	// Paging:
	implementation(libs.androidx.paging.compose.android)
	// Retrofit
	androidTestImplementation(libs.retrofit)
	androidTestImplementation(libs.retrofit.converter.gson)
	androidTestImplementation(libs.okhttp)
	androidTestImplementation(libs.okhttp.logging.interceptor)
	androidTestImplementation(libs.okhttp.converter.scalars)
	// mockWebServer
	androidTestImplementation(libs.mockwebserver)
}