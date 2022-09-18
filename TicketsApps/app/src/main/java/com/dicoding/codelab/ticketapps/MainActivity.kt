package com.dicoding.codelab.ticketapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val seatsView = findViewById<SeatsView>(R.id.seatsView)
        val confirmButton = findViewById<Button>(R.id.finishButton)

        confirmButton.setOnClickListener {
            seatsView.seat?.let {
                Toast.makeText(this, "Selected seat: ${it.name}", Toast.LENGTH_SHORT).show()
            } ?: kotlin.run {
                Toast.makeText(this, "Please select your seat", Toast.LENGTH_SHORT).show()
            }
        }
    }
}