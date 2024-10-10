package com.softworkshub.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput :TextView? = null
    var lastDigit : Boolean = false
    var lastDot :Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.resultShow_textView)
    }

    fun onDigit(view : View){
        tvInput?.append((view as Button).text )
        lastDigit = true
        lastDot = false
    }

    fun clear(view:View){
        tvInput?.text=""
    }

    fun onDecimalPoint(view: View){
        if (lastDigit && !lastDot){
            tvInput?.append(".")
            lastDigit = false
            lastDot = true
        }
    }

    @SuppressLint("SetTextI18n")
    fun onEqual(view: View){
        if (lastDigit){
            var tvValue = tvInput?.text.toString()
            var prifix = ""

            try {

                if (tvValue.startsWith("-")){
                    prifix = "-"
                    tvValue = tvValue.substring(1)
                }


                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prifix.isNotEmpty()){
                        one = prifix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prifix.isNotEmpty()){
                        one = prifix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prifix.isNotEmpty()){
                        one = prifix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prifix.isNotEmpty()){
                        one = prifix + one
                    }
                    tvInput?.text = removeZeroAfterDot( (one.toDouble() / two.toDouble()).toString())
                }else if (tvValue.contains("%")){
                    val splitValue = tvValue.split("%")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prifix.isNotEmpty()){
                        one = prifix + one
                    }
                    tvInput?.text = removeZeroAfterDot((one.toDouble() % two.toDouble()).toString())
                }
            }catch (e : ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result :String):String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0,result.length-2)

        return  value
    }

    fun onOperator(view: View){
        tvInput?.text?.let {
            if (lastDigit && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastDigit = false
                lastDot = false
            }
        }
    }

    private fun isOperatorAdded(value :String):Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("+")|| value.contains("-") || value.contains("*") || value.contains("/") || value.contains("%")
        }
    }
}