package com.example.pesticide

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pesticide.databinding.ActivityBudgetBinding
import com.google.firebase.database.*

class BudgetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBudgetBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var pesticideList: MutableList<PesticideData>
    private var totalCost: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().getReference("Pesticide Directory")
        pesticideList = mutableListOf()

        val adapter = PesticideAdapter(this, R.layout.list_item_pesticide, pesticideList)

        binding.listView.adapter = adapter


        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                pesticideList.clear()
                totalCost = 0.0

                for (postSnapshot in snapshot.children) {
                    val pesticide = postSnapshot.getValue(PesticideData::class.java)
                    pesticideList.add(pesticide!!)
                }

                for (pesticide in pesticideList) {
                    var totalPrice = 0.0

                    if (!pesticide.price.isNullOrEmpty()) {
                        totalPrice = pesticide.price!!.toDouble()
                    }

                    pesticide.totalPrice = totalPrice
                    totalCost += totalPrice
                }

                adapter.notifyDataSetChanged()
                binding.totalCostView.setText("Total Cost: Lkr  " + String.format("%.2f", totalCost))
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
    }
}



