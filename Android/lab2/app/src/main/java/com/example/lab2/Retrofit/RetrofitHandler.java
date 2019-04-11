package com.example.lab2.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHandler //класс для обработки результатов retrofit
{
    private static final String baseURL = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/";
    private static Retrofit retrofit = null;
    public static Retrofit setRetrofit()
    {
        if(retrofit == null)
            retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
