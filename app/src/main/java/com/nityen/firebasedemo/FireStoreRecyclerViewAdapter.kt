package com.nityen.firebasedemo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestoreException
import java.util.zip.Inflater

class FireStoreRecyclerViewAdapter(option: FirestoreRecyclerOptions<Students>) :
    FirestoreRecyclerAdapter<Students, FireStoreRecyclerViewAdapter.ViewHolder>(option) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById<TextView>(R.id.name_txt)
        val age: TextView = itemView.findViewById<TextView>(R.id.age_txt)
        val roll: TextView = itemView.findViewById<TextView>(R.id.roll_txt)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FireStoreRecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items_layout, parent, false)
        Log.d(TAG, "onCreateViewHolder: ")
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: FireStoreRecyclerViewAdapter.ViewHolder,
        position: Int,
        model: Students
    ) {
        Log.d(TAG, "onBindViewHolder: ")
        holder.name.text = model.name
        holder.age.text = model.age
        holder.roll.text = model.roll
    }

    override fun onDataChanged() {
        super.onDataChanged()
        Log.d(TAG, "onDataChanged: ")
    }

    override fun onError(e: FirebaseFirestoreException) {
        super.onError(e)
        Log.d(TAG, "onError: $e")
    }
}