package io.github.dalinaum.buildtimer

import org.gradle.internal.time.Time
import org.gradle.internal.time.Timer

class TaskTimer(private val threshold: Long = 50) {

    private var timer: Timer? = Time.startTimer()
    private var _elapsedTime = -1L

    val hasToReport get() = isCompleted && (elapsedTime > threshold)
    val isCompleted get() = timer == null

    val elapsedTime: Long
        get() {
            timer?.let {
                _elapsedTime = it.elapsedMillis
            }
            return _elapsedTime
        }

    fun complete() {
        if (isCompleted) {
            throw IllegalStateException("It completed already.")
        }
        timer?.let {
            _elapsedTime = it.elapsedMillis
            timer = null
        }
    }
}