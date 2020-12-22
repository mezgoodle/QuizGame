package mezidia.mezgoodle.quizgame

import android.annotation.SuppressLint

class Stopwatch {
    private val nanoseconds = 1000000000
    private val seconds = 60

    // Get current time
    fun getTime(): Long {
        return System.nanoTime() / nanoseconds
    }

    // Get final time
    @SuppressLint("DefaultLocale")
    fun getResult(time: Long): String {
        val minutes = (time / seconds).toInt()
        val seconds = (time - seconds * minutes).toInt()
        return if (minutes == 0) {
            String.format("Your time is %d seconds", time)
        } else String.format("Your time is %d minute(s) and %d seconds.", minutes, seconds)
    }
}