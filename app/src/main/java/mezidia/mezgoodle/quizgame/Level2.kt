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
import mezidia.mezgoodle.quizgame.Level2

class Level2 : Level() {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.universal)

        // Get time on the start
        val startTime = stopwatch.GetTime()

        textLevels = findViewById(R.id.text_levels)
        textLevels!!.setText(R.string.level2)

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

        // Insert image in dialog window
        val previewImg = dialog!!.findViewById<ImageView>(R.id.previewimg)
        previewImg.setImageResource(R.drawable.previewimgtwo)

        // Set description
        val textDescription = dialog!!.findViewById<TextView>(R.id.textdescription)
        textDescription.setText(R.string.leveltwo)

        // Button for closing dialog window
        val btnClose = dialog!!.findViewById<TextView>(R.id.btnclose)
        setClickTV(btnClose, this@Level2, GameLevels::class.java)

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

        val textDescriptionEnd = dialogEnd!!.findViewById<TextView>(R.id.textdescriptionEnd)
        textDescriptionEnd.setText(R.string.leveltwoEnd)

        val timerDescriptionEnd = dialogEnd!!.findViewById<TextView>(R.id.timerdescriptionEnd)

        // Button for closing dialog window
        val btnClose1 = dialogEnd!!.findViewById<TextView>(R.id.btnclose)
        setClickTV(btnClose1, this@Level2, GameLevels::class.java)

        // Button for continue the activity
        val btnContinue1 = dialogEnd!!.findViewById<Button>(R.id.btncontinue)
        setClickTV(btnContinue1, this@Level2, Level3::class.java)

        // Button Back
        val btnBack = findViewById<Button>(R.id.button_back_level)
        setClickBTN(btnBack, this@Level2, GameLevels::class.java)

        // Connect animation
        val a = AnimationUtils.loadAnimation(this@Level2, R.anim.alpha)

        // Left picture
        numLeft = random.nextInt(numImages) // Random int
        imgLeft.setImageResource(array.images2[numLeft]) // Get image from array
        textLeft.setText(array.texts2[numLeft]) // Get text from array

        // Right picture
        numRight = random.nextInt(numImages)
        while (numLeft == numRight) {
            numRight = random.nextInt(numImages)
        }
        imgRight.setImageResource(array.images2[numRight])
        textRight.setText(array.texts2[numRight])

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
                    val save = getSharedPreferences("Save", Context.MODE_PRIVATE)
                    val level = save.getInt("Level", 1)
                    if (level > 2) {
                        // empty
                    } else {
                        val editor = save.edit()
                        editor.putInt("Level", 3)
                        editor.commit()
                    }
                    timerDescriptionEnd.text = result
                    dialogEnd!!.show()
                } else {
                    numLeft = random.nextInt(numImages) // Random int
                    imgLeft.setImageResource(array.images2[numLeft]) // Get image from array
                    imgLeft.startAnimation(a)
                    textLeft.setText(array.texts2[numLeft]) // Get text from array

                    // Right picture
                    numRight = random.nextInt(numImages)
                    while (numLeft == numRight) {
                        numRight = random.nextInt(numImages)
                    }
                    imgRight.setImageResource(array.images2[numRight])
                    imgRight.startAnimation(a)
                    textRight.setText(array.texts2[numRight])
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
                    if (level > 2) {
                        // empty
                    } else {
                        val editor = save.edit()
                        editor.putInt("Level", 3)
                        editor.commit()
                    }
                    dialogEnd!!.show()
                } else {
                    numLeft = random.nextInt(10) // Random int
                    imgLeft.setImageResource(array.images2[numLeft]) // Get image from array
                    imgLeft.startAnimation(a)
                    textLeft.setText(array.texts2[numLeft]) // Get text from array

                    // Right picture
                    numRight = random.nextInt(10)
                    while (numLeft == numRight) {
                        numRight = random.nextInt(10)
                    }
                    imgRight.setImageResource(array.images2[numRight])
                    imgRight.startAnimation(a)
                    textRight.setText(array.texts2[numRight])
                    imgLeft.isEnabled = true
                }
            }
            true
        }
    }

    // System button back
    override fun onBackPressed() {
        try {
            val intent = Intent(this@Level2, GameLevels::class.java)
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