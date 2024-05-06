plugins {
    kotlin("jvm") version "1.9.23"
    id("maven-publish")
}

// versions
val adventure = "4.16.0"
//

group = "sh.lumin.loom"
version = "1.0."

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-api:$adventure")
}

tasks {
    test {
        useJUnitPlatform()
    }

    javadoc { options.encoding = Charsets.UTF_8.name() }

    compileKotlin { kotlinOptions.jvmTarget = "17" }

}
kotlin {
    jvmToolchain(17)
}
