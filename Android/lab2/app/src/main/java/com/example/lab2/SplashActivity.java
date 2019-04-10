package com.example.lab2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.MemoryFile;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity
{
    private Service service = RetrofitHandler.setRetrofir().create(Service.class);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /*call.enqueue(new Callback<ArrayList<Civilization>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Civilization>> call, Response<ArrayList<Civilization>> response)
            {
                if (response.body() != null)
                {
                    ArrayList<Civilization> technologies = response.body();
                    DataClass.civilizations.addAll(technologies);
                    DataClass.civilizations.remove(0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Civilization>> call, Throwable t)
            {}
        });
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 500);*/
        while(!InternetConnection.isOnline(this))
        {
            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
        }
        MyAsyncTask myAsyncTask = (MyAsyncTask) new MyAsyncTask().execute();
    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {

            DataClass.civilizations = new ArrayList<>();
            Call<ArrayList<Civilization>> call = service.getTehnologys();
            try
            {
                Response<ArrayList<Civilization>> response = call.execute();
                ArrayList<Civilization> arrayList = response.body();
                assert arrayList != null;
                DataClass.civilizations.addAll(arrayList);
                DataClass.civilizations.remove(0);
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
}
