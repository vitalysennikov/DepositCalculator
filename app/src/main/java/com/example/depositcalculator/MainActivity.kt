package com.example.depositcalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var depositAmount: TextInputEditText
    private lateinit var startDate: TextInputEditText
    private lateinit var endDate: TextInputEditText
    private lateinit var interestRate: TextInputEditText
    private lateinit var daysResult: TextView
    private lateinit var interestResult: TextView
    private lateinit var totalResult: TextView
    private lateinit var manualDateCheckbox: CheckBox

    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        depositAmount = findViewById(R.id.depositAmount)
        startDate = findViewById(R.id.startDate)
        endDate = findViewById(R.id.endDate)
        interestRate = findViewById(R.id.interestRate)
        daysResult = findViewById(R.id.daysResult)
        interestResult = findViewById(R.id.interestResult)
        totalResult = findViewById(R.id.totalResult)
        manualDateCheckbox = findViewById(R.id.manualDateCheckbox)
        val calculateButton = findViewById<Button>(R.id.calculateButton)

        startDate.setText(dateFormat.format(Date()))

        startDate.setOnClickListener { showDatePicker(startDate) }
        endDate.setOnClickListener { showDatePicker(endDate) }

        manualDateCheckbox.setOnCheckedChangeListener { _, isChecked ->
            startDate.isEnabled = isChecked
            if (!isChecked) startDate.setText(dateFormat.format(Date()))
        }

        calculateButton.setOnClickListener { calculate() }
    }

    private fun showDatePicker(field: TextInputEditText) {
        DatePickerDialog(
            this,
            { _, year, month, day ->
                calendar.set(year, month, day)
                field.setText(dateFormat.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun calculate() {
        try {
            val amount = depositAmount.text.toString().toDouble()
            val rate = interestRate.text.toString().toDouble()
            val start = dateFormat.parse(startDate.text.toString())!!
            val end = dateFormat.parse(endDate.text.toString())!!

            val days = (end.time - start.time) / (1000 * 60 * 60 * 24)
            val interest = amount * rate / 365 * days
            val total = amount + interest

            daysResult.text = "Дней: $days"
            interestResult.text = "Проценты: ${"%.2f".format(interest)}"
            totalResult.text = "Итоговая сумма: ${"%.2f".format(total)}"
        } catch (e: Exception) {
            daysResult.text = "Ошибка в данных"
            interestResult.text = ""
            totalResult.text = ""
        }
    }
}
