package io.github.dalinaum.buildtimer

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class BuildTimerPluginTest {

    companion object {
        const val PLUGIN = "io.github.dalinaum.buildtimer"
    }

    @Test
    fun applyPlugin() {
        val project = ProjectBuilder.builder().build()
        project.apply(mapOf("plugin" to PLUGIN))
        assert(project.pluginManager.hasPlugin(PLUGIN))
    }

//    @Test
//    fun
}