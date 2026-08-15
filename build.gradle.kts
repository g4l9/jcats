plugins {
    id("java")
    alias(libs.plugins.jmh)
}

group = "com.github.g4l9.jcats"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    jmh(libs.bundles.jmh)

    testImplementation(platform("org.junit:junit-bom:6.0.0"))

    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

jmh {
    warmupIterations = 5
    iterations = 5
    fork = 1
    profilers = listOf("perfasm")
}