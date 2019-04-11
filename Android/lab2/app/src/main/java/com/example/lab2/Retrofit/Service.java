package com.example.lab2.Retrofit;

import com.example.lab2.PojoClass.Civilization;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service
{
    @GET("data/techs.ruleset.json")
    Call<ArrayList<Civilization>> getTehnologys();
}
