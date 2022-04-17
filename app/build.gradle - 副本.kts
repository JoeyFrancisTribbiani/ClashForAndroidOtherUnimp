plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.application")
}
//必须配置
var mfph = json([
        //宿主包名
        "apk.applicationId" : "com.github.kr328.clash",
])
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
