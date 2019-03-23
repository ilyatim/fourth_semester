package com.example.lab1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public class MyArrayAdapter extends ArrayAdapter<String>
{
    private String icon = "R.drawable."; //путь до картинок
    private final Activity context;
    private final ArrayList<String> list; //массив строк со строковыми значениями целых чисел

    public MyArrayAdapter(Activity context, int menuitems, ArrayList<String> names)
    {
        super(context, R.layout.menuitems, names);
        this.context = context;
        this.list = names;
    }

    // Класс для сохранения во внешний класс и для ограничения доступа
    // из потомков класса
    static class ViewHolder
    {
        public ImageView imageView;
        public TextView textView;
    }

    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent)
    {
        // ViewHolder буферизирует оценку различных полей шаблона элемента

        ViewHolder holder;
        View rowView = convertView;

        if (rowView == null)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.menuitems, null, false);
            holder = new ViewHolder();
            holder.textView = (TextView) rowView.findViewById(R.id.label);
            holder.imageView = (ImageView) rowView.findViewById(R.id.icon);
            rowView.setTag(holder);
        } else
        {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.textView.setText(list.get(position));
        holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        Random random = new Random();
        holder.imageView.setImageResource(getImageId(context, "m" + String.valueOf(random.nextInt(98) + 1)));
        if (position % 2 == 1)
            rowView.setBackgroundColor(Color.parseColor("#CCCCCC"));
        else
            rowView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        return rowView;
    }
    private static int getImageId(Context context, String imageName)
    {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
