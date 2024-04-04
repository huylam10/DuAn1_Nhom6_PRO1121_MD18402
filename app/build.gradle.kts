plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.nhom6_pro1121_md18402"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nhom6_pro1121_md18402"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(files("libs\\activation.jar"))
    implementation(files("libs\\additionnal.jar"))
    implementation(files("libs\\mail.jar"))

    implementation("androidx.activity:activity:1.8.0")
    testImplementation("junit:junit:4.13.2") // chỉ giữ lại một lần
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("io.github.ParkSangGwon:tedpermission-normal:3.3.0")
    implementation("com.github.bumptech.glide:glide:4.14.2") // chỉ giữ lại một phiên bản
    implementation("me.relex:circleindicator:2.1.6")

    implementation("androidx.navigation:navigation-fragment:2.7.5")
    implementation("androidx.navigation:navigation-ui:2.7.5")
    implementation("com.google.android.material:material:1.11.0")


}