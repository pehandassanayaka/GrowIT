package com.example.pesticide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PesticideAdapter(context: Context, private val resource: Int, private val items: List<PesticideData>)
    : ArrayAdapter<PesticideData>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        if (view == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(resource, parent, false)
        }

        val nameTextView = view?.findViewById<TextView>(R.id.nameTextView)
        val priceTextView = view?.findViewById<TextView>(R.id.priceTextView)
        //val daysTextView = view?.findViewById<TextView>(R.id.daysTextView)

        val item = items[position]

        nameTextView?.text = item.name
        priceTextView?.text = item.price
        //daysTextView?.text = item.applicationDays.toString()

        return view!!
    }
}