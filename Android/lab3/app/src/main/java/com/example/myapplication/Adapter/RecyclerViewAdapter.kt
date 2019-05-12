package com.example.myapplication.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.myapplication.Database.Entity.Student
import com.example.myapplication.R
import kotlinx.android.synthetic.main.list.view.*

class RecyclerViewAdapter(private val students: List<Student>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>()
{
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int)
    {
        viewHolder.studentId.text = (position + 1).toString()
        viewHolder.studentFIO.text = students[position].fio
        viewHolder.studentAddTime.text = students[position].addTime
    }

    override fun getItemCount(): Int
    {
        return students.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder
    {
        val inflater = LayoutInflater.from(viewGroup.context)
        val item = inflater.inflate(R.layout.list, viewGroup, false)
        return RecyclerViewAdapter.ViewHolder(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val studentId = itemView.findViewById(R.id.studentId) as TextView
        val studentFIO = itemView.findViewById(R.id.studentFIO) as TextView
        val studentAddTime = itemView.findViewById(R.id.studentAddTime) as TextView
    }
}