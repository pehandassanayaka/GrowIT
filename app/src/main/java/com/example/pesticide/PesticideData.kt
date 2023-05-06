package com.example.pesticide

data class PesticideData(val name: String? = null,
                         val price: String?= null,
                         val cropType: String?= null,
                         val volume: String?= null,
                         val expDate: String?= null,
                         var totalPrice: Double = 0.0){

}
