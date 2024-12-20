plugins {
  kotlin("jvm") version "2.0.20"
}

group = "org.library_architecture_mvvm_modify_kotlin"
version = "1.0"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
  testImplementation("junit:junit:4.13.2")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
}