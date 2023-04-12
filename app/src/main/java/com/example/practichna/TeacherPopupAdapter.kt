package com.example.practichna

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TeacherPopupAdapter(private val teacherPopupList: ArrayList<String>) :
    RecyclerView.Adapter<TeacherPopupAdapter.TeacherPopupViewHolder>() {

    class TeacherPopupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameGroup: TextView = itemView.findViewById(R.id.nameGroup)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherPopupViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_popup_teachers, parent, false)
        return TeacherPopupViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return teacherPopupList.size
    }

    override fun onBindViewHolder(holder: TeacherPopupViewHolder, position: Int) {
        holder.nameGroup.text = teacherPopupList[position]
    }
}