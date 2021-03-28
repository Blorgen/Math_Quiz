package com.blorgen.mathquiz

import android.graphics.Color.red
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_playing.*
import kotlin.random.Random

class PlayingActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var text: String
    private lateinit var expression: TextView
    private var answer = 0
    private var first_arg = 0
    private var second_arg = 0
    private lateinit var operator: List<Char>
    private lateinit var numbers: List<Int>
    private var wrongCount = 0
    private var rightCount = 0
    private lateinit var tvWrongCount: TextView
    private lateinit var tvRightCount: TextView
    private lateinit var btnOne: Button
    private lateinit var btnTwo: Button
    private lateinit var btnThree: Button
    private lateinit var btnFour: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing)
        btnOne = first_button
        btnTwo = second_button
        btnThree = third_button
        btnFour = fourth_button
        tvWrongCount = wrong_view
        tvRightCount = total_view
        expression = expression_view

        first_button.setOnClickListener(this)
        second_button.setOnClickListener(this)
        third_button.setOnClickListener(this)
        fourth_button.setOnClickListener(this)

        expressionMaker()
        answersMaker()
    }

    override fun onClick(v: View) {
        if (v.id == btnOne.id) {
            isRight(btnOne)
        }
        if (v.id == btnTwo.id) {
            isRight(btnTwo)
        }
        if (v.id == btnThree.id) {
            isRight(btnThree)
        }
        if (v.id == btnFour.id) {
            isRight(btnFour)
        }
        val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_LONG)
        toast.show()

    }

    private fun isRight(btn: Button) {
        if (btn.text.toString().toInt() == answer) {
            text = "You are right"
            expressionMaker()
            answersMaker()
            rightCount++
            tvRightCount.text = "Right: " + rightCount
//          --------------------------
            btnOne.isEnabled = true
            btnTwo.isEnabled = true
            btnThree.isEnabled = true
            btnFour.isEnabled = true
        } else {
            text = "You are wrong"
            wrongCount++
            tvWrongCount.text = "Wrong: " + wrongCount
//          --------------------------
            btn.isEnabled = false
            btn.text = "NOPE"
        }

    }

    private fun expressionMaker() {
        operator = listOf<Char>('+', '-', '*').shuffled()
        first_arg = Random.nextInt(20, 150)
        second_arg = Random.nextInt(20, 150)
        expression.text = "$first_arg ${operator[0]} $second_arg"
    }

    private fun answersMaker() {

        if (operator[0] == '+') {
            answer = first_arg + second_arg
        }
        if (operator[0] == '-') {
            answer = first_arg - second_arg
        }
        if (operator[0] == '*') {
            answer = first_arg * second_arg
        }

        numbers = listOf<Int>(
                answer,
                answer + Random.nextInt(-10, 10),
                answer + Random.nextInt(-10, 10),
                answer + Random.nextInt(-10, 10)
        )
        numbers = numbers.shuffled()

        btnOne.text = numbers[0].toString()
        btnTwo.text = numbers[1].toString()
        btnThree.text = numbers[2].toString()
        btnFour.text = numbers[3].toString()
    }
}