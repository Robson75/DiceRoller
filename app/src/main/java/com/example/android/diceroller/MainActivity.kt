package com.example.android.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    lateinit var diceImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        diceImage = findViewById(R.id.dice_image)
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val minTurns = 2
        val turns = Random.nextInt(10) + minTurns
        var last = 0
        Handler().postDelayed({
            getNewDiceImage(turns, last)
            Log.d(TAG, "get new dice image")

        }, 100)

    }

    private fun getNewDiceImage(turns:Int, last:Int) {
        var lastValue = last
        if (turns > 0) {
            var newValue = lastValue
            while (newValue == lastValue) {
                newValue = Random.nextInt(6) + 1
            }
            lastValue = newValue
            val drawableResource = when (newValue) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                6 -> R.drawable.dice_6
                else -> R.drawable.empty_dice
            }
            diceImage.setImageResource(drawableResource)
            Handler().postDelayed({
                getNewDiceImage(turns - 1, lastValue)
                Log.d(TAG, "get new dice image")

            }, 150)
        }
    }

}
