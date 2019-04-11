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
    private Service service = RetrofitHandler.setRetrofir().create(Service.class);
    private static String TAG = "SplashScr";
    private MyAsyncTask myAsyncTask = (MyAsyncTask) new MyAsyncTask();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(!InternetConnection.isOnline(this))
        {
            Toast.makeText(this, "Missing connection, please tap the screen to reconnect", Toast.LENGTH_LONG).show();
        }
        else
        {
            myAsyncTask.execute();
        }
    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {

            DataClass.create();
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
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
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
    public boolean onTouchEvent(MotionEvent motionEvent)
    {
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(!InternetConnection.isOnline(this))
            {
                Toast.makeText(this, "Missing connection, please tap the screen to reconnect", Toast.LENGTH_LONG).show();
            }
            else
            {
                myAsyncTask.execute();
            }
        }
        return super.onTouchEvent(motionEvent);
    }
}
