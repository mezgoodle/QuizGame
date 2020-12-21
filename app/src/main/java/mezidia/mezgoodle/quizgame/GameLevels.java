
package mezidia.mezgoodle.quizgame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class GameLevels extends Level {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);

        final SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int level = save.getInt("Level", 1);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button btnBack = findViewById(R.id.button_back);
        this.setClickBTN(btnBack, GameLevels.this, MainActivity.class);

        // Button for step on level1
        TextView textView1 = findViewById(R.id.textView1);
        this.setClickTVLevels(textView1, GameLevels.this, Level1.class, level, 1);

        // Button for step on level2
        TextView textView2 = findViewById(R.id.textView2);
        this.setClickTVLevels(textView2, GameLevels.this, Level2.class, level, 2);

        // Button for step on level3
        TextView textView3 = findViewById(R.id.textView3);
        this.setClickTVLevels(textView3, GameLevels.this, Level3.class, level, 3);

        // Button for step on level4
        TextView textView4 = findViewById(R.id.textView4);
        this.setClickTVLevels(textView4, GameLevels.this, Level4.class, level, 4);

        final int[] x = {
            R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5,
            R.id.textView6, R.id.textView7, R.id.textView8, R.id.textView9, R.id.textView10,
            R.id.textView11, R.id.textView12, R.id.textView13, R.id.textView14, R.id.textView15,
            R.id.textView16, R.id.textView17, R.id.textView18, R.id.textView19, R.id.textView20,
            R.id.textView21, R.id.textView22, R.id.textView23, R.id.textView24, R.id.textView25,
            R.id.textView26, R.id.textView27, R.id.textView28, R.id.textView29, R.id.textView30,
        };

        for (int i = 1; i < level; i++) {
            TextView tv = findViewById(x[i]);
            tv.setText("" + (i + 1));
        }
    }
    // System button "Back"
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(GameLevels.this, MainActivity.class);
            startActivity(intent);finish();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
