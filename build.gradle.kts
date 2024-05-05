plugins {
    kotlin("jvm") version "1.9.23"
    id("io.github.goooler.shadow") version "8.1.7"
    id("io.papermc.paperweight.userdev") version "1.5.13"
    id("xyz.jpenilla.run-paper") version "2.2.2"
}

// versions
val adventure = "4.16.0"
val adventureBukkit = "4.3.2"
//

group = "sh.lumin.loom"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(kotlin("test"))

    paperweight.paperDevBundle("1.20.4-R0.1-SNAPSHOT")

    implementation("net.kyori:adventure-api:$adventure")
    implementation("net.kyori:adventure-platform-bukkit:$adventureBukkit")
}

tasks {
    test {
        useJUnitPlatform()
    }

    runServer { minecraftVersion("1.20.4") }
    compileKotlin { kotlinOptions.jvmTarget = "17" }

}
kotlin {
    jvmToolchain(17)
}