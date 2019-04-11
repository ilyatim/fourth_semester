package com.example.lab2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class InternetConnection //класс для проверки интернет соединения
{
    public static boolean isOnline(Context context)
    {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    public static void showToast(Context context) //выводит сообщение об ошибке в активити
    {
        if(!InternetConnection.isOnline(context))
        {
            Toast.makeText(context, "Missing connection", Toast.LENGTH_SHORT).show();
        }
    }
}
