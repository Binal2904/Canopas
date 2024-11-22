package com.task.binal

class Temp {
//    https://codeshare.io/XLd1Ln
}

[versions]
agp = "8.5.2"
kotlin = "2.0.20"
coreKtx = "1.15.0"
junit = "4.13.2"
junitVersion = "1.2.1"
espressoCore = "3.6.1"
appcompat = "1.7.0"
material = "1.12.0"
activity = "1.9.3"
constraintlayout = "2.2.0"
ksp = "1.8.10-1.0.9"
hilt = "2.52"
devtoolsKsp = "2.0.21-1.0.27"
# Coroutines
kotlinxCoroutinesCore = "1.9.0"
kotlinxCoroutinesAndroid = "1.9.0"
#viewmode
viewmodel = "2.5.0"
# Retrofit
retrofit = "2.11.0"
# OkHttp
okhttpBom = "4.12.0"
# Moshi
moshiKotlin = "1.15.1"
gson = "2.9.0"
viewbinding = "8.7.2"
room = "2.6.1"
roomKtx = "2.6.1"
uiDesktop = "1.7.0"

[libraries]
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
junit = { group = "junit", name = "junit", version.ref = "junit" }
androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
androidx-appcompat = { group = "androidx.appcompat", name = "appcompat", version.ref = "appcompat" }
material = { group = "com.google.android.material", name = "material", version.ref = "material" }
androidx-activity = { group = "androidx.activity", name = "activity", version.ref = "activity" }
androidx-constraintlayout = { group = "androidx.constraintlayout", name = "constraintlayout", version.ref = "constraintlayout" }
dagger-hilt = { group = "com.google.dagger", name = "hilt-android", version.ref = "hilt" }
dagger-hilt-compiler = { group = "com.google.dagger", name = "hilt-android-compiler", version.ref = "hilt" }
# Coroutines
kotlinx-coroutines-core = { group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-core", version.ref = "kotlinxCoroutinesCore" }
kotlinx-coroutines-android = { group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-android", version.ref = "kotlinxCoroutinesAndroid" }
#viewmodel
viewmodel-ktx = { group = "androidx.lifecycle", name = "lifecycle-viewmodel-ktx", version.ref = "viewmodel" }
viewmodel-runtime = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "viewmodel" }
viewmodel-extension = { group = "androidx.lifecycle", name = "lifecycle-extensions", version.ref = "viewmodel" }

# Retrofit
retrofit = { group = "com.squareup.retrofit2", name = "retrofit", version.ref = "retrofit" }
# OkHttp
okhttp-bom = { group = "com.squareup.okhttp3", name = "okhttp-bom", version.ref = "okhttpBom" }
okhttp-okhttp = { group = "com.squareup.okhttp3", name = "okhttp" }
okhttp-logging-interceptor = { group = "com.squareup.okhttp3", name = "logging-interceptor" }
# Moshi
moshi-kotlin = { group = "com.squareup.moshi", name = "moshi-kotlin", version.ref = "moshiKotlin" }
converter-moshi = { group = "com.squareup.retrofit2", name = "converter-gson", version.ref = "gson" }
gson = { group = "com.google.code.gson", name = "gson", version.ref = "gson" }
androidx-viewbinding = { group = "androidx.databinding", name = "viewbinding", version.ref = "viewbinding" }

androidx-room-runtime = { module = "androidx.room:room-runtime", version.ref = "room" }
androidx-room-compiler = { module = "androidx.room:room-compiler", version.ref = "room" }
androidx-room-ktx = { module = "androidx.room:room-ktx", version.ref = "room" }

androidx-ui-desktop = { group = "androidx.compose.ui", name = "ui-desktop", version.ref = "uiDesktop" }

[plugins]
android-application = { id = "com.android.application", version.ref = "agp" }
jetbrains-kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
daggerHiltAndroid = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }
devtoolsKsp = { id = "com.google.devtools.ksp", version.ref = "devtoolsKsp" }

