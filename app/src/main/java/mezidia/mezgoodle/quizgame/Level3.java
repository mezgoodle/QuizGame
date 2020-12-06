package mezidia.mezgoodle.quizgame;

import android.app.Dialog;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level3 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;
    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0;
    final static int full_points = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        // Create round corners for left image
        img_left.setClipToOutline(true);
        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        // Create round corners for right image
        img_right.setClipToOutline(true);

        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);

        // Show game on fullscreen
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Set background image for dialog window
        ImageView background = (ImageView)findViewById(R.id.background);
        background.setImageResource(R.drawable.level3);

        // Call dialog window on the start of game
        dialog = new Dialog(this); // create new dialog window
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // hide window title
        dialog.setContentView(R.layout.previewdialog); // path to layout of dialog window
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // transparent color of dialog window
        dialog.setCancelable(false); // cant close window with back button

        // Insert image in dialog window
        ImageView previewimg = (ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimgthree);

        // Set background for dialog window
        LinearLayout dialogBackground = (LinearLayout)dialog.findViewById(R.id.dialogbackground);
        dialogBackground.setBackgroundResource(R.drawable.previewbackgroundthree);

        // Set description
        TextView textdescription = (TextView)dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.levelthree);

        // Button for closing dialog window
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);finish();
                } catch (Exception e) {
                    // Empty
                }
                dialog.dismiss(); // Close dialog window
            }
        });

        // Button for continue the activity
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

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

        TextView textdescriptionEnd = (TextView)dialogEnd.findViewById(R.id.textdescriptionEnd);
        textdescriptionEnd.setText(R.string.leveltwoEnd);
        // Button for closing dialog window
        TextView btnclose1 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);finish();
                } catch (Exception e) {
                    // Empty
                }
                dialogEnd.dismiss(); // Close dialog window
            }
        });

        // Button for continue the activity
        Button btncontinue1 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, Level3.class);
                    startActivity(intent);finish();
                } catch (Exception e) {
                    // Empty
                }
                dialogEnd.dismiss();
            }
        });

        // Button Back
        Button btn_back = (Button)findViewById(R.id.button_back_level);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);finish();
                } catch (Exception e) {
                    // Empty
                }
            }
        });

        // Array for progress bar
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };

        // Connect animation
        final Animation a = AnimationUtils.loadAnimation(Level3.this, R.anim.alpha);

        // Left picture
        numLeft = random.nextInt(21);               // Random int
        img_left.setImageResource(array.images3[numLeft]); // Get image from array
        text_left.setText(array.texts3[numLeft]);          // Get text from array

        // Right picture
        numRight = random.nextInt(21);
        while (numLeft == numRight) {
            numRight = random.nextInt(21);
        }
        img_right.setImageResource(array.images3[numRight]);
        text_right.setText(array.texts3[numRight]);

        // Listen clicking on left image
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Touch image
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_right.setEnabled(false); // Block right picture
                    if (numLeft > numRight) {
                        img_left.setImageResource(R.drawable.img_true);
                    } else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft > numRight) {
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
                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(21);               // Random int
                        img_left.setImageResource(array.images3[numLeft]); // Get image from array
                        img_left.startAnimation(a);
                        text_left.setText(array.texts3[numLeft]);          // Get text from array

                        // Right picture
                        numRight = random.nextInt(21);
                        while (numLeft == numRight) {
                            numRight = random.nextInt(21);
                        }
                        img_right.setImageResource(array.images3[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts3[numRight]);

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
                    if (numLeft < numRight) {
                        img_right.setImageResource(R.drawable.img_true);
                    } else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft < numRight) {
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
                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(21);               // Random int
                        img_left.setImageResource(array.images3[numLeft]); // Get image from array
                        img_left.startAnimation(a);
                        text_left.setText(array.texts3[numLeft]);          // Get text from array

                        // Right picture
                        numRight = random.nextInt(21);
                        while (numLeft == numRight) {
                            numRight = random.nextInt(21);
                        }
                        img_right.setImageResource(array.images3[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts3[numRight]);

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
            Intent intent = new Intent(Level3.this, GameLevels.class);
            startActivity(intent);finish();
        } catch (Exception e) {
            // Empty
        }
    }
}