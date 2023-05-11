package com.example.fertilizeradmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.fertilizeradmin.databinding.ActivityAddBinding
import com.example.fertilizeradmin.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val pkgnme = "com.example.fertilizers"
    val classnme = "com.example.fertilizers.MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainAdd.setOnClickListener{
            val intent = Intent(this@MainActivity,AddActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainUpdate.setOnClickListener{
            val intent = Intent(this@MainActivity,UpdateActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.mainDelete.setOnClickListener{
            val intent = Intent(this@MainActivity,DeleteActivity::class.java)
            startActivity(intent)
            finish()
        }

//        binding.mainView.setOnClickListener {
//            val intent = Intent().apply {
//                setClassName(pkgnme, classnme)
//                putExtra("data", "Hello from MainActivity")
//            }
//            startActivity(intent)
//            finish()
//        }

        binding.mainList.setOnClickListener{
            val intent = Intent(this@MainActivity,ListFertilizers::class.java)
            startActivity(intent)
            finish()
        }
        binding.mainView.setOnClickListener{
            val intent = Intent(this@MainActivity,SearchActivity::class.java)
            startActivity(intent)
            finish()
        }
//        binding.mainToDo.setOnClickListener{
//            val intent = Intent(this@MainActivity,ToDoActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
        binding.mainToDo.setOnClickListener{
            val intent = Intent(this@MainActivity,SchedulingActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.calculateBudget.setOnClickListener{
            val intent = Intent(this@MainActivity,FertCalculator::class.java)
            startActivity(intent)
            finish()
        }







    }
}