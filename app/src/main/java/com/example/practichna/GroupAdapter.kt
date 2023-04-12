package com.example.practichna

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView

class GroupAdapter(private val groupList: ArrayList<com.example.practichna.Group>, val listener: GroupActivity) : RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {
    inner class GroupViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val gName : TextView = itemView.findViewById(R.id.nameGroup)

        init {
            itemView.setOnClickListener{
                val position = adapterPosition
                listener.onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent,false)
        return GroupViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return groupList.size
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.gName.text = groupList[position].nameGroup
    }
    interface GroupClickListener{
        fun onClick(position: Int)
    }
}