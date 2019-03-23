package com.example.lab1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.sql.Time;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private Timer timer; //экземпляр класса Timer
    private ImageView imageView; //картинка для создания анимации
    private Animation animation1; //анимация для imageView
    private final int max_size = 1000000; //размер массива который будет использоваться во 2 активити, макс - 1000000

    private DBHelper dbHelper; //объект базы данных для хранения строк listView

    class Timer extends Thread //потом для запуска нового активити
    {
        private Context context;

        Timer(Context context)
        {
            this.context=context;
        }
        @Override
        public void run()
        {
            try
            {
                sleep(3000);
            } catch(java.lang.InterruptedException ex)
            {
                ex.printStackTrace();
            }
            if(!isChangingConfigurations()) //проверка на поворот экрана
            {
                Intent intent = new Intent(context, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findById(); //поиск view элементов в activity

        dbHelper = new DBHelper(this);

        animation1 = AnimationUtils.loadAnimation(this, R.anim.mytrans); //установка анимации для картинки
        setAnimation(imageView); //запуск анимации

        MyAsyncTask task = (MyAsyncTask) new MyAsyncTask().execute(); //заполнение бд в выделенном потоке

        timer = new Timer(this);
        timer.start(); //запуск нового потока для открытия нового activity

    }

    public void findById() //функция для поиска view элементов в activity
    {
        imageView = (ImageView)findViewById(R.id.imageView3);
    }

    @SuppressLint("StaticFieldLeak")
    class MyAsyncTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            database.delete(DBHelper.TABLE_NUMBERS, null, null);
            for(int i = 1; i <= max_size; i++) //заполнение в цикле бд конвертируемыми строковыми значениями
            {
                MenuItem item = new MenuItem(i);
                contentValues.put(DBHelper.KEY_ID, i);
                contentValues.put(DBHelper.KEY_NAME, item.getNumber());
                database.insert(DBHelper.TABLE_NUMBERS, null, contentValues);
            }
            return null;
        }
    }
    public void setAnimation(View view) //запуск анимации
    {
        imageView.startAnimation(animation1);
    }

    @Override
    protected void onDestroy() //переопредление метода onDestroy родительского класса
    {
        super.onDestroy();
    }
}
