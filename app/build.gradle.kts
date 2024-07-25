import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
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
        buildConfigField("String", "UNWIREDLAB_API_KEY", "\"${properties["UNWIREDLAB_API_KEY"]}\"")

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
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {
    val coreVersion = "1.13.1"
    val lifecycleVersion = "2.8.3"
    val activityComposeVersion = "1.9.0"
    val junitVersion = "4.13.2"
    implementation("androidx.core:core-ktx:$coreVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:$junitVersion")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
// Di with Hilt
dependencies {
    val hiltVersion = "2.51.1"
    val hiltNavigationVersion = "1.2.0"
    // Di with Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltNavigationVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")
}
// Remote datasource
dependencies {
    val retrofitVersion = "2.11.0"
    val moshiVersion = "1.14.0"
    val okhttpVersion = "4.12.0"
    val lifecycleExtensionVersion = "2.2.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleExtensionVersion")
}

dependencies {
    implementation (files("libs/activation.jar"))
    implementation (files("libs/additionnal.jar"))
    implementation (files("libs/mail.jar"))
}

dependencies {
    val blonghoVersion = "v1.5.4-alpha-1"
    implementation("com.github.blongho:worldCountryData:$blonghoVersion")
}
