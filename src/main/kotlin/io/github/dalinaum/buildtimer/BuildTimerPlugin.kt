package io.github.dalinaum.buildtimer

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.tasks.TaskState
import org.gradle.internal.time.Time

class BuildTimerPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            gradle.addListener(TaskTimerListener())
        }
    }

    class TaskTimerListener : TaskExecutionListener {
        private val timers: MutableMap<Task, TaskTimer> = mutableMapOf()

        override fun beforeExecute(task: Task) {
            timers[task] = TaskTimer()
        }

        override fun afterExecute(task: Task, state: TaskState) {
            val timer = timers[task]
            timer?.let {
                it.complete()
                println("$task took ${it.ellipsedTime}...")
            }
        }
    }

    class TaskTimer {
        private val timer = Time.startTimer()
        private var _ellapsedTime = -1L
        private var _isCompleted = false

        val hasToReport get() = (!_isCompleted) && (ellipsedTime > 100)
        val isCompleted get() = _isCompleted
        val ellipsedTime get() = _ellapsedTime

        fun complete() {
            _ellapsedTime = timer.elapsedMillis
            _isCompleted = true
        }
    }
}