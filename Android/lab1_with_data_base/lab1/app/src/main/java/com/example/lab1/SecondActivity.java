package com.example.lab1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity
{
    private ArrayList<String> list = new ArrayList<>(); //массив строк для передачи в адаптер
    private DBHelper dbHelper; //объект бд для хранения строк listView
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findById(); //поиск view элементов по их id
        fill(); //заполнения массива list из бд

        ArrayAdapter<String> adapter = new MyArrayAdapter(this, R.layout.menuitems, list); //подключение адаптера к массиву
        listView.setAdapter(adapter); //установка адаптера для listView

        dbHelper.close(); //прекращение работы с базой данных
    }
    private void fill()
    {
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase(); //загрузка бд из памяти

        //подключение к бд курсора(указатель на строчку)
        Cursor cursor = database.query(DBHelper.TABLE_NUMBERS, null, null, null, null, null, null);
        cursor.moveToFirst(); //установка курсора на 1 значение в бд

        int indexOfNumber = cursor.getColumnIndex(DBHelper.KEY_NAME); //получение номера столбца со строковыми представлениями целых чисел

        do
        {
            String number = cursor.getString(indexOfNumber); //получение строкового значения из бд
            list.add(number);
        }
        while(cursor.moveToNext());
        cursor.close(); //завершение работы с курсором
    }
    private void findById() //функция поиска view элементов по их id
    {
        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void onDestroy() //переопределение родительского метода onDestroy
    {
        super.onDestroy();
    }
}
