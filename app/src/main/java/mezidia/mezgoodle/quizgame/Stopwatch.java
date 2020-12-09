package mezidia.mezgoodle.quizgame;

import android.annotation.SuppressLint;

public class Stopwatch {
    final int nanoseconds = 1000000000;
    final int seconds = 60;

    public long GetTime() {
        return System.nanoTime() / this.nanoseconds;
    }

    @SuppressLint("DefaultLocale")
    public String GetResult(long time) {
        int minutes = (int) (time / this.seconds);
        int seconds = (int) (time - this.seconds * minutes);
        if (minutes == 0) {
            return String.format("Your time is %d seconds", time);
        } else return String.format("Your time is %d minute(s) and %d seconds.", minutes, seconds);
    }
}
