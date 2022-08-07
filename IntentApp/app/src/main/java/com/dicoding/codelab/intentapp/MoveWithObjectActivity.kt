package com.dicoding.codelab.intentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MoveWithObjectActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val tvObjectData: TextView = findViewById(R.id.tv_object_received)
        val person = intent.getParcelableExtra<Person>(EXTRA_PERSON) as Person
        val textData =
            "Name: ${person.name.toString()}, \nEmail: ${person.email}, \nAge: ${person.age}, \nLocation: ${person.city}"

        tvObjectData.text = textData
    }
}