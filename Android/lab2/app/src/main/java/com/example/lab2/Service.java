package com.example.lab2;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service
{
    @GET("data/techs.ruleset.json")
    Call<ArrayList<Civilization>> getTehnologys();
}
