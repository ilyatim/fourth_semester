package com.example.myapplication.Database.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Student")
class Student
{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    @ColumnInfo(name = "fio")
    var fio: String? = null
    @ColumnInfo(name = "addTime")
    var addTime: String? = null

    constructor(fio: String?, addTime: String?)
    {
        this.fio = fio
        this.addTime = addTime
    }
}