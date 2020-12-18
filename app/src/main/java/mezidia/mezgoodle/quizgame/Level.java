package mezidia.mezgoodle.quizgame;

import android.app.Dialog;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level extends AppCompatActivity {
    protected Dialog dialog;
    protected Dialog dialogEnd;
    protected int numLeft;
    protected int numRight;
    protected Array array = new Array();
    protected Random random = new Random();
    protected Stopwatch stopwatch = new Stopwatch();
    protected int count = 0;
    protected final static int full_points = 20;
}
