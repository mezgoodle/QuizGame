package mezidia.mezgoodle.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class Level1 extends AppCompatActivity {

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        // Create round corners for left image
        img_left.setClipToOutline(true);
        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        // Create round corners for right image
        img_right.setClipToOutline(true);

        // Show game on fullscreen
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Call dialog window on the start of game
        dialog = new Dialog(this); // create new dialog window
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // hide window title
        dialog.setContentView(R.layout.previewdialog); // path to layout of dialog window
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // transparent color of dialog window
        dialog.setCancelable(false); // cant close window with back button
        dialog.show(); // show dialog window
    }
}