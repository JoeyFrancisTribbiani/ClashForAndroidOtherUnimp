plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.application")
}
android {
    signingConfigs {
        create("release") {
            storeFile = file("D:\\Android\\alias_release.jks")
            storePassword = "123123"
            keyAlias = "release"
            keyPassword = "123123"
        }
    }
    defaultConfig {
        targetSdkVersion(30)   //最优26 2.8.11开始支持29~30
        ndk {
            abiFilters += listOf("armeabi-v7a", "x86", "arm64-v8a")
        }
        manifestPlaceholders["apk.applicationId"] = "com.github.joey.speednet"
    }
//此处配置必须添加 否则无法正确运行
    aaptOptions {
        additionalParameters("--auto-add-overlay")
        //noCompress 'foo', 'bar'
        ignoreAssetsPattern = "!.svn:!.git:.*:!CVS:!thumbs.db:!picasa.ini:!*.scc:*~"
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
        }
    }

}

dependencies {
    compileOnly(project(":hideapi"))

    implementation(project(":core"))
    implementation(project(":service"))
    implementation(project(":design"))
    implementation(project(":common"))
//
//    //导入SDK相关依赖jar、aar
    implementation(fileTree("libs"))
//    //必须添加的依赖
    implementation("com.android.support:recyclerview-v7:28.0.0")
    implementation("com.android.support:support-v4:28.0.0")
    implementation("com.android.support:appcompat-v7:28.0.0")
    implementation("com.alibaba:fastjson:1.1.46.android")
    implementation("com.facebook.fresco:fresco:1.13.0")
    implementation("com.facebook.fresco:animated-gif:1.13.0")
    implementation("com.github.bumptech.glide:glide:4.9.0")


    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.13.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")

    implementation(deps.kotlin.coroutine)
    implementation(deps.androidx.core)
    implementation(deps.androidx.activity)
    implementation(deps.androidx.fragment)
    implementation(deps.androidx.appcompat)
    implementation(deps.androidx.coordinator)
    implementation(deps.androidx.recyclerview)
    implementation(deps.google.material)

    val premiumImplementation by configurations

    premiumImplementation(deps.appcenter.analytics)
    premiumImplementation(deps.appcenter.crashes)
}

tasks.getByName("clean", type = Delete::class) {
    delete(file("release"))
}
