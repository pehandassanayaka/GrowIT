package com.example.fertilizeradmin.Repository

import androidx.lifecycle.MutableLiveData
import com.example.fertilizeradmin.Models.Fertilizer
import com.google.firebase.database.*

class FertilizerRepository {

    private val databaseReference : DatabaseReference=FirebaseDatabase.getInstance().getReference("Fertilizer Directory")

    @Volatile private var INSTANCE : FertilizerRepository ?= null

    fun getInstance():FertilizerRepository{
        return INSTANCE ?: synchronized(this){
            val instance = FertilizerRepository()
            INSTANCE=instance
            instance
        }
    }

    fun loadFertilizers(fertilizerList: MutableLiveData<List<Fertilizer>>){
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                try{

                    val _fertilizerList:List<Fertilizer> = snapshot.children.map{dataSnapshot ->

                        dataSnapshot.getValue(Fertilizer::class.java)!!
                    }

                    fertilizerList.postValue(_fertilizerList)

                }catch (e:Exception){

                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }



}