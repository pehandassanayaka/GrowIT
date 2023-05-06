package com.example.pesticide

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DosageCalcActivity : AppCompatActivity() {
    private var cropTypeSpinner: Spinner? = null
    private var pestSeveritySpinner: Spinner? = null
    private var treatingAreaEditText: EditText? = null
    private var pesticideSpinner: Spinner? = null
    private var calculateButton: Button? = null
    private var dosageTextView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dosagecalc)

        // Get references to UI elements
        cropTypeSpinner = findViewById<Spinner>(R.id.cropTypeSpinner)
        pestSeveritySpinner = findViewById<Spinner>(R.id.pestSeveritySpinner)
        treatingAreaEditText = findViewById<EditText>(R.id.treatingAreaEditText)
        pesticideSpinner = findViewById<Spinner>(R.id.pesticideSpinner)
        calculateButton = findViewById<Button>(R.id.calculateButton)
        dosageTextView = findViewById<TextView>(R.id.dosageTextView)

        // Set up the spinners with the list of crop types and pest severities
        val cropTypeAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.crop_types,
            android.R.layout.simple_spinner_item
        )
        cropTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cropTypeSpinner!!.adapter = cropTypeAdapter

        val pestSeverityAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.pest_severities,
            android.R.layout.simple_spinner_item
        )
        pestSeverityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        pestSeveritySpinner!!.adapter = pestSeverityAdapter


        // Set up the spinner with the list of pesticides
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.pesticide_names,
            android.R.layout.simple_spinner_item,
            //arrayOf("Pesticide A", "Pesticide B", "Pesticide C")
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        pesticideSpinner!!.adapter = adapter

        // Set up the button click listener
        calculateButton!!.setOnClickListener(View.OnClickListener { calculateDosage() })
    }

    private fun calculateDosage() {
        // Get the inputs from the user
        val cropType = cropTypeSpinner!!.selectedItem.toString()
        val pestSeverity = pestSeveritySpinner!!.selectedItem.toString()
        val treatingArea = treatingAreaEditText!!.text.toString().toDouble()
        val selectedPesticide = pesticideSpinner!!.selectedItem as String

        // Perform the calculation based on the selected pesticide
        var dosage = 0.0
        when (selectedPesticide) {
            "Pesticide A" -> dosage =
                calculateDosageForPesticideA(cropType, pestSeverity, treatingArea)
            "Pesticide B" -> dosage =
                calculateDosageForPesticideB(cropType, pestSeverity, treatingArea)
            "Pesticide C" -> dosage =
                calculateDosageForPesticideC(cropType, pestSeverity, treatingArea)
        }

        // Display the calculated dosage
        dosageTextView!!.text = String.format("%.2f ml", dosage)
    }

    private fun calculateDosageForPesticideA(
        cropType: String,
        pestSeverity: String,
        treatingArea: Double
    ): Double {
        // TODO: Implement the formula for Pesticide A dosage calculation
        var dosage = 0.0
        if (cropType.equals("Paddy")) {
            if (pestSeverity.equals("Low")) {
                dosage = treatingArea * 0.25 * 1000
            } else if (pestSeverity.equals("Medium")) {
                dosage = treatingArea * 0.5 * 1000
            } else if (pestSeverity.equals("High")) {
                dosage = treatingArea * 1 * 1000
            }
        } else if (cropType.equals("Wheat")) {
            if (pestSeverity.equals("Low")) {
                dosage = treatingArea * 0.5 * 1000
            } else if (pestSeverity.equals("Medium")) {
                dosage = treatingArea * 1 * 1000
            } else if (pestSeverity.equals("High")) {
                dosage = treatingArea * 2 * 1000
            }
        }
        return dosage
    }

    private fun calculateDosageForPesticideB(
        cropType: String,
        pestSeverity: String,
        treatingArea: Double
    ): Double {
        // TODO: Implement the formula for Pesticide B dosage calculation
        var dosage = 0.0
        if (cropType.equals("Paddy")) {
            if (pestSeverity.equals("Low")) {
                dosage = treatingArea * 0.25 * 1000
            } else if (pestSeverity.equals("Medium")) {
                dosage = treatingArea * 0.5 * 1000
            } else if (pestSeverity.equals("High")) {
                dosage = treatingArea * 1 * 1000
            }
        } else if (cropType.equals("Wheat")) {
            if (pestSeverity.equals("Low")) {
                dosage = treatingArea * 0.5 * 1000
            } else if (pestSeverity.equals("Medium")) {
                dosage = treatingArea * 1 * 1000
            } else if (pestSeverity.equals("High")) {
                dosage = treatingArea * 2 * 1000
            }
        }
        return dosage
    }

    private fun calculateDosageForPesticideC(
        cropType: String,
        pestSeverity: String,
        treatingArea: Double
    ): Double {
        // TODO: Implement the formula for Pesticide C dosage calculation
        var dosage = 0.0
        if (cropType.equals("Paddy")) {
            if (pestSeverity.equals("Low")) {
                dosage = treatingArea * 0.25 * 1000
            } else if (pestSeverity.equals("Medium")) {
                dosage = treatingArea * 0.5 * 1000
            } else if (pestSeverity.equals("High")) {
                dosage = treatingArea * 1 * 1000
            }
        } else if (cropType.equals("Wheat")) {
            if (pestSeverity.equals("Low")) {
                dosage = treatingArea * 0.5 * 1000
            } else if (pestSeverity.equals("Medium")) {
                dosage = treatingArea * 1 * 1000
            } else if (pestSeverity.equals("High")) {
                dosage = treatingArea * 2 * 1000
            }
        }
        return dosage
    }
}