import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.0.20"
}

group = "org.example"

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(files("../build/libs/lamm-1.0.0.jar"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.7.3")
    implementation("io.ktor:ktor-client-core:3.0.2")
    implementation("io.ktor:ktor-client-cio:3.0.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.2")
    implementation("io.ktor:ktor-client-content-negotiation:3.0.2")
}