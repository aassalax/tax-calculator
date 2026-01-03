plugins {
    kotlin("jvm") version "2.2.20"
}

group = "com.aassalax"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.2")
    testImplementation("com.tngtech.jgiven:jgiven-core:2.0.3")
    testImplementation("com.tngtech.jgiven:jgiven-junit5:2.0.3")
    testImplementation("io.toolisticon.testing:jgiven-kotlin:2.0.2.1")
    testImplementation("org.assertj:assertj-core:3.25.3")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(24)
}