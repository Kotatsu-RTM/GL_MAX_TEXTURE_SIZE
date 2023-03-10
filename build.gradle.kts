import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0"
}

group = "dev.siro256.gl_max_texture_size"
version = "1.0.0"

repositories {
    maven { url = uri("https://repo.siro256.dev/repository/maven-public/") }
}

dependencies {
    api(kotlin("test"))

    api("org.lwjgl:lwjgl:3.3.1")
    api("org.lwjgl:lwjgl-assimp:3.3.1")
    api("org.lwjgl:lwjgl-glfw:3.3.1")
    api("org.lwjgl:lwjgl-openal:3.3.1")
    api("org.lwjgl:lwjgl-opengl:3.3.1")
    api("org.lwjgl:lwjgl-stb:3.3.1")

    runtimeOnly("org.lwjgl:lwjgl:3.3.1:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-assimp:3.3.1:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-glfw:3.3.1:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-openal:3.3.1:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-opengl:3.3.1:natives-windows")
    runtimeOnly("org.lwjgl:lwjgl-stb:3.3.1:natives-windows")
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
}

tasks {
    processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        from(file("LICENSE")) {
            rename { "LICENSE_${project.name}" }
        }
        from(file("README.md")) {
            rename { "README_${project.name}.md" }
        }
    }

    jar {
        mapOf(
            "Main-Class" to "dev.siro256.gl_max_texture_size.GlMaxTextureSize"
        ).let { manifest.attributes(it) }
    }

    withType<KotlinCompile> {
        kotlinOptions.apply {
            jvmTarget = "1.8"
            allWarningsAsErrors = true
        }
    }

    withType<Jar> {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE

        from(
            configurations.api.get().copy()
                .apply { isCanBeResolved = true }
                .map { if (it.isDirectory) it else zipTree(it) }
        )

        from(
            configurations.runtimeOnly.get().copy()
                .apply { isCanBeResolved = true }
                .map { if (it.isDirectory) it else zipTree(it) }
        )
    }
}
