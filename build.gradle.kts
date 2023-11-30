import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.20"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1") {
        because("Many questions need some serialization from / to Json.")
    }
    implementation("com.github.shiguruikai:combinatoricskt:1.6.0") {
        because("Combinatorics are often very useful.")
    }
    implementation("commons-codec:commons-codec:1.16.0") {
        because("Need at the very least digestutil for md5 shenanigans")
    }
    testApi("org.junit.jupiter:junit-jupiter-engine:5.9.2") {
        because("Necessary to run tests")
    }
    testApi("org.junit.jupiter:junit-jupiter-params:5.9.2") {
        because("Necessary to run tests parameterized")
    }
    testImplementation("org.assertj:assertj-core:3.24.2") {
        because("AssertJ is nice to make assertions with in tests")
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    test {
        useJUnitPlatform()
    }
}