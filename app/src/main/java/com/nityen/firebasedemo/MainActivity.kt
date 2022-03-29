package com.nityen.firebasedemo

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize


const val TAG = "tag"
const val COLLECTION = "Students"

class MainActivity : AppCompatActivity() {


    private lateinit var dialog: Dialog
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterFireStore: FireStoreRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = FirebaseFirestore.getInstance()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)



        findViewById<FloatingActionButton>(R.id.fab_btn).setOnClickListener {

            dialog = Dialog(this).apply {
                this.setContentView(R.layout.add_items)
                this.setCancelable(false)
                this.window?.attributes?.width = WindowManager.LayoutParams.FILL_PARENT

                val name = this.findViewById<EditText>(R.id.name_edt)
                val email = this.findViewById<EditText>(R.id.age_edt)
                val pass = this.findViewById<EditText>(R.id.roll_edt)



                this.findViewById<Button>(R.id.save_btn).setOnClickListener {
                    val items =
                        Students(name.text.toString(), email.text.toString(), pass.text.toString())

                    db.collection(COLLECTION).document(name.text.toString()).set(items)
                        .addOnSuccessListener {
                            Log.d(TAG, "onCreate: success")
                        }.addOnFailureListener { e ->
                        Log.d(TAG, "onCreate: $e")
                    }
                    dialog.dismiss()
                }


            }
            dialog.show()
        }


//        Using recyclerView to show items
//        db.collection(COLLECTION).addSnapshotListener { v, e ->
//
//            if (e != null) {
//                Log.d(TAG, "onCreate: $e")
//                return@addSnapshotListener
//            }
//
//            if (v != null) {
//                val l = v.documents
//                val list = ArrayList<DocumentSnapshot>()
//                for (i in l) {
//                    list.add(i)
//                }
//                recyclerView.adapter = RecyclerViewAdapter(list)
//
//            }
//
//        }


//        using FireStoreRecyclerViewAdapter to show items

//        val database:FirebaseDatabase = Firebase.database
//        val ref  = database.reference.child(COLLECTION)
//        val options = FirebaseRecyclerOptions.Builder<Students>()
//            .setQuery(ref,Students::class.java).build()



        val query = db.collection(COLLECTION)
        val options =
            FirestoreRecyclerOptions.Builder<Students>().setQuery(query, Students::class.java)
                .build()
        adapterFireStore = FireStoreRecyclerViewAdapter(options)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterFireStore

//        try{
//            
//            
//        }

//            val query = db.collection(COLLECTION).orderBy("priority")
//                query.get().addOnSuccessListener {
//                for(i in it){
//                Log.d(TAG, "onCreate:${i["name"]} ")
//            }
//            }.addOnFailureListener{
//                    Log.d(TAG, "onCreate: fail")
//                }
//
//            query.addSnapshotListener(object : EventListener<QuerySnapshot?> {
//                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
//                    if (error != null) {
//                        // Handle error
//                        //...
//                        return
//                    }
//
//                    // Convert query snapshot to a list of chats
//                    val chats: List<Students> = value!!.toObjects(Students::class.java)
//
//                    Log.d(TAG, "onEvent: $chats")
//                    // Update UI
//                    // ...
//                }
//            })
//
////            Log.d(TAG, "onCreate: $query")
////            val options =
////                FirestoreRecyclerOptions.Builder<Students>().setQuery(query, Students::class.java)
////                    .build()
////            Log.d(TAG, "onCreate: $options")
////            adapter = RecyclerViewAdapter(options)
////            recyclerView.adapter = adapter
////            recyclerView.layoutManager = LinearLayoutManager(this)
//        }catch (e:Exception){
//            Log.d(TAG, "onCreate: $e")
//        }

//        db.collection(COLLECTION).get().addOnSuccessListener {
//            for(i in it){
//                Log.d(TAG, "onCreate:${i["name"]} ")
//            }
//        }.addOnFailureListener{
//
//        }


    }

    override fun onStart() {
        super.onStart()
//        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
//        adapter.stopListening()
    }


}
