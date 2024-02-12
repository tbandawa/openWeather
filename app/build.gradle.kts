plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "me.tbandawa.android.openweather"
    compileSdk = 34

    defaultConfig {
        applicationId = "me.tbandawa.android.openweather"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField( "String", "OPEN_WEATHER_ICON_URL", "\"https://openweathermap.org/img/wn/\"")
        buildConfigField("String", "OPEN_WEATHER_ICON_4X", "\"@4x.png\"")
        buildConfigField("String", "OPEN_WEATHER_ICON_2X", "\"@2x.png\"")

        buildConfigField("String", "OPEN_WEATHER_ICONS_SOURCE_URL", "\"https://icons8.com/\"")
        buildConfigField("String", "OPEN_WEATHER_SOURCE_CODE_URL", "\"https://github.com/tbandawa/openWeather\"")
        buildConfigField("String", "OPEN_WEATHER_AUTHER_EMAIL", "\"mailto:tonderaibandawa@gmail.com\"")
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    // Navigation
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Accompanist Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.20.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}