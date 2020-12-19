package mezidia.mezgoodle.quizgame;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    protected TextView text_levels;
    Context lvl;

    public void setClickTV(TextView tv, Level fromLevel) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(fromLevel, GameLevels.class);
                    startActivity(intent);finish();
                } catch (Exception e) {
                    // Empty
                }
            }
        });
    }

    public void setClickBTN(Button btn, Level fromLevel) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(fromLevel, GameLevels.class);
                    startActivity(intent);finish();
                } catch (Exception e) {
                    // Empty
                }
            }
        });
    }
}
