package com.example.myapplication.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.myapplication.Database.DAO.StudentDAO
import com.example.myapplication.Database.Entity.Student

@Database(entities = [Student::class], version = 2, exportSchema = false)

abstract class MyDatabase: RoomDatabase()
{
    abstract fun studentDAO(): StudentDAO
}