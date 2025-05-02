package com.example.mykapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var oper: Int = 0
    private var num1: Double = 0.0
    private lateinit var numero1: TextView
    private lateinit var numero2: TextView
    private lateinit var btnigual: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        numero1 = findViewById(R.id.numero1)
        numero2 = findViewById(R.id.numero2)
        val btnborrar: Button = findViewById(R.id.btnborrar)
        btnigual = findViewById(R.id.btnigual)

        btnigual.setOnClickListener {
            try {
                val num2 = numero2.text.toString().toDouble()
                val resp = when (oper) {
                    1 -> num1 + num2
                    2 -> num1 - num2
                    3 -> num1 * num2
                    4 -> if (num2 != 0.0) num1 / num2 else Double.NaN
                    else -> 0.0
                }
                numero2.text = resp.toString()
                numero1.text = ""
            } catch (e: NumberFormatException) {
                numero2.text = "Error"
            }
        }

        btnborrar.setOnClickListener {
            numero1.text = ""
            numero2.text = ""
            num1 = 0.0
            oper = 0
        }

        numero2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                btnigual.isEnabled = !s.isNullOrEmpty()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    fun presionarDigito(view: View) {
        when (view.id) {
            R.id.btncero -> numero2.append("0")
            R.id.btnuno -> numero2.append("1")
            R.id.btndos -> numero2.append("2")
            R.id.btntres -> numero2.append("3")
            R.id.btncuatro -> numero2.append("4")
            R.id.btncinco -> numero2.append("5")
            R.id.btnseis -> numero2.append("6")
            R.id.btnsiete -> numero2.append("7")
            R.id.btnocho -> numero2.append("8")
            R.id.btnnueve -> numero2.append("9")
            R.id.btnpunto -> if (!numero2.text.contains(".")) numero2.append(".")
        }
    }

    fun clicOperacion(view: View) {
        if (numero2.text.isEmpty()) return
        num1 = numero2.text.toString().toDouble()
        val num2Text = numero2.text.toString()
        numero2.text = ""

        when (view.id) {
            R.id.btnsuma -> {
                numero1.text = "$num2Text +"
                oper = 1
            }
            R.id.btnrestar -> {
                numero1.text = "$num2Text -"
                oper = 2
            }
            R.id.btnmultiplicar -> {
                numero1.text = "$num2Text ×"
                oper = 3
            }
            R.id.btndivision -> {
                numero1.text = "$num2Text ÷"
                oper = 4
            }
        }
    }
}