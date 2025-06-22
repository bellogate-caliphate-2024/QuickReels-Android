plugins {
	kotlin("kapt")
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.jetbrainsKotlinAndroid)
	alias(libs.plugins.hilt)
	alias(libs.plugins.serialization)
	alias(libs.plugins.compose.compiler)
	id("com.google.gms.google-services")
	id("com.google.firebase.crashlytics")
}

android {
	signingConfigs {
		create("releaseConfig") {
			storeFile = file("/Users/user/Documents/quickreels_keystore")
			storePassword = "quickreels2468"
			keyAlias = "quickreels"
			keyPassword = "quickreels2468"
		}
	}
	namespace = "com.bellogatecaliphate.quickreels"
	compileSdk = 35
	
	defaultConfig {
		applicationId = "com.bellogatecaliphate.quickreels"
		minSdk = 28
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"
		
		testInstrumentationRunner = "com.bellogatecaliphate.quickreels.CustomTestRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}
	
	buildTypes {
		release {
			isMinifyEnabled = true
			isDebuggable = false
			isShrinkResources = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
			signingConfig = signingConfigs.getByName("releaseConfig")
		}
		
		debug {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	
	flavorDimensions += listOf("environment")
	productFlavors {
		create("production") {
			dimension = "environment"
		}
		
		create("staging") {
			applicationIdSuffix = ".staging"
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
	buildFeatures {
		compose = true
		buildConfig = true
	}
	
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {
	
	implementation(project(":feature:account"))
	implementation(project(":feature:create_post"))
	implementation(project(":feature:timeline"))
	implementation(project(":feature:chat"))
	implementation(project(":feature:ads:appopenad"))
	implementation(project(":domain"))
	implementation(project(":core"))
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.androidx.uiautomator.v18)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
	// AppCompanist
	implementation(libs.accompanist.systemuicontroller)
	// Retrofit
	implementation(libs.retrofit)
	implementation(libs.retrofit.converter.gson)
	implementation(libs.okhttp)
	implementation(libs.okhttp.logging.interceptor)
	implementation(libs.okhttp.converter.scalars)
	// hilt
	implementation(libs.hilt)
	kapt(libs.hilt.compiler)
	kapt(libs.androidx.hilt.compiler)
	implementation(libs.androidx.hilt.work)
	// hilt test
	kaptTest(libs.hilt.compiler)
	androidTestImplementation(libs.hilt.android.testing)
	kaptAndroidTest(libs.hilt.compiler)
	// navigation graph
	implementation(libs.navigation.graph)
	// bottom navigation compose
	implementation(libs.bottom.navigation)
	// serialization
	implementation(libs.kotlinx.serialization.json)
	// Coil
	implementation(libs.coil.compose)
	implementation(libs.coil.network.okhttp)
	// mockWebServer
	androidTestImplementation(libs.mockwebserver)
	// Google Ads sdk
	implementation(libs.play.services.ads)
	// lifecycle process
	implementation(libs.androidx.lifecycle.process)
	// firebase
	implementation(platform(libs.firebase.bom))
	implementation(libs.firebase.analytics)
	implementation(libs.firebase.crashlytics)
	// firebase auth
	googleAuthDependencies()
}

fun DependencyHandler.googleAuthDependencies() {
	// firebase auth
	implementation(libs.firebase.auth)
	// Also add the dependencies for the Credential Manager libraries and specify their versions
	implementation(libs.androidx.credentials)
	implementation(libs.androidx.credentials.play.services.auth)
	implementation(libs.googleid)
}

kapt {
	correctErrorTypes = true
}