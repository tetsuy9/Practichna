package com.example.practichna

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import javax.security.auth.Subject

class GroupPopupAdapter(private val groupPopupList: ArrayList<String>) :
    RecyclerView.Adapter<GroupPopupAdapter.GroupPopupViewHolder>() {

    class GroupPopupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subject: TextView = itemView.findViewById(R.id.subject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupPopupViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_popup_group, parent, false)
        return GroupPopupViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return groupPopupList.size
    }

    override fun onBindViewHolder(holder: GroupPopupViewHolder, position: Int) {
        holder.subject.text = groupPopupList[position]

    }
}