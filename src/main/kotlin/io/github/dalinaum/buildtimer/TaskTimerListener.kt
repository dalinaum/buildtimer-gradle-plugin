package io.github.dalinaum.buildtimer

import org.gradle.BuildListener
import org.gradle.BuildResult
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.api.tasks.TaskState

class TaskTimerListener : TaskExecutionListener, BuildListener {

    private val timerMap = mutableMapOf<Task, TaskTimer>()
    private val timerList = mutableListOf<Task>()

    override fun beforeExecute(task: Task) {
        timerMap[task] = TaskTimer()
        timerList.add(task)
    }

    override fun afterExecute(task: Task, state: TaskState) {
        val timer = timerMap[task]
        timer?.let {
            it.complete()
            println("$task took ${it.elapsedTime}...")
        }
    }

    override fun settingsEvaluated(settings: Settings?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun buildFinished(result: BuildResult?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun projectsLoaded(gradle: Gradle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun buildStarted(gradle: Gradle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun projectsEvaluated(gradle: Gradle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}