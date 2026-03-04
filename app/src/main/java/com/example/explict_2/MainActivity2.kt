package com.example.explict_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.explict_2.User
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.explict_2.R
import android.os.Build

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        // Pastikan ID ini ada di layout activity_main2.xml
        val tvHasilPesan = findViewById<TextView>(R.id.tvHasilPesan)
        val etBalasan = findViewById<EditText>(R.id.etBalasan)
        val btnBalas = findViewById<Button>(R.id.btnBalas)

        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Cara untuk API 33 ke atas
            intent.getParcelableExtra("EXTRA_USER", User::class.java)
        } else {
            // Cara lama untuk API 32 ke bawah
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<User>("EXTRA_USER")
        }

        val pesanDiterima = intent.getStringExtra("EXTRA_PESAN")

        if (user != null) {
            val info = "Nama: ${user.nama}\nEmail: ${user.email}\nUmur: ${user.umur}\n\nMotivasi: $pesanDiterima"
            tvHasilPesan.text = info
        } else {
            tvHasilPesan.text = pesanDiterima ?: "Data Kosong"
        }

        btnBalas.setOnClickListener {
            val balasan = etBalasan.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("EXTRA_BALASAN", balasan)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}