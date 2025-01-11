import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  kotlin("jvm") version "2.0.20"
  `maven-publish`
}

group = "io.github.antonpichka"
version = "1.0.2"

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

  val sourcesJar by creating(Jar::class) {
    dependsOn(JavaPlugin.CLASSES_TASK_NAME)
    from(sourceSets["main"].allSource)
    archiveClassifier.set("sources")
  }

  val javadocJar by creating(Jar::class) {
    dependsOn(JavaPlugin.JAVADOC_TASK_NAME)
    from("${layout.buildDirectory}/docs")
    archiveClassifier.set("javadoc")
  }

  build {
    dependsOn(sourcesJar, javadocJar)
  }
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      pom {
        name = "io.github.antonpichka:lamm"
        description = "MVVM Modify for Kotlin but you can also port to another language"
        url = "https://github.com/antonpichka/library_architecture_mvvm_modify_kotlin"
        licenses {
          license {
            name = "The MIT License (MIT)"
            url = "https://mit-license.org/"
          }
        }
        scm {
          connection = "scm:git:git://github.com:antonpichka/library_architecture_mvvm_modify_kotlin.git"
          developerConnection = "scm:git:ssh://github.com:antonpichka/library_architecture_mvvm_modify_kotlin.git"
          url = "https://github.com/antonpichka/library_architecture_mvvm_modify_kotlin"
        }
        developers {
          developer {
            name = "Anton Pichka"
            email = "antonpichka@gmail.com"
            organization = "Github"
            organizationUrl = "https://github.com/antonpichka"
          }
        }
      }
      from(components["java"])
      artifact(tasks["sourcesJar"])
      artifact(tasks["javadocJar"])
    }
  }
  repositories {
    maven {
      url = uri("build/publish")
    }
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
  testImplementation("junit:junit:4.13.2")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
}