package com.example.firebaserealtimedatabase.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firebaserealtimedatabase.R
import com.example.firebaserealtimedatabase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button :Button = findViewById(R.id.insert)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        binding.fetch.setOnClickListener {
            val intent = Intent( this, MainActivity3::class.java)
            startActivity(intent)
        }

    }
}