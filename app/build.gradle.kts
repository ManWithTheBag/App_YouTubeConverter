plugins {
    id("com.android.application")
}

android {
    namespace = "org.doit.youtubeconverterapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.doit.youtubeconverterapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 3
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }



    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildToolsVersion = "34.0.0"
}



dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.fasterxml.jackson.core:jackson-core:2.16.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("regexp:regexp:1.3")
    compileOnly("org.projectlombok:lombok:1.18.30")
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    implementation ("com.google.android.gms:play-services-ads:22.6.0")
}