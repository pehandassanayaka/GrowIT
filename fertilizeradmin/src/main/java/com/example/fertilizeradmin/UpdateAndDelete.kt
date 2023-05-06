package com.example.fertilizeradmin

interface UpdateAndDelete{
    fun modiifyItem(itemUID:String,isDone:Boolean)
    fun onItemDelete(itemUID:String)
}