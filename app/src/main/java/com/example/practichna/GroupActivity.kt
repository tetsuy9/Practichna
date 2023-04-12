package com.example.practichna

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GroupActivity : AppCompatActivity(), GroupAdapter.GroupClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var groupList: ArrayList<com.example.practichna.Group>
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)

        recyclerView = findViewById(R.id.groupRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        groupList = arrayListOf()

        db = FirebaseFirestore.getInstance()

        db.collection("groups").get().addOnSuccessListener {
            if(!it.isEmpty){
                for(data in it.documents){
                    val group:com.example.practichna.Group? = data.toObject(com.example.practichna.Group::class.java)
                    if (group != null) {
                        groupList.add(group)
                    }
                }
                recyclerView.adapter = GroupAdapter(groupList, this)
            }
        }

            .addOnFailureListener{
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
    }
    override fun onClick(position: Int) {
        when(position){
            0 -> showPopupGroups("P-42")
            1 -> showPopupGroups("P-41")
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun showPopupGroups(group:String){
        val builder = AlertDialog.Builder(this)
        val customViewGroup = LayoutInflater.from(this).inflate(R.layout.popup_groups, null)
        builder.setView(customViewGroup)

        val popupTeacher = builder.create()
        popupTeacher.show()

        recyclerView = customViewGroup.findViewById(R.id.groupPopupRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        db = FirebaseFirestore.getInstance()

        db.collection("groupShedule").document(group).collection(
            SimpleDateFormat("EEEE", Locale.ENGLISH).format(
                Date().time
            ))
            .document("subjects").get().addOnSuccessListener {
                recyclerView.adapter = GroupPopupAdapter((it.data?.get("subject") as ArrayList<String>))
            }

            .addOnFailureListener{
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }

    }
}