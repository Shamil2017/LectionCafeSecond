package com.example.lectioncafesecond

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView

class active_main_order : AppCompatActivity() {
    private lateinit var textViewHello: TextView
    private lateinit var textViewAddition: TextView
    private lateinit var checkBoxMilk: CheckBox
    private lateinit var checkBoxSugar: CheckBox
    private lateinit var checkBoxLemon: CheckBox
    private lateinit var spinnerTea: Spinner
    private lateinit var spinnerCoffee: Spinner
    private lateinit var builderAddition: StringBuilder

    private var name: String = ""
    private var drink: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_main_order)
        val intent = intent

        name = intent.getStringExtra("name") ?: getString(R.string.defaultName)
        drink = getString(R.string.tea)

        textViewHello = findViewById(R.id.textViewHello)
        val hello = "Привет, $name! Что будете заказывать?"
        textViewHello.text = hello

        textViewAddition = findViewById(R.id.textViewAdditions)
        checkBoxMilk = findViewById(R.id.checkBoxMilk)
        checkBoxSugar = findViewById(R.id.checkBoxSugar)
        checkBoxLemon = findViewById(R.id.checkBoxLemon)
        spinnerTea = findViewById(R.id.spinnerTea)
        spinnerCoffee = findViewById(R.id.spinnerCoffee)
        builderAddition = StringBuilder()

    }

    fun onClickChangeDrink(view: android.view.View) {
        val button = view as RadioButton
        val id = button.id

        if (id == R.id.radioButtonTea) {
            drink = getString(R.string.tea)
            spinnerTea.visibility = View.VISIBLE
            spinnerCoffee.visibility = View.INVISIBLE
        } else if (id == R.id.radioButtonCoffee) {
            drink = getString(R.string.coffee)
            spinnerCoffee.visibility = View.VISIBLE
            spinnerTea.visibility = View.INVISIBLE
        }

        val additions = "Что добавить в ваш $drink"
        textViewAddition.text = additions
    }
    fun onClickSendOrder(view: android.view.View) {
        builderAddition.setLength(0)

        if (checkBoxLemon.isChecked) {
            builderAddition.append(getString(R.string.lemon)).append(" ")
        }
        if (checkBoxMilk.isChecked) {
            builderAddition.append(getString(R.string.milk)).append(" ")
        }
        if (checkBoxSugar.isChecked && drink == getString(R.string.tea)) {
            builderAddition.append(getString(R.string.sugar)).append(" ")
        }

        val optionOfDrink: String
        optionOfDrink = if (drink == getString(R.string.tea)) {
            spinnerTea.selectedItem.toString()
        } else {
            spinnerCoffee.selectedItem.toString()
        }

        val order = "Имя: $name Напиток: $drink Вид напитка: $optionOfDrink"

        val additions: String
        additions = if (builderAddition.length > 0) {
            "\n" + getString(R.string.need_additions) + builderAddition.toString()
        } else {
            ""
        }

        val fullOrder = order + additions

        val intent = Intent(this, Order_activity::class.java)
        intent.putExtra("order", fullOrder)
        startActivity(intent)
    }
}