package com.example.explict_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.explict_2.User
import com.example.explict_2.MainActivity2

class MainActivity : AppCompatActivity() {

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val dataBalasan = result.data?.getStringExtra("EXTRA_BALASAN")
            // Misal kita tampilkan balasan di Toast atau TextView
            Toast.makeText(this, "Balasan: $dataBalasan", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val etPesan = findViewById<EditText>(R.id.etPesan)
        val btnKirim = findViewById<Button>(R.id.btnKirim)

        btnKirim.setOnClickListener {
            val pesan = etPesan.text.toString()
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("EXTRA_PESAN", pesan)

            // 2. JANGAN pakai startActivity(intent) lagi!
            // Gunakan launcher yang sudah kita buat tadi:
            resultLauncher.launch(intent)

            val userBaru = User(
                nama = "Budi Doremi",
                email = "budi@email.com",
                umur = 25
            )

            val u_intent = Intent(this, MainActivity2::class.java)
            u_intent.putExtra("EXTRA_USER", userBaru)
            startActivity(u_intent)

        }
    }
}