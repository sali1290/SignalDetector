import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.signaldetector"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.signaldetector"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "API_KEY", "\"${properties["API_KEY"]}\"")

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
    val coreVersion = "1.12.0"
    val lifecycleVersion = "2.7.0"
    val activityComposeVersion = "1.8.2"
    val junitVersion = "4.13.2"
    implementation("androidx.core:core-ktx:$coreVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")
    implementation(platform("androidx.compose:compose-bom:2024.04.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.04.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
// Di with Koin
dependencies {
    val koinVersion = "3.2.3"
    val koinComposeVersion = "3.2.2"
    val lifecycleViewModeVersion = "2.7.0"
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("io.insert-koin:koin-androidx-compose:$koinComposeVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleViewModeVersion")
}
// Remote datasource
dependencies {
    val retrofitVersion = "2.9.0"
    val moshiVersion = "1.14.0"
    val okhttpVersion = "3.14.9"
    val lifecycleExtensionVersion = "2.2.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleExtensionVersion")
}