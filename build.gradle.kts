/* Gradle build file for JaCaMo Application */
plugins {
    java
    eclipse
}

version = "1.0"
group = "org.jacamo"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(15))
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.gradle.org/gradle/libs-releases") }
    maven { url = uri("https://raw.githubusercontent.com/jacamo-lang/mvn-repo/master") }
    flatDir { dirs("lib") }
}

dependencies {
    implementation("org.jacamo:jacamo:1.1")
}

sourceSets {
    main {
        java {
            srcDir("src/env")
            srcDir("src/agt")
            srcDir("src/org")
            srcDir("src/int")
            srcDir("src/java")
        }
        resources {
            srcDir("src/resources")
        }
    }
}

tasks.register<JavaExec>("run") {
    dependsOn("classes")
    description = "Runs the application"
    group = "JaCaMo"
    mainClass.set("jacamo.infra.JaCaMoLauncher")
    args = listOf("auction_org.jcm")
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.getByName<Delete>("clean").doFirst {
    delete("bin")
    delete("build")
}
