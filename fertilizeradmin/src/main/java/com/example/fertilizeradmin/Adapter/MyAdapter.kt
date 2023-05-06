package com.example.fertilizeradmin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fertilizeradmin.Models.Fertilizer
import com.example.fertilizeradmin.R

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private  val  fertilizerList = ArrayList<Fertilizer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(
            R.layout.fertilizer_item,
            parent,false

        )
        return MyViewHolder(itemview)
    }

    override fun getItemCount(): Int {

        return fertilizerList.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = fertilizerList[position]

        holder.fertID.text=currentitem.fertID
        holder.fertName.text=currentitem.fertName
        holder.quantity.text=currentitem.quantity
        holder.price.text=currentitem.price
        holder.des.text=currentitem.description



    }

    fun updateFetilizerList(fertilizerList: List<Fertilizer>){
        this.fertilizerList.clear()
        this.fertilizerList.addAll(fertilizerList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val fertID : TextView = itemView.findViewById(R.id.tvFertID)
        val fertName : TextView = itemView.findViewById(R.id.tvFertName)
        val quantity : TextView = itemView.findViewById(R.id.tvFertQuantity)
        val price : TextView = itemView.findViewById(R.id.tvFertPrice)
        val des : TextView = itemView.findViewById(R.id.tvFertDes)


    }

}