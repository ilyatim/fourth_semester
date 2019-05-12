package com.example.myapplication.Activity

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Adapter.RecyclerViewAdapter
import com.example.myapplication.Database.MyDatabase
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity()
{
    lateinit var database: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        database = Room.databaseBuilder<MyDatabase>(this, MyDatabase::class.java, "studentDB")
            .allowMainThreadQueries()
            .build()
        recyclerView.adapter = RecyclerViewAdapter(database.studentDAO().students)
    }
    override fun onDestroy()
    {
        database.close()
        super.onDestroy()
    }
}
