package com.example.lab2;

import com.example.lab2.PojoClass.Civilization;

import java.util.ArrayList;

public class DataClass
{
    private static ArrayList<Civilization> civilizations;

    public static void create()
    {
        civilizations = new ArrayList<>();
    }
    public static void addAll(ArrayList<Civilization> arrayList)
    {
        civilizations.addAll(arrayList);
        civilizations.remove(0);
    }
    public static Civilization get(int index)
    {
        return civilizations.get(index);
    }
    public static int getSize()
    {
        return civilizations.size();
    }
}
