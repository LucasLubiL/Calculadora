package com.atividadeum.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    private lateinit var txtDisplay: TextView

    var expressao = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtDisplay = findViewById(R.id.txtDisplay)

        configurarBotoes()
    }

    fun configurarBotoes(){

        val numeros = listOf(
            R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,
            R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9
        )

        for(id in numeros){

            findViewById<Button>(id).setOnClickListener {

                clicarNumero((it as Button).text.toString())

            }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { clicarOperador("+") }
        findViewById<Button>(R.id.btnSub).setOnClickListener { clicarOperador("-") }
        findViewById<Button>(R.id.btnMult).setOnClickListener { clicarOperador("*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { clicarOperador("/") }
        findViewById<Button>(R.id.btnPower).setOnClickListener { clicarOperador("^") }

        findViewById<Button>(R.id.btnEqual).setOnClickListener { calcularResultado() }

        findViewById<Button>(R.id.btnClear).setOnClickListener { limpar() }

        findViewById<Button>(R.id.btnDot).setOnClickListener { adicionarPonto() }

        findViewById<Button>(R.id.btnSin).setOnClickListener { funcaoCientifica("sin") }
        findViewById<Button>(R.id.btnCos).setOnClickListener { funcaoCientifica("cos") }
        findViewById<Button>(R.id.btnTan).setOnClickListener { funcaoCientifica("tan") }
        findViewById<Button>(R.id.btnLog).setOnClickListener { funcaoCientifica("log") }

    }

    fun clicarNumero(numero:String){

        if(txtDisplay.text == "0"){
            txtDisplay.text = numero
            expressao = numero
        }else{
            txtDisplay.text = txtDisplay.text.toString() + numero
            expressao += numero
        }

    }

    fun clicarOperador(op:String){

        if(expressao.isEmpty()) return

        val ultimo = expressao.last()

        if(ultimo == '+' || ultimo == '-' || ultimo == '*' || ultimo == '/' || ultimo == '^'){
            return
        }

        txtDisplay.text = txtDisplay.text.toString() + op
        expressao += op

    }

    fun calcularResultado(){

        try{

            val resultado = resolverExpressao(expressao)

            txtDisplay.text = resultado.toString()

            expressao = resultado.toString()

        }catch(e:Exception){

            mostrarErro("Erro na conta")

        }

    }

    fun resolverExpressao(expr:String):Double{

        var numeros = expr.split("+","-","*","/","^").map { it.toDouble() }.toMutableList()

        var operadores = mutableListOf<Char>()

        for(c in expr){
            if(c == '+' || c == '-' || c == '*' || c == '/' || c == '^'){
                operadores.add(c)
            }
        }

        var i = 0

        while(i < operadores.size){

            if(operadores[i] == '^' || operadores[i] == '*' || operadores[i] == '/'){

                val resultado = when(operadores[i]){

                    '^' -> numeros[i].pow(numeros[i+1])

                    '*' -> numeros[i] * numeros[i+1]

                    '/' -> {

                        if(numeros[i+1] == 0.0){
                            throw Exception("Divisão por zero")
                        }

                        numeros[i] / numeros[i+1]
                    }

                    else -> 0.0
                }

                numeros[i] = resultado
                numeros.removeAt(i+1)
                operadores.removeAt(i)

            }else{
                i++
            }

        }

        var resultado = numeros[0]

        for(j in operadores.indices){

            if(operadores[j] == '+'){
                resultado += numeros[j+1]
            }
            else if(operadores[j] == '-'){
                resultado -= numeros[j+1]
            }

        }

        return resultado

    }

    fun limpar(){

        txtDisplay.text = "0"
        expressao = ""

    }

    fun adicionarPonto(){

        txtDisplay.text = txtDisplay.text.toString() + "."
        expressao += "."

    }

    fun funcaoCientifica(tipo:String){

        try{

            val numero = txtDisplay.text.toString().toDouble()

            var resultado = 0.0

            if(tipo == "sin"){
                resultado = sin(Math.toRadians(numero))
            }
            else if(tipo == "cos"){
                resultado = cos(Math.toRadians(numero))
            }
            else if(tipo == "tan"){
                resultado = tan(Math.toRadians(numero))
            }
            else if(tipo == "log"){

                if(numero <= 0){
                    mostrarErro("Log só aceita números positivos")
                    return
                }

                resultado = log10(numero)

            }

            txtDisplay.text = resultado.toString()

            expressao = resultado.toString()

        }catch(e:Exception){

            mostrarErro("Erro no cálculo")

        }

    }

    fun mostrarErro(msg:String){

        val dialog = AlertDialog.Builder(this)
            .setTitle("Erro")
            .setMessage(msg)
            .setPositiveButton("OK",null)
            .create()

        dialog.show()

    }

}