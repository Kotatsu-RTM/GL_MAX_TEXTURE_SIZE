import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0"
}

group = "dev.siro256.gl_max_texture_size"
version = "1.0.0-SNAPSHOT"

repositories {
    maven { url = uri("https://repo.siro256.dev/repository/maven-public/") }
}

dependencies {
    api(kotlin("test"))

    implementation("org.lwjgl:lwjgl:3.3.1")
    implementation("org.lwjgl:lwjgl-assimp:3.3.1")
    implementation("org.lwjgl:lwjgl-glfw3.3.1")
    implementation("org.lwjgl:lwjgl-openal:3.3.1")
    implementation("org.lwjgl:lwjgl-opengl:3.3.1")
    implementation("org.lwjgl:lwjgl-stb:3.3.1")

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
