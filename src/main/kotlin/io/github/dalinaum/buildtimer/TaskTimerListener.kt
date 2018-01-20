package io.github.dalinaum.buildtimer

import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.tasks.TaskState

class TaskTimerListener : TaskExecutionListener {

    private val timers: MutableMap<Task, TaskTimer> = mutableMapOf()

    override fun beforeExecute(task: Task) {
        timers[task] = TaskTimer()
    }

    override fun afterExecute(task: Task, state: TaskState) {
        val timer = timers[task]
        timer?.let {
            it.complete()
            println("$task took ${it.elapsedTime}...")
        }
    }
}