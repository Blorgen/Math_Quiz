package com.blorgen.mathquiz

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_playing.*
import kotlin.random.Random

class PlayingActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var expression: TextView
    private var answer = 0.0
    private var first_arg = 0
    private var second_arg = 0
    private lateinit var operator: List<Char>
    private lateinit var numbers: List<Double>
    private var wrongCount = 0
    private var rightCount = 0
    private var sumSwitch = true
    private var minusSwitch = true
    private var multiplySwitch = true
    private var divideSwitch = true
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

    }

    private fun isRight(btn: Button) {
        if (btn.text.toString().toDouble() == answer.round()) {
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
            wrongCount++
            tvWrongCount.text = "Wrong: " + wrongCount
//          --------------------------
            btn.isEnabled = false
            btn.text = "NOPE"
        }

    }

    private fun expressionMaker() {
        operator = mutableListOf<Char>()
        if (sumSwitch){
            (operator as MutableList<Char>).add('+')
        }
        if (minusSwitch){
            (operator as MutableList<Char>).add('-')
        }
        if (multiplySwitch){
            (operator as MutableList<Char>).add('*')
        }
        if (divideSwitch){
            (operator as MutableList<Char>).add('/')
        }
        operator = operator.shuffled()
        first_arg = Random.nextInt(20, 150)
        second_arg = Random.nextInt(20, 150)
        expression.text = "$first_arg ${operator[0]} $second_arg"
    }

    private fun answersMaker() {

        var isDivide = false

        if (operator[0] == '+') {
            answer = (first_arg + second_arg).toDouble()
        }
        if (operator[0] == '-') {
            answer = (first_arg - second_arg).toDouble()
        }
        if (operator[0] == '*') {
            answer = (first_arg * second_arg).toDouble()
        }
        if (operator[0] == '/'){
            answer = first_arg.toDouble() / second_arg.toDouble()
            isDivide = true
        }

        if (isDivide){
            numbers = listOf<Double>(
                answer,
                answer + Random.nextDouble(-10.0, 10.0),
                answer + Random.nextDouble(-10.0, 10.0),
                answer + Random.nextDouble(-10.0, 10.0)
            )
        } else {
            numbers = listOf<Double>(
                answer,
                answer + Random.nextInt(-10, 10),
                answer + Random.nextInt(-10, 10),
                answer + Random.nextInt(-10, 10)
            )
        }

        numbers = numbers.shuffled()

        if (isDivide) {
            btnOne.text = numbers[0].round().toString()
            btnTwo.text = numbers[1].round().toString()
            btnThree.text = numbers[2].round().toString()
            btnFour.text = numbers[3].round().toString()
        } else {
            btnOne.text = numbers[0].toInt().toString()
            btnTwo.text = numbers[1].toInt().toString()
            btnThree.text = numbers[2].toInt().toString()
            btnFour.text = numbers[3].toInt().toString()
        }
    }

    private fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(this).toDouble()

    override fun onResume() {
        super.onResume()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sumSwitch = sharedPreferences.getBoolean("sum", true)
        minusSwitch = sharedPreferences.getBoolean("minus", true)
        multiplySwitch = sharedPreferences.getBoolean("multiply", true)
        divideSwitch = sharedPreferences.getBoolean("divide", true)

        expressionMaker()
        answersMaker()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("wrong", wrongCount)
        outState.putInt("right", rightCount)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        wrongCount = savedInstanceState.getInt("wrong")
        rightCount = savedInstanceState.getInt("right")
        //
        tvWrongCount.text = "Wrong: " + wrongCount
        tvRightCount.text = "Right: " + rightCount

    }


}