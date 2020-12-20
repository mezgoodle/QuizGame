package mezidia.mezgoodle.quizgame;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class Level4 extends Level {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        // Get time on the start
        final long startTime = stopwatch.GetTime();

        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level4);

        final ImageView img_left = findViewById(R.id.img_left);
        // Create round corners for left image
        img_left.setClipToOutline(true);
        final ImageView img_right = findViewById(R.id.img_right);
        // Create round corners for right image
        img_right.setClipToOutline(true);

        final TextView text_left = findViewById(R.id.text_left);
        text_left.setTextColor(R.color.black95);
        final TextView text_right = findViewById(R.id.text_right);
        text_right.setTextColor(R.color.black95);

        // Show game on fullscreen
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Set background image for dialog window
        ImageView background = findViewById(R.id.background);
        background.setImageResource(R.drawable.level4);

        // Call dialog window on the start of game
        dialog = new Dialog(this); // create new dialog window
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // hide window title
        dialog.setContentView(R.layout.previewdialog); // path to layout of dialog window
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // transparent color of dialog window
        dialog.setCancelable(false); // cant close window with back button

        // Insert image in dialog window
        ImageView previewimg = dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimgfour);

        // Set background for dialog window
        LinearLayout dialogBackground = dialog.findViewById(R.id.dialogbackground);
        dialogBackground.setBackgroundResource(R.drawable.previewbackgroundfour);

        // Set description
        TextView textdescription = dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.levelfour);

        // Button for closing dialog window
        TextView btnClose = dialog.findViewById(R.id.btnclose);
        this.setClickTV(btnClose, Level4.this, GameLevels.class);

        // Button for continue the activity
        Button btnContinue = dialog.findViewById(R.id.btncontinue);
        btnContinue.setOnClickListener(v -> dialog.dismiss());

        dialog.show(); // show dialog window

        //_______________________________
        // Call dialog window on the end of game
        dialogEnd = new Dialog(this); // create new dialog window
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); // hide window title
        dialogEnd.setContentView(R.layout.dialogend); // path to layout of dialog window
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // transparent color of dialog window
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false); // cant close window with back button
        // Set background image
        LinearLayout dialogBackgroundEnd = dialogEnd.findViewById(R.id.dialogbackground);
        dialogBackgroundEnd.setBackgroundResource(R.drawable.previewbackgroundfour);

        TextView textdescriptionEnd = dialogEnd.findViewById(R.id.textdescriptionEnd);
        textdescriptionEnd.setText(R.string.levelfourEnd);
        TextView timerDescriptionEnd = dialogEnd.findViewById(R.id.timerdescriptionEnd);

        // Button for closing dialog window
        TextView btnClose1 = dialogEnd.findViewById(R.id.btnclose);
        this.setClickTV(btnClose1, Level4.this, GameLevels.class);

        // Button for continue the activity
        Button btnContinue1 = dialogEnd.findViewById(R.id.btncontinue);
        this.setClickBTN(btnContinue1, Level4.this, GameLevels.class);

        // Button Back
        Button btnBack = findViewById(R.id.button_back_level);
        this.setClickBTN(btnBack, Level4.this, GameLevels.class);

        // Left picture
        numLeft = random.nextInt(20);               // Random int
        img_left.setImageResource(array.images4[numLeft]); // Get image from array
        text_left.setText(array.texts4[numLeft]);          // Get text from array

        // Right picture
        numRight = random.nextInt(20);
        while (array.strong4[numLeft] == array.strong4[numRight]) {
            numRight = random.nextInt(20);
        }
        img_right.setImageResource(array.images4[numRight]);
        text_right.setText(array.texts4[numRight]);

        // Listen clicking on left image
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Touch image
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_right.setEnabled(false); // Block right picture
                    if (array.strong4[numLeft] > array.strong4[numRight]) {
                        img_left.setImageResource(R.drawable.img_true);
                    } else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (array.strong4[numLeft] > array.strong4[numRight]) {
                        if (count < full_points) {
                            count +=1;
                        }

                        // Fill progress
                        for (int i = 0; i < full_points; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count -= 2;
                            }
                        }
                        // Fill progress
                        for (int i = 0; i < full_points - 1; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == full_points) {
                        // Exit from level
                        final long finishTime = stopwatch.GetTime();
                        String result = stopwatch.GetResult(finishTime - startTime);
                        timerDescriptionEnd.setText(result);
                        dialogEnd.show();
                    } else {
                        // Left picture
                        numLeft = random.nextInt(20);               // Random int
                        img_left.setImageResource(array.images4[numLeft]); // Get image from array
                        text_left.setText(array.texts4[numLeft]);          // Get text from array

                        // Right picture
                        numRight = random.nextInt(20);
                        while (array.strong4[numLeft] == array.strong4[numRight]) {
                            numRight = random.nextInt(20);
                        }
                        img_right.setImageResource(array.images4[numRight]);
                        text_right.setText(array.texts4[numRight]);

                        img_right.setEnabled(true);
                    }
                }
                return true;
            }
        });

        // Listen clicking on right image
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Touch image
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_left.setEnabled(false); // Block left picture
                    if (array.strong4[numLeft] < array.strong4[numRight]) {
                        img_right.setImageResource(R.drawable.img_true);
                    } else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (array.strong4[numLeft] < array.strong4[numRight]) {
                        if (count < full_points) {
                            count +=1;
                        }

                        // Fill progress
                        for (int i = 0; i < full_points; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count -= 2;
                            }
                        }
                        // Fill progress
                        for (int i = 0; i < full_points - 1; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == full_points) {
                        // Exit from level
                        final long finishTime = stopwatch.GetTime();
                        String result = stopwatch.GetResult(finishTime - startTime);
                        timerDescriptionEnd.setText(result);
                        dialogEnd.show();
                    } else {
                        // Left picture
                        numLeft = random.nextInt(20);               // Random int
                        img_left.setImageResource(array.images4[numLeft]); // Get image from array
                        text_left.setText(array.texts4[numLeft]);          // Get text from array

                        // Right picture
                        numRight = random.nextInt(20);
                        while (array.strong4[numLeft] == array.strong4[numRight]) {
                            numRight = random.nextInt(20);
                        }
                        img_right.setImageResource(array.images4[numRight]);
                        text_right.setText(array.texts4[numRight]);

                        img_left.setEnabled(true);
                    }
                }
                return true;
            }
        });
    }

    // System button back
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Level4.this, GameLevels.class);
            startActivity(intent);finish();
        } catch (Exception e) {
            // Empty
        }
    }
}