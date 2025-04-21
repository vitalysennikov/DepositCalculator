plugins {
    id("com.android.application")  // Плагин для приложения
    id("org.jetbrains.kotlin.android")  // Поддержка Kotlin
}

android {
    namespace = "com.example.depositcalculator"  // Пакет приложения
    compileSdk = 34  // Версия SDK для компиляции

    defaultConfig {
        applicationId = "com.example.depositcalculator"  // ID пакета
        minSdk = 21  // Минимальная версия Android
        targetSdk = 34  // Целевая версия Android
        versionCode = 1  // Версия кода
        versionName = "1.0"  // Версия имени
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false  // Отключение обфускации (для debug)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17  // Java 17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"  // Kotlin → JVM 17
    }
}

dependencies {
    // Стандартные зависимости:
    implementation("androidx.core:core-ktx:1.12.0")  // Kotlin-расширения
    implementation("com.google.android.material:material:1.11.0")  // Material Design
    implementation("androidx.appcompat:appcompat:1.6.1")  // Совместимость

    // Тестирование (опционально):
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
}
