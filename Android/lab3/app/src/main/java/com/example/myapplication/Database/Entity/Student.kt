package com.example.myapplication.Database.Entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Student")
class Student
{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "fio")
    var fio: String? = null

    @ColumnInfo(name = "firstName")
    var firstName: String? = null

    @ColumnInfo(name = "secondName")
    var secondName: String? = null

    @ColumnInfo(name = "thirdName")
    var thirdName: String? = null

    @ColumnInfo(name = "addTime")
    var addTime: String? = null

    constructor(fio: String?, addTime: String?)
    {
        var FIO = fio?.split(" ".toRegex())
        this.secondName = FIO?.get(0)
        this.firstName = FIO?.get(1)
        this.thirdName = FIO?.get(2)
        this.fio = fio
        this.addTime = addTime
    }
    @Ignore
    constructor(secondName: String?, firstName: String?, thirdName: String?, addTime: String?)
    {
        this.firstName = firstName
        this.secondName = secondName
        this.thirdName = thirdName
        this.addTime = addTime
    }
}