package com.aaxena.calculator

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.view.KeyEvent
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    val SHARED_PREFS = "sharedPrefs"
    val TEXT = "text"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (isFirstTime()) {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Press the Volume Up and Down buttons to Enable or Disable Button Vibrations")
                // if the dialog is cancelable
                .setCancelable(false)
                .setPositiveButton("Okay!") { dialog, id ->
                    dialog.dismiss()
                }
            val alert = dialogBuilder.create()
            alert.setTitle("Button Haptics")
            alert.show()
        }

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
                }
                else
                    vibratePhone()
                    answer.text = result.toString()

            } catch (e: Exception) {
                answer.text = getString(R.string.error)
            }
        }
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        val action: Int = event.getAction()
        val keyCode: Int = event.getKeyCode()
        return when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                if (action == KeyEvent.ACTION_UP) {
                    vibratePhoneLightly()
                    val sharedPreferences =
                        getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString(TEXT,"on");
                    editor.commit();
                    Toast.makeText(this,"Haptics Turned On",LENGTH_SHORT).show()
                }
                true
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                if (action == KeyEvent.ACTION_DOWN) {
                    vibratePhoneLightly()
                    val sharedPreferences =
                        getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString(TEXT,"off");
                    editor.commit();
                    Toast.makeText(this,"Haptics Turned Off",LENGTH_SHORT).show()
                }
                true
            }
            else -> super.dispatchKeyEvent(event)
        }
    }

    fun vibratePhoneLightly() {
        val sharedPreferences =
            getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val saving_as: String? = sharedPreferences.getString(TEXT, "on")
        if (saving_as.equals("on")) {
            val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(15)
        }
        else{
            val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(0)
        }

    }

    fun vibratePhone() {
        val sharedPreferences =
            getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val saving_as: String? = sharedPreferences.getString(TEXT, "on")
        if (saving_as.equals("on")) {
            val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(20)
        }
        else{
            val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(0)
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
    private fun isFirstTime(): Boolean {
        val preferences = getPreferences(Context.MODE_PRIVATE)
        val ranBefore = preferences.getBoolean("RanBefore", false)
        if (!ranBefore) {
            // first time
            val editor = preferences.edit()
            editor.putBoolean("RanBefore", true)
            editor.commit()
        }
        return !ranBefore
    }
}