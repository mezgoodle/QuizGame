package mezidia.mezgoodle.quizgame

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import mezidia.mezgoodle.quizgame.Level4

class Level4 : Level() {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.universal)

        // Get time on the start
        val startTime = stopwatch.GetTime()

        textLevels = findViewById(R.id.text_levels)
        textLevels!!.setText(R.string.level4)

        val imgLeft = findViewById<ImageView>(R.id.img_left)
        // Create round corners for left image
        imgLeft.clipToOutline = true

        val imgRight = findViewById<ImageView>(R.id.img_right)
        // Create round corners for right image
        imgRight.clipToOutline = true

        val textLeft = findViewById<TextView>(R.id.text_left)
        textLeft.setTextColor(R.color.black95)
        val textRight = findViewById<TextView>(R.id.text_right)
        textRight.setTextColor(R.color.black95)

        // Show game on fullscreen
        val w = window
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        // Set background image for dialog window
        val background = findViewById<ImageView>(R.id.background)
        background.setImageResource(R.drawable.level4)

        // Call dialog window on the start of game
        dialog = Dialog(this) // create new dialog window
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE) // hide window title
        dialog!!.setContentView(R.layout.previewdialog) // path to layout of dialog window
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCancelable(false) // cant close window with back button

        // Insert image in dialog window
        val previewImg = dialog!!.findViewById<ImageView>(R.id.previewimg)
        previewImg.setImageResource(R.drawable.previewimgfour)

        // Set background for dialog window
        val dialogBackground = dialog!!.findViewById<LinearLayout>(R.id.dialogbackground)
        dialogBackground.setBackgroundResource(R.drawable.previewbackgroundfour)

        // Set description
        val textDescription = dialog!!.findViewById<TextView>(R.id.textdescription)
        textDescription.setText(R.string.levelfour)

        // Button for closing dialog window
        val btnClose = dialog!!.findViewById<TextView>(R.id.btnclose)
        setClickTV(btnClose, this@Level4, GameLevels::class.java)

        // Button for continue the activity
        val btnContinue = dialog!!.findViewById<Button>(R.id.btncontinue)
        btnContinue.setOnClickListener { v: View? -> dialog!!.dismiss() }
        dialog!!.show() // show dialog window

        // Call dialog window on the end of game
        dialogEnd = Dialog(this) // create new dialog window
        dialogEnd!!.requestWindowFeature(Window.FEATURE_NO_TITLE) // hide window title
        dialogEnd!!.setContentView(R.layout.dialogend) // path to layout of dialog window
        dialogEnd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogEnd!!.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT)
        dialogEnd!!.setCancelable(false) // cant close window with back button

        // Set background image
        val dialogBackgroundEnd = dialogEnd!!.findViewById<LinearLayout>(R.id.dialogbackground)
        dialogBackgroundEnd.setBackgroundResource(R.drawable.previewbackgroundfour)

        val textDescriptionEnd = dialogEnd!!.findViewById<TextView>(R.id.textdescriptionEnd)
        textDescriptionEnd.setText(R.string.levelfourEnd)
        val timerDescriptionEnd = dialogEnd!!.findViewById<TextView>(R.id.timerdescriptionEnd)

        // Button for closing dialog window
        val btnClose1 = dialogEnd!!.findViewById<TextView>(R.id.btnclose)
        setClickTV(btnClose1, this@Level4, GameLevels::class.java)

        // Button for continue the activity
        val btnContinue1 = dialogEnd!!.findViewById<Button>(R.id.btncontinue)
        setClickBTN(btnContinue1, this@Level4, GameLevels::class.java)

        // Button Back
        val btnBack = findViewById<Button>(R.id.button_back_level)
        setClickBTN(btnBack, this@Level4, GameLevels::class.java)

        // Left picture
        numLeft = random.nextInt(numImages) // Random int
        imgLeft.setImageResource(array.images4[numLeft]) // Get image from array
        textLeft.setText(array.texts4[numLeft]) // Get text from array

        // Right picture
        numRight = random.nextInt(numImages)
        while (array.strong4[numLeft] == array.strong4[numRight]) {
            numRight = random.nextInt(numImages)
        }
        imgRight.setImageResource(array.images4[numRight])
        textRight.setText(array.texts4[numRight])

        // Listen clicking on left image
        imgLeft.setOnTouchListener { v, event -> // Touch image
            if (event.action == MotionEvent.ACTION_DOWN) {
                imgRight.isEnabled = false // Block right picture
                if (array.strong4[numLeft] > array.strong4[numRight]) {
                    imgLeft.setImageResource(R.drawable.img_true)
                } else {
                    imgLeft.setImageResource(R.drawable.img_false)
                }
            } else if (event.action == MotionEvent.ACTION_UP) {
                if (array.strong4[numLeft] > array.strong4[numRight]) {
                    if (count < fullPoints) {
                        count += 1
                    }

                    // Fill progress
                    for (i in 0 until fullPoints) {
                        val tv = findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_points)
                    }
                    for (i in 0 until count) {
                        val tv = findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_points_green)
                    }
                } else {
                    if (count > 0) {
                        if (count == 1) {
                            count = 0
                        } else {
                            count -= 2
                        }
                    }
                    // Fill progress
                    for (i in 0 until fullPoints - 1) {
                        val tv = findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_points)
                    }
                    for (i in 0 until count) {
                        val tv = findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_points_green)
                    }
                }
                if (count == fullPoints) {
                    // Exit from level
                    val finishTime = stopwatch.GetTime()
                    val result = stopwatch.GetResult(finishTime - startTime)
                    timerDescriptionEnd.text = result
                    dialogEnd!!.show()
                } else {
                    // Left picture
                    numLeft = random.nextInt(numImages) // Random int
                    imgLeft.setImageResource(array.images4[numLeft]) // Get image from array
                    textLeft.setText(array.texts4[numLeft]) // Get text from array

                    // Right picture
                    numRight = random.nextInt(numImages)
                    while (array.strong4[numLeft] == array.strong4[numRight]) {
                        numRight = random.nextInt(numImages)
                    }
                    imgRight.setImageResource(array.images4[numRight])
                    textRight.setText(array.texts4[numRight])
                    imgRight.isEnabled = true
                }
            }
            true
        }

        // Listen clicking on right image
        imgRight.setOnTouchListener { v, event -> // Touch image
            if (event.action == MotionEvent.ACTION_DOWN) {
                imgLeft.isEnabled = false // Block left picture
                if (array.strong4[numLeft] < array.strong4[numRight]) {
                    imgRight.setImageResource(R.drawable.img_true)
                } else {
                    imgRight.setImageResource(R.drawable.img_false)
                }
            } else if (event.action == MotionEvent.ACTION_UP) {
                if (array.strong4[numLeft] < array.strong4[numRight]) {
                    if (count < fullPoints) {
                        count += 1
                    }

                    // Fill progress
                    for (i in 0 until fullPoints) {
                        val tv = findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_points)
                    }
                    for (i in 0 until count) {
                        val tv = findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_points_green)
                    }
                } else {
                    if (count > 0) {
                        if (count == 1) {
                            count = 0
                        } else {
                            count -= 2
                        }
                    }
                    // Fill progress
                    for (i in 0 until fullPoints - 1) {
                        val tv = findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_points)
                    }
                    for (i in 0 until count) {
                        val tv = findViewById<TextView>(progress[i])
                        tv.setBackgroundResource(R.drawable.style_points_green)
                    }
                }
                if (count == fullPoints) {
                    // Exit from level
                    val finishTime = stopwatch.GetTime()
                    val result = stopwatch.GetResult(finishTime - startTime)
                    timerDescriptionEnd.text = result
                    dialogEnd!!.show()
                } else {
                    // Left picture
                    numLeft = random.nextInt(numImages) // Random int
                    imgLeft.setImageResource(array.images4[numLeft]) // Get image from array
                    textLeft.setText(array.texts4[numLeft]) // Get text from array

                    // Right picture
                    numRight = random.nextInt(numImages)
                    while (array.strong4[numLeft] == array.strong4[numRight]) {
                        numRight = random.nextInt(numImages)
                    }
                    imgRight.setImageResource(array.images4[numRight])
                    textRight.setText(array.texts4[numRight])
                    imgLeft.isEnabled = true
                }
            }
            true
        }
    }

    // System button back
    override fun onBackPressed() {
        try {
            val intent = Intent(this@Level4, GameLevels::class.java)
            startActivity(intent)
            finish()
        } catch (e: Exception) {
            println(e)
        }
    }

    companion object {
        const val numImages = 20
    }
}