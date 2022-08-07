package com.dicoding.codelab.intentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnMoveActivity: Button
    private lateinit var btnMoveWithData: Button
    private lateinit var btnMoveWithObject: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMoveActivity = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        btnMoveWithData = findViewById(R.id.btn_move_with_data)
        btnMoveWithData.setOnClickListener(this)

        btnMoveWithObject = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObject.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_move_with_data -> {
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)

                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Reky Bongso")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 28)
                startActivity(moveWithDataIntent)
            }
            R.id.btn_move_activity_object -> {
                val person = Person("Reky Bongso", 28, "rekybongso@gmail.com", "Makassar")
                val moveObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)

                moveObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveObjectIntent)
            }
        }
    }
}