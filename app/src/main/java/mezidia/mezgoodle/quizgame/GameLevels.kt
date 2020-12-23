package mezidia.mezgoodle.quizgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView

class GameLevels : Level() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gamelevels)

        // Set first level open
        val save = getSharedPreferences("Save", Context.MODE_PRIVATE)
        val level = save.getInt("Level", 1)

        // Set window
        val w = window
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // Back button
        val btnBack = findViewById<Button>(R.id.button_back)
        setClickBTN(btnBack, this@GameLevels, MainActivity::class.java)

        // Button for step on level1
        val textView1 = findViewById<TextView>(R.id.textView1)
        setClickTVLevels(textView1, this@GameLevels, Level1::class.java, level, 1)

        // Button for step on level2
        val textView2 = findViewById<TextView>(R.id.textView2)
        setClickTVLevels(textView2, this@GameLevels, Level2::class.java, level, 2)

        // Button for step on level3
        val textView3 = findViewById<TextView>(R.id.textView3)
        setClickTVLevels(textView3, this@GameLevels, Level3::class.java, level, 3)

        // Button for step on level4
        val textView4 = findViewById<TextView>(R.id.textView4)
        setClickTVLevels(textView4, this@GameLevels, Level4::class.java, level, 4)

        // Level views
        val x = intArrayOf(
                R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4, R.id.textView5,
                R.id.textView6, R.id.textView7, R.id.textView8, R.id.textView9, R.id.textView10,
                R.id.textView11, R.id.textView12, R.id.textView13, R.id.textView14, R.id.textView15,
                R.id.textView16, R.id.textView17, R.id.textView18, R.id.textView19, R.id.textView20,
                R.id.textView21, R.id.textView22, R.id.textView23, R.id.textView24, R.id.textView25,
                R.id.textView26, R.id.textView27, R.id.textView28, R.id.textView29, R.id.textView30)

        // Set text on open levels
        for (i in 1 until level) {
            val tv = findViewById<TextView>(x[i])
            tv.text = "" + (i + 1)
        }
    }

    // System button "Back"
    override fun onBackPressed() {
        try {
            val intent = Intent(this@GameLevels, MainActivity::class.java)
            startActivity(intent)
            finish()
        } catch (e: Exception) {
            println(e)
        }
    }
}