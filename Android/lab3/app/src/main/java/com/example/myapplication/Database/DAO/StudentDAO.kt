package com.example.myapplication.Database.DAO

import android.arch.persistence.room.*
import com.example.myapplication.Database.Entity.Student

@Dao
interface StudentDAO
{
    @get:Query("SELECT * FROM Student")
    val students: List<Student>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg students: Student)

    @Delete
    fun delete(student: Student)

    @Update
    fun update(student: Student)

    @Query("DELETE FROM Student")
    fun nukeTable()

    @Query("SELECT * FROM Student WHERE ID = (SELECT MAX(id) FROM Student)")
    fun getLastRecord(): Student

}