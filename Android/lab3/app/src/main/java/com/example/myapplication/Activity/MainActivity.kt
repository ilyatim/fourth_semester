package com.example.myapplication.Activity

import android.annotation.SuppressLint
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.Database.MyDatabase
import com.example.myapplication.Database.Entity.Student
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity() : AppCompatActivity()
{
    lateinit var database: MyDatabase
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val MIGRATION_1_2 = object : Migration(1, 2)
        {
            override fun migrate(database: SupportSQLiteDatabase)
            {

            }
        }
        database = Room.databaseBuilder<MyDatabase>(this, MyDatabase::class.java, "studentDB")
            .addMigrations(MIGRATION_1_2)
            .allowMainThreadQueries()
            .build()
        database.studentDAO().nukeTable()

        for(i in 1..5)
        {
            database.studentDAO().insertAll(Student(resources.getStringArray(R.array.students).random(),
                                                    getDate()))
        }
        setOnClickListener()

    }

    private fun setOnClickListener()
    {
        button1.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
        button2.setOnClickListener {
            database.studentDAO().insertAll(Student(resources.getStringArray(R.array.students).random(),
                                                    getDate()))
        }
        button3.setOnClickListener {
            val student = database.studentDAO().getLastRecord()
            student.firstName = "Иван"
            student.secondName = "Иванов"
            student.thirdName = "Иванович"
            database.studentDAO().update(student)
        }
    }

    override fun onDestroy()
    {
        database.close()
        super.onDestroy()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(): String
    {
        val dateFormatter = SimpleDateFormat("hh:mm:ss")
        dateFormatter.isLenient = false
        val today = Date()
        return dateFormatter.format(today)
    }
}
