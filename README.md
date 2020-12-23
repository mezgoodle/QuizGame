# QuizGame

[![Language](https://img.shields.io/badge/language-kotlin-brightgreen?style=flat-square)](https://developer.android.com/kotlin)

Hello everyone! This is the repository of my Android game on Kotlin.

## Table of contents

- [Motivation](#motivation)
- [Build status](#build-status)
- [Badges](#badges)
- [Code style](#code-style)
- [Screenshots](#screenshots)
- [Game](#game)
- [Code Example](#code-example)
- [Installation](#installation)
- [Tests](#tests)
- [Contribute](#contribute)
- [Credits](#credits)
- [License](#license)

## Motivation

I did this project for one of my subjects at university. I didn't know anything about _Android_, so I was looking for lessons on _YouTube_. I did everything on videos, then added a little bit of my _own_, like timer and class imitation. Everything was first written in **Java**, which I also met for the first time, after that I used _Android Studio_ to convert to **Kotlin**.

## Build status

Here you can see build status of [continuous integration](https://en.wikipedia.org/wiki/Continuous_integration):

![Android CI](https://github.com/mezgoodle/QuizGame/workflows/Android%20CI/badge.svg)
![Lint Code Base](https://github.com/mezgoodle/QuizGame/workflows/Lint%20Code%20Base/badge.svg)

## Badges

[![Theme](https://img.shields.io/badge/Theme-Mobile_game-brightgreen?style=flat-square)](https://en.wikipedia.org/wiki/Mobile_game)

## Code style

I'm using [Codacy](https://www.codacy.com/) to automate my code quality.

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/eddd563787e94a95adfc7efc32116228)](https://www.codacy.com/gh/mezgoodle/QuizGame/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mezgoodle/QuizGame&amp;utm_campaign=Badge_Grade)

## Screenshots

- Game levels

![Screenshot 1](https://raw.githubusercontent.com/mezgoodle/images/master/quizgame1.jpg)

- Preview on level 1

![Screenshot 2](https://raw.githubusercontent.com/mezgoodle/images/master/quizgame2.jpg)

- Level 1

![Screenshot 3](https://raw.githubusercontent.com/mezgoodle/images/master/quizgame3.jpg)

## Game

This game is mostly for kids, for education, such as: take a bigger number or edible item.

## Code Example

Here you can see level-father class:

```kt
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
```

## Installation

Just download _apk_ file from [releases](https://github.com/mezgoodle/QuizGame/releases) and install on your device.

## Tests

I didn't write some special tests, but set up **Android CI** by _GitHub_. I give you the [link](https://github.com/mezgoodle/QuizGame/actions?query=workflow%3A%22Android+CI%22) to [GitHub Actions](https://github.com/features/actions), where you can see results.

## Contribute

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change. Also look at the [CONTRIBUTING.md](https://github.com/mezgoodle/QuizGame/blob/master/CONTRIBUTING.md).

## Credits

Links to video and articles which inspired me to build this project:

- [Tutorial playlist](https://www.youtube.com/watch?v=dB9ffzM9oq0&list=PLiyjLbEJ4htZC3N-OrOdLQyZd4usgFtX0)
- [Tutorial channel on YouTube(Russian)](https://www.youtube.com/c/LobanovSpace)
- [Official Android documentation](https://developer.android.com/docs)

## License

MIT © [mezgoodle](https://github.com/mezgoodle)
