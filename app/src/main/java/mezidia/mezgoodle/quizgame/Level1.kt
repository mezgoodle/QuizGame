package mezidia.mezgoodle.quizgame

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import mezidia.mezgoodle.quizgame.Level1

class Level1 : Level() {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.universal)

        // Get time on the start
        val startTime = stopwatch.GetTime()

        textLevels = findViewById(R.id.text_levels)
        textLevels!!.setText(R.string.level1)

        val imgLeft = findViewById<ImageView>(R.id.img_left)
        // Create round corners for left image
        imgLeft.clipToOutline = true

        val imgRight = findViewById<ImageView>(R.id.img_right)
        // Create round corners for right image
        imgRight.clipToOutline = true

        val textLeft = findViewById<TextView>(R.id.text_left)
        val textRight = findViewById<TextView>(R.id.text_right)

        // Show game on fullscreen
        val w = window
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        // Call dialog window on the start of game
        dialog = Dialog(this) // create new dialog window
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE) // hide window title
        dialog!!.setContentView(R.layout.previewdialog) // path to layout of dialog window
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCancelable(false) // cant close window with back button

        // Button for closing dialog window
        val btnClose = dialog!!.findViewById<TextView>(R.id.btnclose)
        setClickTV(btnClose, this@Level1, GameLevels::class.java)

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

        val timerDescriptionEnd = dialogEnd!!.findViewById<TextView>(R.id.timerdescriptionEnd)

        // Button for closing dialog window
        val btnClose1 = dialogEnd!!.findViewById<TextView>(R.id.btnclose)
        setClickTV(btnClose1, this@Level1, GameLevels::class.java)

        // Button for continue the activity
        val btnContinue1 = dialogEnd!!.findViewById<Button>(R.id.btncontinue)
        setClickBTN(btnContinue1, this@Level1, Level2::class.java)

        // Button Back
        val btnBack = findViewById<Button>(R.id.button_back_level)
        setClickBTN(btnBack, this@Level1, GameLevels::class.java)

        // Connect animation
        val a = AnimationUtils.loadAnimation(this@Level1, R.anim.alpha)

        // Left picture
        numLeft = random.nextInt(numImages) // Random int
        imgLeft.setImageResource(array.images1[numLeft]) // Get image from array
        textLeft.setText(array.texts1[numLeft]) // Get text from array

        // Right picture
        numRight = random.nextInt(numImages)
        while (numLeft == numRight) {
            numRight = random.nextInt(numImages)
        }
        imgRight.setImageResource(array.images1[numRight])
        textRight.setText(array.texts1[numRight])

        // Listen clicking on left image
        imgLeft.setOnTouchListener { v, event -> // Touch image
            if (event.action == MotionEvent.ACTION_DOWN) {
                imgRight.isEnabled = false // Block right picture
                if (numLeft > numRight) {
                    imgLeft.setImageResource(R.drawable.img_true)
                } else {
                    imgLeft.setImageResource(R.drawable.img_false)
                }
            } else if (event.action == MotionEvent.ACTION_UP) {
                if (numLeft > numRight) {
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
                    val save = getSharedPreferences("Save", Context.MODE_PRIVATE)
                    val level = save.getInt("Level", 1)
                    if (level > 1) {
                        // empty
                    } else {
                        val editor = save.edit()
                        editor.putInt("Level", 2)
                        editor.commit()
                    }
                    dialogEnd!!.show()
                } else {
                    numLeft = random.nextInt(numImages) // Random int
                    imgLeft.setImageResource(array.images1[numLeft]) // Get image from array
                    imgLeft.startAnimation(a)
                    textLeft.setText(array.texts1[numLeft]) // Get text from array

                    // Right picture
                    numRight = random.nextInt(numImages)
                    while (numLeft == numRight) {
                        numRight = random.nextInt(numImages)
                    }
                    imgRight.setImageResource(array.images1[numRight])
                    imgRight.startAnimation(a)
                    textRight.setText(array.texts1[numRight])
                    imgRight.isEnabled = true
                }
            }
            true
        }

        // Listen clicking on right image
        imgRight.setOnTouchListener { v, event -> // Touch image
            if (event.action == MotionEvent.ACTION_DOWN) {
                imgLeft.isEnabled = false // Block left picture
                if (numLeft < numRight) {
                    imgRight.setImageResource(R.drawable.img_true)
                } else {
                    imgRight.setImageResource(R.drawable.img_false)
                }
            } else if (event.action == MotionEvent.ACTION_UP) {
                if (numLeft < numRight) {
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
                    val save = getSharedPreferences("Save", Context.MODE_PRIVATE)
                    val level = save.getInt("Level", 1)
                    if (level > 1) {
                        // empty
                    } else {
                        val editor = save.edit()
                        editor.putInt("Level", 2)
                        editor.commit()
                    }
                    dialogEnd!!.show()
                } else {
                    numLeft = random.nextInt(numImages) // Random int
                    imgLeft.setImageResource(array.images1[numLeft]) // Get image from array
                    imgLeft.startAnimation(a)
                    textLeft.setText(array.texts1[numLeft]) // Get text from array

                    // Right picture
                    numRight = random.nextInt(numImages)
                    while (numLeft == numRight) {
                        numRight = random.nextInt(numImages)
                    }
                    imgRight.setImageResource(array.images1[numRight])
                    imgRight.startAnimation(a)
                    textRight.setText(array.texts1[numRight])
                    imgLeft.isEnabled = true
                }
            }
            true
        }
    }

    // System button back
    override fun onBackPressed() {
        try {
            val intent = Intent(this@Level1, GameLevels::class.java)
            startActivity(intent)
            finish()
        } catch (e: Exception) {
            println(e)
        }
    }

    companion object {
        const val numImages = 10
    }
}