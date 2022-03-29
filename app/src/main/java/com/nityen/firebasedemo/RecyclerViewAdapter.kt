package com.nityen.firebasedemo

import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot

class RecyclerViewAdapter(private val list:List<DocumentSnapshot>):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById<TextView>(R.id.name_txt)
        val age: TextView = itemView.findViewById<TextView>(R.id.age_txt)
        val roll: TextView = itemView.findViewById<TextView>(R.id.roll_txt)

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items_layout, parent, false)
        return ViewHolder(v)
    }



    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
       val i = list[position]
           Log.d(TAG, "onBindViewHolder: ${i["age"]}")
           holder.name.text = i["name"].toString()
           holder.age.text = i["age"].toString()
           holder.roll.text =i["roll"].toString()


    }

    override fun getItemCount(): Int {
        return list.size
    }
}