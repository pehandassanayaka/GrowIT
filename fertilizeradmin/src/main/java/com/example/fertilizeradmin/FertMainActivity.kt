package com.example.fertilizeradmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.fertilizeradmin.databinding.ActivityMainBinding



class FertMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val pkgnme = "com.example.fertilizers"
    val classnme = "com.example.fertilizers.FertMainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainAdd.setOnClickListener{
            val intent = Intent(this@FertMainActivity,AddFertActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.mainUpdate.setOnClickListener{
            val intent = Intent(this@FertMainActivity,UpdateFertActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.mainDelete.setOnClickListener{
            val intent = Intent(this@FertMainActivity,DeleteFertActivity::class.java)
            startActivity(intent)
            finish()
        }

//        binding.mainView.setOnClickListener {
//            val intent = Intent().apply {
//                setClassName(pkgnme, classnme)
//                putExtra("data", "Hello from FertMainActivity")
//            }
//            startActivity(intent)
//            finish()
//        }

        binding.mainList.setOnClickListener{
            val intent = Intent(this@FertMainActivity,ListFertilizers::class.java)
            startActivity(intent)
            finish()
        }
        binding.mainView.setOnClickListener{
            val intent = Intent(this@FertMainActivity,SearchFertActivity::class.java)
            startActivity(intent)
            finish()
        }
//        binding.mainToDo.setOnClickListener{
//            val intent = Intent(this@FertMainActivity,ToDoActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
        binding.mainToDo.setOnClickListener{
            val intent = Intent(this@FertMainActivity,SchedulingActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.calculateBudget.setOnClickListener{
            val intent = Intent(this@FertMainActivity,FertCalculator::class.java)
            startActivity(intent)
            finish()
        }







    }
}