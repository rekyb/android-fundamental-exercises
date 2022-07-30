package com.dicoding.codelab.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtPanjang: EditText
    private lateinit var edtLebar: EditText
    private lateinit var edtTinggi: EditText
    private lateinit var btnHitung: Button
    private lateinit var tvHasil: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtPanjang = findViewById(R.id.edt_panjang)
        edtLebar = findViewById(R.id.edt_lebar)
        edtTinggi = findViewById(R.id.edt_tinggi)
        btnHitung = findViewById(R.id.btn_hitung)
        tvHasil = findViewById(R.id.tv_hasil)

        btnHitung.setOnClickListener(this)

        if (savedInstanceState != null) {
            val hasilSaveState = savedInstanceState.getString(STATE_RESULT)
            tvHasil.text = hasilSaveState
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvHasil.text.toString())
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.btn_hitung) {
            val inputPanjang = edtPanjang.text.toString().trim()
            val inputLebar = edtLebar.text.toString().trim()
            val inputTinggi = edtTinggi.text.toString().trim()

            var isItEmpty = false

            if (inputPanjang.isEmpty()) {
                isItEmpty = true
                edtPanjang.error = "Field tidak boleh kosong"
            }

            if (inputLebar.isEmpty()) {
                isItEmpty = true
                edtLebar.error = "Field tidak boleh kosong"
            }

            if (inputTinggi.isEmpty()) {
                isItEmpty = true
                edtTinggi.error = "Field tidak boleh kosong"
            }

            if (!isItEmpty) {
                val volume =
                    inputPanjang.toDouble() * inputLebar.toDouble() * inputTinggi.toDouble()
                tvHasil.text = volume.toString()
            }
        }
    }
}