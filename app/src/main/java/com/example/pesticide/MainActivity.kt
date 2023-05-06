package com.example.pesticide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pesticide.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, UploadActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainUpdate.setOnClickListener{
            val intent = Intent(this@MainActivity, UpdateActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainDelete.setOnClickListener{
            val intent = Intent(this@MainActivity, DeleteActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainCalender.setOnClickListener{
            val intent = Intent(this@MainActivity, CalenderActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainBudget.setOnClickListener{
            val intent = Intent(this@MainActivity, BudgetActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainDosage.setOnClickListener{
            val intent = Intent(this@MainActivity, DosageCalcActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainSearch.setOnClickListener{
            val intent = Intent(this@MainActivity, ViewActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}