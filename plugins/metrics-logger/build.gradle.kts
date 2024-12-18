import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    `java-library`
    jacoco
    alias(libs.plugins.semver)
    alias(libs.plugins.shadow.jar)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))

    compileOnly(libs.keycloak.core)
    compileOnly(libs.keycloak.server.spi)
    compileOnly(libs.keycloak.server.spi.private)
    compileOnly(libs.keycloak.services)

    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.junit.jupiter)
    testImplementation(libs.junit)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

configurations {
    configurations.testImplementation.get().apply {
        extendsFrom(configurations.compileOnly.get())
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.named<Jar>("jar") {
    enabled = false
}

tasks.named<DefaultTask>("build") {
    dependsOn(tasks.shadowJar)
}

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)
    reports {
        xml.required = true
    }
}

tasks.named<JacocoCoverageVerification>("jacocoTestCoverageVerification") {
    violationRules {
        rule {
            limit {
                minimum = "0.9".toBigDecimal()
            }
        }
    }
}

tasks.withType<ShadowJar> {
    archiveClassifier = ""
    archiveVersion = getVersion()
}

tasks.register("version") {
    group = "1up"
    description = "Prints the current version of this extension"
    doLast {
        print(getVersion())
    }
}

fun getVersion(): String {
    return "v${semver.version}"
}
