package mezidia.mezgoodle.quizgame;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
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

import androidx.annotation.RequiresApi;

public class Level1 extends Level {
    final static int numImages = 10;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        // Get time on the start
        final long startTime = stopwatch.GetTime();

        textLevels = findViewById(R.id.text_levels);
        textLevels.setText(R.string.level1);

        final ImageView imgLeft = findViewById(R.id.img_left);
        // Create round corners for left image
        imgLeft.setClipToOutline(true);
        final ImageView imgRight = findViewById(R.id.img_right);
        // Create round corners for right image
        imgRight.setClipToOutline(true);

        final TextView textLeft = findViewById(R.id.text_left);
        final TextView textRight = findViewById(R.id.text_right);

        // Show game on fullscreen
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Call dialog window on the start of game
        dialog = new Dialog(this); // create new dialog window
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // hide window title
        dialog.setContentView(R.layout.previewdialog); // path to layout of dialog window
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false); // cant close window with back button

        // Button for closing dialog window
        TextView btnClose = dialog.findViewById(R.id.btnclose);
        this.setClickTV(btnClose, Level1.this, GameLevels.class);

        // Button for continue the activity
        Button btnContinue = dialog.findViewById(R.id.btncontinue);
        btnContinue.setOnClickListener(v -> dialog.dismiss());

        dialog.show(); // show dialog window

        // Call dialog window on the end of game
        dialogEnd = new Dialog(this); // create new dialog window
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); // hide window title
        dialogEnd.setContentView(R.layout.dialogend); // path to layout of dialog window
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false); // cant close window with back button

        TextView timerDescriptionEnd = dialogEnd.findViewById(R.id.timerdescriptionEnd);

        // Button for closing dialog window
        TextView btnClose1 = dialogEnd.findViewById(R.id.btnclose);
        this.setClickTV(btnClose1, Level1.this, GameLevels.class);

        // Button for continue the activity
        Button btnContinue1 = dialogEnd.findViewById(R.id.btncontinue);
        this.setClickBTN(btnContinue1, Level1.this, Level2.class);

        // Button Back
        Button btnBack = findViewById(R.id.button_back_level);
        this.setClickBTN(btnBack, Level1.this, GameLevels.class);

        // Connect animation
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);

        // Left picture
        numLeft = random.nextInt(numImages);               // Random int
        imgLeft.setImageResource(array.images1[numLeft]); // Get image from array
        textLeft.setText(array.texts1[numLeft]);          // Get text from array

        // Right picture
        numRight = random.nextInt(numImages);
        while (numLeft == numRight) {
            numRight = random.nextInt(numImages);
        }
        imgRight.setImageResource(array.images1[numRight]);
        textRight.setText(array.texts1[numRight]);

        // Listen clicking on left image
        imgLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Touch image
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgRight.setEnabled(false); // Block right picture
                    if (numLeft > numRight) {
                        imgLeft.setImageResource(R.drawable.img_true);
                    } else {
                        imgLeft.setImageResource(R.drawable.img_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft > numRight) {
                        if (count < fullPoints) {
                            count += 1;
                        }

                        // Fill progress
                        for (int i = 0; i < fullPoints; i++) {
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
                        for (int i = 0; i < fullPoints - 1; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == fullPoints) {
                        // Exit from level
                        final long finishTime = stopwatch.GetTime();
                        String result = stopwatch.GetResult(finishTime - startTime);
                        timerDescriptionEnd.setText(result);
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if (level > 1) {
                            // empty
                        } else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 2);
                            editor.commit();
                        }
                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(numImages);               // Random int
                        imgLeft.setImageResource(array.images1[numLeft]); // Get image from array
                        imgLeft.startAnimation(a);
                        textLeft.setText(array.texts1[numLeft]);          // Get text from array

                        // Right picture
                        numRight = random.nextInt(numImages);
                        while (numLeft == numRight) {
                            numRight = random.nextInt(numImages);
                        }
                        imgRight.setImageResource(array.images1[numRight]);
                        imgRight.startAnimation(a);
                        textRight.setText(array.texts1[numRight]);

                        imgRight.setEnabled(true);
                    }
                }
                return true;
            }
        });

        // Listen clicking on right image
        imgRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Touch image
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgLeft.setEnabled(false); // Block left picture
                    if (numLeft < numRight) {
                        imgRight.setImageResource(R.drawable.img_true);
                    } else {
                        imgRight.setImageResource(R.drawable.img_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft < numRight) {
                        if (count < fullPoints) {
                            count += 1;
                        }

                        // Fill progress
                        for (int i = 0; i < fullPoints; i++) {
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
                        for (int i = 0; i < fullPoints - 1; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == fullPoints) {
                        // Exit from level
                        final long finishTime = stopwatch.GetTime();
                        String result = stopwatch.GetResult(finishTime - startTime);
                        timerDescriptionEnd.setText(result);
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);
                        if (level > 1) {
                            // empty
                        } else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 2);
                            editor.commit();
                        }
                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(numImages);               // Random int
                        imgLeft.setImageResource(array.images1[numLeft]); // Get image from array
                        imgLeft.startAnimation(a);
                        textLeft.setText(array.texts1[numLeft]);          // Get text from array

                        // Right picture
                        numRight = random.nextInt(numImages);
                        while (numLeft == numRight) {
                            numRight = random.nextInt(numImages);
                        }
                        imgRight.setImageResource(array.images1[numRight]);
                        imgRight.startAnimation(a);
                        textRight.setText(array.texts1[numRight]);

                        imgLeft.setEnabled(true);
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
            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent);finish();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
