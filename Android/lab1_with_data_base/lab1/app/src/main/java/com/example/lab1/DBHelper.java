package com.example.lab1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    //класс наследник от бд SQLite
    //для упрощения интерфейса все переменные являются static и final

    public static final int DATABASE_VERSION = 1; //версия базы данных
    public static final String DATABASE_NAME = "Numbers"; //имя всей бд
    public static final String TABLE_NUMBERS = "numbers"; //имя конкретной таблицы в бд

    public static final String KEY_ID = "_id"; // id элемента в таблице
    public static final String KEY_NAME = "name"; // столбец, хранящий строковые представления целых чисел

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NUMBERS + "(" + KEY_ID + " integer primary key," + KEY_NAME + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists " + TABLE_NUMBERS);

        onCreate(db);
    }
}
