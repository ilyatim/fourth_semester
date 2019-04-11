package com.example.lab2.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.lab2.InternetConnection;
import com.example.lab2.PojoClass.Civilization;
import com.example.lab2.DataClass;
import com.example.lab2.Retrofit.RetrofitHandler;
import com.example.lab2.Retrofit.Service;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity
{
    private boolean isAlreadyTap;   //проверка: нажал ли пользователь на экран посел реконекта или нет
    private Service service = RetrofitHandler.setRetrofit().create(Service.class);  //экземпляр интерфейса service
    private MyAsyncTask myAsyncTask = (MyAsyncTask) new MyAsyncTask();  //AsyncTask для отправление запроса в доп. потоке


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        isAlreadyTap = false;
        if(!InternetConnection.isOnline(this))  //проверка на наличия интернет соединения
        {
            Toast.makeText(this, "Missing connection, please tap the screen to reconnect", Toast.LENGTH_LONG).show();
        }
        else
        {
            myAsyncTask.execute();
        }
    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void>   //класс наследник AsyncTask для выполнения интернет запроса в фоне
    {

        @Override
        protected Void doInBackground(Void... voids)
        {

            DataClass.create(); //создание будущего массива для recycle view и view pager
            /*
            *выполнение запроса и послудеющий парсинг json файла с помощью библиотеки retrofit2
            */
            Call<ArrayList<Civilization>> call = service.getTehnologys();
            try
            {
                Response<ArrayList<Civilization>> response = call.execute();
                ArrayList<Civilization> arrayList = response.body();
                assert arrayList != null;
                DataClass.addAll(arrayList);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);   //запуск нового окна
                                                                                                //после выполнения действий в AsyncTask
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent)    //переопределение касания к экрану
    {
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)  //если былы совершенно касание, выполнить действия
        {
            if(!InternetConnection.isOnline(this))
            {
                Toast.makeText(this, "Missing connection, please tap the screen to reconnect", Toast.LENGTH_LONG).show();
            }
            else if(!isAlreadyTap)
            {
                isAlreadyTap = true;
                myAsyncTask.execute();
            }
        }
        return super.onTouchEvent(motionEvent);
    }
}
