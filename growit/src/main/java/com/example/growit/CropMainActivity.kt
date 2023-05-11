package com.example.growit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.growit.databinding.ActivityMainBinding

class CropMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainUpload.setOnClickListener{
            val intent = Intent(this@CropMainActivity, CropUploadActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainUpdate.setOnClickListener{
            val intent = Intent(this@CropMainActivity, CropUpdateActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainDelete.setOnClickListener{
            val intent = Intent(this@CropMainActivity, CropDeleteActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainView.setOnClickListener{
            val intent = Intent(this@CropMainActivity, CropViewActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainCal.setOnClickListener{
            val intent = Intent(this@CropMainActivity, CropCalActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}