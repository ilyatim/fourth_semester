package com.example.lab2.Retrofit;

import com.example.lab2.PojoClass.Civilization;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service    //интерфейс для управления адресом
{
    @GET("data/techs.ruleset.json") //get запрос для retrofit
    Call<ArrayList<Civilization>> getTehnologys();
}
