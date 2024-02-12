plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.protobuf")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.android")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.20.1"
    }
    plugins {
        kotlin { }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
            task.plugins {
                kotlin {}
            }
        }
    }
}

android {
    namespace = "openweather.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField( "String", "OPEN_WEATHER_BASE_URL", "\"https://api.openweathermap.org/data/2.5/\"")
        buildConfigField( "String", "OPEN_WEATHER_API_KEY", "\"dfc3d17632993a8fd49021d3c3d20f96\"")
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
    buildFeatures {
        buildConfig = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation("androidx.core:core-ktx:1.12.0")

    // Koin
    implementation("io.insert-koin:koin-androidx-compose:3.2.0")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Ktor
    implementation("io.ktor:ktor-client-android:2.1.2")
    implementation("io.ktor:ktor-client-core:2.1.2")
    implementation("io.ktor:ktor-client-content-negotiation:2.1.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.2")
    implementation("io.ktor:ktor-client-logging:2.1.2")

    // Reflection
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Preferences DataStore & Protobuf
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("com.google.protobuf:protobuf-javalite:3.21.5")
    implementation("com.google.protobuf:protobuf-kotlin-lite:3.21.5")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    testImplementation("junit:junit:4.13.2")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.0")
    testImplementation("android.arch.core:core-testing:1.1.1")
    testImplementation("org.mockito:mockito-core:5.2.0")
    androidTestImplementation("org.mockito:mockito-android:3.12.4")
    androidTestImplementation("android.arch.core:core-testing:1.1.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}