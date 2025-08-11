plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
}

android {
    namespace = "app.dll.test"
    compileSdk = 35

    defaultConfig {
        applicationId = "app.dll.test"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin {
        jvmToolchain(21)
    }

    buildFeatures {
        viewBinding = true
    }

    buildToolsVersion = "35.0.0"

    packaging {
        resources {
            pickFirsts += "/META-INF/DEPENDENCIES"
        }
    }
}

dependencies {
    implementation("androidx.annotation:annotation:1.9.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.2")
    implementation("com.google.firebase:firebase-inappmessaging:22.0.0")
    implementation("androidx.preference:preference-ktx:1.2.1")
    implementation("androidx.core:core-splashscreen:1.0.1")

    val navVersion = "2.9.3"

    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")
    androidTestImplementation("androidx.navigation:navigation-testing:$navVersion")
    implementation("androidx.navigation:navigation-compose:$navVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    implementation("androidx.activity:activity-ktx:1.10.1")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")

    implementation(platform("com.google.firebase:firebase-bom:34.1.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")

    implementation("androidx.credentials:credentials:1.5.0")
    implementation("com.google.android.gms:play-services-auth:21.4.0")
    implementation("com.google.android.gms:play-services-location:21.3.0")

    implementation("com.github.bumptech.glide:glide:4.14.2")
    ksp("com.github.bumptech.glide:ksp:4.14.2")

    implementation("org.apache.pdfbox:pdfbox:2.0.31")
    implementation("org.apache.pdfbox:fontbox:2.0.31")

    implementation("com.kosherjava:zmanim:2.5.0")

    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.recyclerview:recyclerview:1.4.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

//    implementation("com.github.DImuthuUpe:AndroidPdfViewer:3.1.0-beta.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("androidx.room:room-runtime:2.7.2")
    ksp("androidx.room:room-compiler:2.7.2")

    implementation("com.google.dagger:hilt-android:2.57")
    kapt("com.google.dagger:hilt-compiler:2.57")

    implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")
    kapt("androidx.hilt:hilt-compiler:1.2.0")

    implementation("com.squareup:javapoet:1.13.0")
}

kapt {
    correctErrorTypes = true
}
