package com.aaxena.calculator

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Numbers
        num0.setOnClickListener { appendVal("0", false)
        vibratePhoneLightly()}
        num1.setOnClickListener { appendVal("1", false)
            vibratePhoneLightly()}
        num2.setOnClickListener { appendVal("2", false)
            vibratePhoneLightly()}
        num3.setOnClickListener { appendVal("3", false)
            vibratePhoneLightly()}
        num4.setOnClickListener { appendVal("4", false)
            vibratePhoneLightly()}
        num5.setOnClickListener { appendVal("5", false)
            vibratePhoneLightly()}
        num6.setOnClickListener { appendVal("6", false)
            vibratePhoneLightly()}
        num7.setOnClickListener { appendVal("7", false)
            vibratePhoneLightly()}
        num8.setOnClickListener { appendVal("8", false)
            vibratePhoneLightly()}
        num9.setOnClickListener { appendVal("9", false)
            vibratePhoneLightly()}
        numDot.setOnClickListener { appendVal(".", false)
            vibratePhoneLightly()}
        //Operators
        clear.setOnClickListener { appendVal("", true)
        vibratePhone()}
        startBracket.setOnClickListener { appendVal(" ( ", false)
            vibratePhoneLightly()}
        closeBracket.setOnClickListener { appendVal(" ) ", false)
            vibratePhoneLightly()}
        actionDivide.setOnClickListener { appendVal(" / ", false)
            vibratePhoneLightly()}
        actionMultiply.setOnClickListener { appendVal(" * ", false)
            vibratePhoneLightly()}
        actionMinus.setOnClickListener { appendVal(" - ", false)
            vibratePhoneLightly()}
        actionAdd.setOnClickListener { appendVal(" + ", false)
            vibratePhoneLightly()}

        actionEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(placeholder.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    vibratePhone()
                    answer.text = longResult.toString()
                } else
                    vibratePhone()
                    answer.text = result.toString()

            } catch (e: Exception) {

            }

        }


    }

    fun vibratePhoneLightly() {
        val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(15)
    }

    fun vibratePhone() {
        val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.EFFECT_CLICK))
        } else {
            vibrator.vibrate(20)
        }
    }

    fun appendVal(string: String, isClear: Boolean) {
        if (isClear) {
            placeholder.text = ""
            answer.text = ""
        } else {
            placeholder.append(string)
        }
    }

}