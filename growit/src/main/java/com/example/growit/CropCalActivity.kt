package com.example.growit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.growit.databinding.ActivityCalBinding

class CropCalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalBinding

    private var harvestButton: Button? = null
    private var land: EditText? = null
    private var harvest: EditText? = null
    private var calHarvest: EditText? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        harvestButton = findViewById<Button>(R.id.harvestButton)
        land = findViewById<EditText>(R.id.land)
        harvest = findViewById<EditText>(R.id.harvest)
        calHarvest = findViewById<EditText>(R.id.calHarvest)

        binding.harvestButton?.setOnClickListener {
            val a: Int = land?.text.toString().toInt()
            val b: Int = harvest?.text.toString().toInt()
            val c: Int = a * b
            calHarvest?.setText(c.toString())
        }
    }
}
