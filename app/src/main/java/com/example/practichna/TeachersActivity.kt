package com.example.practichna

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class TeachersActivity : AppCompatActivity(), TeacherAdapter.TeacherClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var teacherList: ArrayList<Teacher>
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teachers)

        recyclerView = findViewById(R.id.teacherRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        teacherList = arrayListOf()

        db = FirebaseFirestore.getInstance()

        db.collection("teacher").get().addOnSuccessListener {
            if(!it.isEmpty){
                for(data in it.documents){
                    val teacher:Teacher? = data.toObject(Teacher::class.java)
                    if (teacher != null) {
                        teacherList.add(teacher)
                    }
                }
                recyclerView.adapter = TeacherAdapter(teacherList, this)
            }
        }
            .addOnFailureListener{
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }


    }

    override fun onClick(position: Int) {
        when(position){
            0 -> showPopupTeachers("teacherOne")
            1 -> showPopupTeachers("teacherTwo")
            2 -> showPopupTeachers("teacherThree")
        }
    }
    @SuppressLint("MissingInflatedId")
    private fun showPopupTeachers(teacher:String){
        val builder = AlertDialog.Builder(this)
        val customViewTeacher = LayoutInflater.from(this).inflate(R.layout.popup_teachers, null)
        builder.setView(customViewTeacher)

        recyclerView = customViewTeacher.findViewById(R.id.teacherPopupRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        db = FirebaseFirestore.getInstance()

        db.collection("teacherGroup").document(teacher).get().addOnSuccessListener {
            recyclerView.adapter = TeacherPopupAdapter(it.data?.get("nameGroup") as ArrayList<String>)
        }
            .addOnFailureListener{
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }

        val popupTeacher = builder.create()
        popupTeacher.show()
    }
}

