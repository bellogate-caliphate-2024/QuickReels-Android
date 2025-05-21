pluginManagement {
	repositories {
		google {
			content {
				includeGroupByRegex("com\\.android.*")
				includeGroupByRegex("com\\.google.*")
				includeGroupByRegex("androidx.*")
			}
		}
		mavenCentral()
		gradlePluginPortal()
		flatDir {
			dirs(rootDir.resolve("libs"))
		}
	}
	plugins {
		kotlin("jvm") version "2.0.20"
	}
}
plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
	}
}

rootProject.name = "QuickReels"
include(":app")
include(":feature")
include(":feature:create_post")
include(":core")
include(":data")
include(":data:post")
include(":domain")
include(":data:user")
include(":data:user")
include(":feature:timeline")
include(":data:contents")
include(":feature:account")
include(":data:comments")
include(":feature:chat")
include(":feature:ads")
include(":feature:ads:appopenad")
include(":feature:ads:nativeads")
include(":feature:ads:nativeads:nativeadstemplates")
include(":feature:ads:bannerads")
include(":core:authentication")
