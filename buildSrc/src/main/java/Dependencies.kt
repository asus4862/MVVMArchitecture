const val kotlinVersion = "1.3.50"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "3.5.3"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
}

object AndroidSdk {
    const val compileSdk = 28
    const val minSdk = 21
    const val targetSdk = compileSdk

    const val versionCode = 1
    const val versionName = "1.0.0"

}

object Kotlin {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val test = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"
    const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val allopen = "org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion"
}

object Support {
    private object Versions {
        const val compat = "1.1.0"
        const val material = "1.0.0"
        const val constraintLayout = "1.1.2"
        const val ktx = "1.1.0-alpha05"
    }
    const val annotations = "androidx.annotation:annotation:${Versions.compat}"
    const val app_compat = "androidx.appcompat:appcompat:${Versions.compat}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.compat}"
    const val design = "com.google.android.material:material:${Versions.material}"
    const val v4 = "androidx.legacy:legacy-support-v4:${Versions.material}"
    const val core_utils = "androidx.legacy:legacy-support-core-utils:${Versions.compat}"

    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val ktx_core = "androidx.core:core-ktx:${Versions.ktx}"
}

object Libraries {
    private object Versions {
        const val okhttp_logging_interceptor = "3.9.0"

        const val paging = "2.1.0"
    }
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"

    const val paging_ktx = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
}

object Koin {
    private object Versions {
        const val koin = "1.0.2"
    }
    const val core = "org.koin:koin-core:${Versions.koin}"
    const val android = "org.koin:koin-android:${Versions.koin}"
    const val architecture = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val test = "org.koin:koin-test:${Versions.koin}"
}


object Room {
    private object Versions {
        const val room = "2.2.2"
    }
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val rxjava2 = "androidx.room:room-rxjava2:${Versions.room}"
    const val testing = "androidx.room:room-testing:${Versions.room}"
}


object Lifecycle {
    private object Versions {
        const val lifecycle = "2.1.0"
    }

    const val runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val java8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    // optional - Test helpers for LiveData
    const val arch_core = "androidx.arch.core:core-testing:${Versions.lifecycle}"
}

object Retrofit {
    private object Versions {
        const val retrofit = "2.3.0"
    }
    const val runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val rx_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
}

object Rx {
    private object Versions {
        const val rxjava2 = "2.1.3"
        const val rx_android = "2.0.1"
    }
    const val rxjava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxjava2}"
    const val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android}"
}


object Glide {
    private object Versions {
        const val glide = "4.11.0"
    }
    const val runtime = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}


object TestLibraries {
    private object Versions {
        const val junit4 = "4.12"

        const val test_core = "1.2.0"

        const val ext_junit = "1.1.1"
        const val ext_truth = "1.2.0"
        const val google_truth = "0.42"


        const val axt_runner = "1.2.0"
        const val axt_rules = "1.2.0"
        const val espresso = "3.2.0"



        const val mockk_version = "1.9.3"
    }

    const val junit = "junit:junit:${Versions.junit4}"

    const val test_core = "androidx.test:core:${Versions.test_core}"

    const val ext_junit =  "androidx.test.ext:junit:${Versions.ext_junit}"
    const val ext_truth =  "androidx.test.ext:truth:${Versions.ext_truth}"
    const val google_truth =  "com.google.truth:truth:${Versions.google_truth}"


    const val axt_runner = "androidx.test:runner:${Versions.axt_runner}"
    const val axt_rules = "androidx.test:rules:${Versions.axt_rules}"

    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val espresso_intents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"


    const val mockk = "io.mockk:mockk:${Versions.mockk_version}"

}