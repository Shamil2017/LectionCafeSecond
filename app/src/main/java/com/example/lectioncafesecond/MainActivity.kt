package com.example.lectioncafesecond

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextName = findViewById(R.id.editTextTextName)
        editTextPassword = findViewById(R.id.editTextPassword)

    }

    fun onClickCreateOrder(view: View) {
        val name = editTextName.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (!name.isEmpty() && !password.isEmpty()) {
            val intent = Intent(this, active_main_order::class.java)
            intent.putExtra("name", name)
            intent.putExtra("password", password)
            startActivity(intent)
        } else {
            Toast.makeText(this, R.string.showWarning, Toast.LENGTH_SHORT).show()
        }
    }
}