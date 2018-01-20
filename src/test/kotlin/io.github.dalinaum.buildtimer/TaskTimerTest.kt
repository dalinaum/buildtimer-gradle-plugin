package io.github.dalinaum.buildtimer

import org.junit.Test

class TaskTimerTest {

    @Test
    fun started() {
        val timer = TaskTimer()
        assert(!timer.isCompleted)
        assert(timer.elapsedTime > 0L)
        assert(!timer.hasToReport)
    }

    @Test
    fun longTask() {
        val timer = TaskTimer()
        assert(!timer.isCompleted)
        Thread.sleep(200L)
        assert(timer.elapsedTime > 100L)
        assert(timer.hasToReport)
    }

    @Test
    fun complete() {
        val timer = TaskTimer()
        assert(!timer.isCompleted)
        timer.complete()
        val elapsedTime = timer.elapsedTime
        assert(elapsedTime > 0L)
        assert(!timer.hasToReport)
        assert(timer.isCompleted)
        Thread.sleep(100L)
        assert(timer.elapsedTime == elapsedTime)
    }

    @Test(expected = IllegalStateException::class)
    fun complete_duplicated() {
        val timer = TaskTimer()
        assert(!timer.isCompleted)
        timer.complete()
        timer.complete()
    }
}