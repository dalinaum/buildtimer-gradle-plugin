package io.github.dalinaum.buildtimer

import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildTimerPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            gradle.addListener(TaskTimerListener())
        }
    }
}