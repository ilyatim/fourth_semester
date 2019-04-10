package com.example.lab2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.MemoryFile;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity
{
    //W/libEGL: EGLNativeWindowType 0x7e84592010 disconnect failed
    private Service service = RetrofitHandler.setRetrofir().create(Service.class);
    private static final String TAG = "SplashScr";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        DataClass.civilizations = new ArrayList<>();
        Call<ArrayList<Civilization>> call = service.getTehnologys();
        call.enqueue(new Callback<ArrayList<Civilization>>()
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
                Log.d(TAG, "onResponse: " + response.code());
            }

            @Override
            public void onFailure(Call<ArrayList<Civilization>> call, Throwable t)
            {
                Log.d(TAG, "onFailure: " + "fail");
            }
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
        }, 500);

        //MyAsyncTask myAsyncTask = (MyAsyncTask) new MyAsyncTask().execute();
    }

    /*class MyAsyncTask extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids)
        {


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);

        }
    }*/

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
