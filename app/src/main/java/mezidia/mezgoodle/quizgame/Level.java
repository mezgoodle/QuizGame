package mezidia.mezgoodle.quizgame;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level extends AppCompatActivity {
    // Common fields
    protected Dialog dialog;
    protected Dialog dialogEnd;
    protected int numLeft;
    protected int numRight;
    protected Array array = new Array();
    protected Random random = new Random();
    protected Stopwatch stopwatch = new Stopwatch();
    protected int count = 0;
    protected final static int fullPoints = 20;
    protected TextView textLevels;

    // Array for progress bar
    final int[] progress = {
            R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
            R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
            R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
            R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
    };

    // Click listener on TextView
    protected void setClickTV(TextView tv, Level fromLevel, Class toLevel) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(fromLevel, toLevel);
                    startActivity(intent);finish();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    // Click listener on Button
    protected void setClickBTN(Button btn, Level fromLevel, Class toLevel) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(fromLevel, toLevel);
                    startActivity(intent);finish();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    // Click listener on another TextView
    protected void setClickTVLevels(TextView tv, Level fromLevel, Class toLevel, int level,
                                    int levelID) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (level >= levelID) {
                        Intent intent = new Intent(fromLevel, toLevel);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }
}
