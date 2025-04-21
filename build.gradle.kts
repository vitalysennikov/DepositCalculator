// Top-level build file (для настройки плагинов и зависимостей всего проекта)
buildscript {
    repositories {
        google()  // Репозиторий для Android Gradle Plugin
        mavenCentral()  // Репозиторий для Kotlin и других библиотек
    }

    dependencies {
        // Плагины для сборки Android и Kotlin:
        classpath("com.android.tools.build:gradle:8.2.2")  // Android Gradle Plugin
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20")  // Kotlin Plugin
    }
}

// Для всех подпроектов (модулей)
plugins {
    id("com.android.application") version "8.2.2" apply false  // Для app-модуля
    id("com.android.library") version "8.2.2" apply false  // Для library-модулей
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
}

// Очистка build-директории (опционально)
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
