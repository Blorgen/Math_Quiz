package com.blorgen.mathquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.play_activity.*
import kotlin.random.Random
import android.widget.Toast.makeText as toastMakeText
import kotlin.collections.shuffle as shuffle

class PlayActivity : AppCompatActivity() {
    var text = "cool"
    var expression: String = "2+2"
    var answer: Int = 4
    var first_arg: Int = Random.nextInt(20, 150)
    var second_arg: Int = Random.nextInt(20, 150)
    var operator = listOf<Char>('+', '-', '*').shuffled()
    var numbers: MutableList<Int> = mutableListOf(1, 2, 3)
    var but = 1
    var but_two = 2
    var but_three = 3
    var but_four = 4
    var wrongCount = 0
    var totalCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.play_activity)
        loadActivity(first_arg, second_arg)


    }

    fun isright(button_number: Int) {

        if (button_number == 0) {
            if (but == answer) {
                text = "You are right"
                totalCount++
                val intent = Intent(this, PlayingActivity2::class.java)
                intent.putExtra("wrongcount", wrongCount)
                intent.putExtra("totalcount", totalCount)
                startActivity(intent)
            } else {
                wrongCount++
                text = "You are wrong"
            }
        }
        if (button_number == 1) {
            if (but_two == answer) {
                text = "You are right"
                totalCount++
                val intent = Intent(this, PlayingActivity2::class.java)
                intent.putExtra("wrongcount", wrongCount)
                intent.putExtra("totalcount", totalCount)
                startActivity(intent)
            } else {
                wrongCount++
                text = "You are wrong"
            }
        }
        if (button_number == 2) {
            if (but_three == answer) {
                text = "You are right"
                totalCount++
                val intent = Intent(this, PlayingActivity2::class.java)
                intent.putExtra("wrongcount", wrongCount)
                intent.putExtra("totalcount", totalCount)
                startActivity(intent)
            } else {
                wrongCount++
                text = "You are wrong"
            }
        }
        if (button_number == 3) {
            if (but_four == answer) {
                text = "You are right"
                totalCount++
                val intent = Intent(this, PlayingActivity2::class.java)
                intent.putExtra("wrongcount", wrongCount)
                intent.putExtra("totalcount", totalCount)
                startActivity(intent)
            } else {
                wrongCount++
                text = "You are wrong"
            }
        }
        val toast = Toast.makeText(applicationContext, text, LENGTH_LONG)
        toast.show()
    }

    fun loadActivity(arg1: Int, arg2: Int) {
        setContentView(R.layout.play_activity)

        wrongCount = getIntent().getIntExtra("wrongcount", wrongCount)
        totalCount = getIntent().getIntExtra("totalcount", totalCount)

        expression = "$arg1 ${operator[0]} $arg2"

        expression_view.text = expression

        if (operator[0] == '+') {
            answer = arg1 + arg2

        }
        if (operator[0] == '-') {
            answer = arg1 - arg2

        }
        if (operator[0] == '*') {
            answer = arg1 * arg2

        }
        wrong_view.text = "${wrong_view.text}$wrongCount"
        total_view.text = "${total_view.text}${totalCount}"

        numbers = mutableListOf(answer, answer + Random.nextInt(-10, 10), answer + Random.nextInt(-10, 10), answer + Random.nextInt(-10, 10))
        numbers.shuffle()
        first_button.text = numbers[0].toString()
        third_button.text = numbers[1].toString()
        second_button.text = numbers[2].toString()
        fourth_button.text = numbers[3].toString()
        but = first_button.text.toString().toInt()
        but_two = second_button.text.toString().toInt()
        but_three = third_button.text.toString().toInt()
        but_four = fourth_button.text.toString().toInt()
        first_button.setOnClickListener {
            isright(0)
        }
        second_button.setOnClickListener {
            isright(1)
        }
        third_button.setOnClickListener {
            isright(2)
        }
        fourth_button.setOnClickListener {
            isright(3)
        }

    }


    override fun onStop() {
        super.onStop()
        finish()
    }
}
