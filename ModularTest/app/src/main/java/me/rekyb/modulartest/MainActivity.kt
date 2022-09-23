package me.rekyb.modulartest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import me.rekyb.featruea.MainActivityOne

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMainOne = findViewById<Button>(R.id.btn_main_one)
        btnMainOne.setOnClickListener {
            startActivity(Intent(this, MainActivityOne::class.java))
        }
    }
}