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
        num0.setOnClickListener { appendVal("0", false) }
        num1.setOnClickListener { appendVal("1", false) }
        num2.setOnClickListener { appendVal("2", false) }
        num3.setOnClickListener { appendVal("3", false) }
        num4.setOnClickListener { appendVal("4", false) }
        num5.setOnClickListener { appendVal("5", false) }
        num6.setOnClickListener { appendVal("6", false) }
        num7.setOnClickListener { appendVal("7", false) }
        num8.setOnClickListener { appendVal("8", false) }
        num9.setOnClickListener { appendVal("9", false) }
        numDot.setOnClickListener { appendVal(".", false) }
        //Operators
        clear.setOnClickListener { appendVal("", true) }
        startBracket.setOnClickListener { appendVal(" ( ", false) }
        closeBracket.setOnClickListener { appendVal(" ) ", false) }
        actionDivide.setOnClickListener { appendVal(" / ", false) }
        actionMultiply.setOnClickListener { appendVal(" * ", false) }
        actionMinus.setOnClickListener { appendVal(" - ", false) }
        actionAdd.setOnClickListener { appendVal(" + ", false) }

        /*
        actionBack.setOnClickListener {
            val expression = placeholder.text.toString()
            if (expression.isNotEmpty()) {
                placeholder.text = expression.substring(0, expression.length - 1)
            }


        }

         */

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
//            placeholder.append(string)
        } else {
            placeholder.append(string)
        }
    }

}