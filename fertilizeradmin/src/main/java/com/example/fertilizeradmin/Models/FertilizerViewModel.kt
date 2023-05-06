package com.example.fertilizeradmin.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fertilizeradmin.Repository.FertilizerRepository

class FertilizerViewModel : ViewModel() {

    private val repository : FertilizerRepository
    private val _allFertilizers = MutableLiveData<List<Fertilizer>>()
    val allFertilizers : LiveData<List<Fertilizer>> = _allFertilizers


    init {
        repository = FertilizerRepository().getInstance()
        repository.loadFertilizers(_allFertilizers)
    }

}