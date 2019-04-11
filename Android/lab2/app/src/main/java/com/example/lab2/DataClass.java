package com.example.lab2;

import com.example.lab2.PojoClass.Civilization;

import java.util.ArrayList;

public class DataClass  //класс для хранения данных
{
    private static ArrayList<Civilization> civilizations;   //хранения в классе массива, загруженного из json файла,
                                                            // для дальнейшего испоьзования в recycle view и pager view

    public static void create()
    {
        civilizations = new ArrayList<>();
    }
    public static void addAll(ArrayList<Civilization> arrayList)
    {
        civilizations.addAll(arrayList);
        civilizations.remove(0);    //т.к. json файл битый, первый элемент содержит некорректное значение, его следует удалить
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
