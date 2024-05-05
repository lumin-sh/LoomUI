plugins {
    kotlin("jvm") version "1.9.23"
    id("maven-publish")
}

// versions
val adventure = "4.16.0"
val adventureBukkit = "4.3.2"
//

group = "sh.lumin.loom"
version = "1.0.1"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        content {
            includeGroup("org.bukkit")
            includeGroup("org.spigotmc")
        }
    }
    maven("https://jitpack.io")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(kotlin("test"))

    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")

    implementation("net.kyori:adventure-api:$adventure")
    implementation("net.kyori:adventure-platform-bukkit:$adventureBukkit")
}

tasks {
    test {
        useJUnitPlatform()
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }

    javadoc { options.encoding = Charsets.UTF_8.name() }

    compileKotlin { kotlinOptions.jvmTarget = "17" }

}
kotlin {
    jvmToolchain(17)
}

publishing {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = group.toString()
                artifactId = "loomui"
                version = version.toString()

                from(components["java"])
            }
        }
    }
}