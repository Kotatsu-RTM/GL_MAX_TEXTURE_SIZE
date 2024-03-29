package dev.siro256.gl_max_texture_size

import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL32

object GlMaxTextureSize {
    @JvmStatic
    fun main(args: Array<String>) {
        GLFWErrorCallback.createPrint(System.err).set()
        if (!GLFW.glfwInit()) throw RuntimeException("Unable to initialize GLFW")

        GLFW.glfwDefaultWindowHints()
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE)
        val window = GLFW.glfwCreateWindow(1, 1, "GL_MAX_TEXTURE_SIZE", 0L, 0L)

        GLFW.glfwMakeContextCurrent(window)
        GL.createCapabilities()

        println("""
            OpenGL Version: ${GL32.glGetInteger(GL32.GL_MAJOR_VERSION)}.${GL32.glGetInteger(GL32.GL_MINOR_VERSION)}
            Vendor: ${GL32.glGetString(GL32.GL_VENDOR)}
            Version: ${GL32.glGetString(GL32.GL_VERSION)}
            Max texture size: ${GL32.glGetInteger(GL32.GL_MAX_TEXTURE_SIZE)}
        """.trimIndent())
    }
}
