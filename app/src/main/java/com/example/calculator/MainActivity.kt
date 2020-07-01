package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private var isActionButton: Boolean = false
    private var isMinusButton: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //数値クリックしたとき
        tvOne.setOnClickListener { appendOnExpression("1", true) }
        tvTwo.setOnClickListener { appendOnExpression("2", true) }
        tvThree.setOnClickListener { appendOnExpression("3", true) }
        tvFour.setOnClickListener { appendOnExpression("4", true) }
        tvFive.setOnClickListener { appendOnExpression("5", true) }
        tvSix.setOnClickListener { appendOnExpression("6", true) }
        tvSeven.setOnClickListener { appendOnExpression("7", true) }
        tvEight.setOnClickListener { appendOnExpression("8", true) }
        tvNine.setOnClickListener { appendOnExpression("9", true) }
        tvZero.setOnClickListener { appendOnExpression("0", true) }
        tvPlusMinus.setOnClickListener {
            appendOnExpression("-", false)
            isMinusButton = true
        }

        //演算子
        tvPlus.setOnClickListener { appendOnExpression("+", false) }
        tvMinus.setOnClickListener { appendOnExpression("-", false) }
        tvMultiply.setOnClickListener { appendOnExpression("*", false) }
        tvDivide.setOnClickListener { appendOnExpression("/", false) }
        tvRemainder.setOnClickListener { appendOnExpression("%", false) }
        tvDot.setOnClickListener { appendOnExpression(".", false) }


        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
            isActionButton = false
            isMinusButton = false

        }

        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if (string.isNotEmpty()) {
                tvExpression.text = string.substring(0, string.length - 1)
            }
            tvResult.text = ""
        }

        tvEqual.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()

            } catch (e: Exception) {
                Log.d("Exception", "message:" + e.message)
            }
        }
    }

    fun appendOnExpression(string: String, canClear: Boolean) {
        if (tvResult.text.isNotEmpty()) {
            tvExpression.text = ""
        }
        if (canClear) {
            tvResult.text = ""
            tvExpression.append(string)

        } else {
            if (isActionButton) {
                val tvexpression = tvExpression.text.toString()
                if (tvexpression.isNotEmpty()) {
                    tvExpression.text = tvexpression.substring(0, tvexpression.length - 1)
                }
            }
            if(isMinusButton){
                val minus = tvExpression.text.toString()
                if(minus.isNotEmpty()){
                    val minusDouble = minus.toDouble()
                    val result = -1*minusDouble
                    tvExpression.text = result.toString()
                    tvResult.text = ""
                    isActionButton = true
                }
            } else {
                tvExpression.append(tvResult.text)
                tvExpression.append(string)
                tvResult.text = ""
                isActionButton = true
            }

        }
    }
}


