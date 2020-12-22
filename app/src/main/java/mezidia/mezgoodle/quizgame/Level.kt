package mezidia.mezgoodle.quizgame

import android.app.Dialog
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

open class Level : AppCompatActivity() {
    // Common fields
    @JvmField
    val fullPoints = 20
    @JvmField
    var dialog: Dialog? = null
    @JvmField
    var dialogEnd: Dialog? = null
    @JvmField
    var numLeft = 0
    @JvmField
    var numRight = 0
    @JvmField
    var array = Array()
    @JvmField
    var random = Random()
    @JvmField
    var stopwatch = Stopwatch()
    @JvmField
    var count = 0
    @JvmField
    var textLevels: TextView? = null

    // Array for progress bar
    @JvmField
    val progress = intArrayOf(
            R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
            R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
            R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
            R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20)

    // Click listener on TextView
    protected fun setClickTV(tv: TextView, fromLevel: Level, toLevel: Class<*>) {
        tv.setOnClickListener {
            try {
                val intent = Intent(fromLevel, toLevel)
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    // Click listener on Button
    protected fun setClickBTN(btn: Button, fromLevel: Level, toLevel: Class<*>) {
        btn.setOnClickListener {
            try {
                val intent = Intent(fromLevel, toLevel)
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    // Click listener on another TextView
    protected fun setClickTVLevels(tv: TextView, fromLevel: Level, toLevel: Class<*>, level: Int,
                                   levelID: Int) {
        tv.setOnClickListener {
            try {
                if (level >= levelID) {
                    val intent = Intent(fromLevel, toLevel)
                    startActivity(intent)
                    finish()
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}