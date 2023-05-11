package com.example.fertilizeradmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fertilizeradmin.databinding.ActivityFertCalculatorBinding
import com.example.fertilizeradmin.databinding.ActivityMainBinding

class FertCalculator : AppCompatActivity()
{

    private lateinit var binding: ActivityFertCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityFertCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.weightPicker.minValue = 30
        binding.weightPicker.maxValue = 150

        binding.pricePicker.minValue = 100
        binding.pricePicker.maxValue = 250

        binding.weightPicker.setOnValueChangedListener{ _,_,_ ->
            calculateBMI()
        }

        binding.pricePicker.setOnValueChangedListener{ _,_,_ ->
            calculateBMI()
        }

    }

    private fun calculateBMI()
    {
        val price = binding.pricePicker.value
        val doublePrice = price.toDouble()

        val weight = binding.weightPicker.value

        val budget = weight.toDouble() * doublePrice

        binding.resultsTV.text = String.format("Your Budget is (Rs.): %.2f", budget)


    }


}






