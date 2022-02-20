@file:Suppress("UNUSED_VARIABLE")

plugins {
    kotlin("multiplatform") version "1.6.10"
}

group = "me.nullicorn"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }

    js(BOTH) {
        nodejs()
        browser()
    }

    sourceSets {
        val commonMain by getting {}

        val jvmMain by getting {
            dependencies {
                // Used internally for JSON serializing & deserializing.
                implementation("com.google.code.gson:gson:2.8.9")
            }
        }

        val jsMain by getting {
            dependencies {
                // Used internally to send requests to Microsoft/Minecraft services.
                implementation(npm("sync-fetch", "^0.3.1"))
            }
        }
    }
}
