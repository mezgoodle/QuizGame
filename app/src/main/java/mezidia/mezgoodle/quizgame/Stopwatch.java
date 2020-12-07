package mezidia.mezgoodle.quizgame;

public class Stopwatch {
    public long GetTime() {
        return System.nanoTime() / 1000000000;
    }
}
