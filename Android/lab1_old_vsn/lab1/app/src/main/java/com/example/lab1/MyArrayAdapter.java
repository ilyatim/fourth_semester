package com.example.lab1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MyArrayAdapter extends ArrayAdapter<MenuItem>
{
    private String icon = "R.drawable.";
    private final Activity context;

    public MyArrayAdapter(Activity context, int menuitems, MenuItem[] names)
    {
        super(context, R.layout.menuitems, names);
        this.context = context;
    }

    // Класс для сохранения во внешний класс и для ограничения доступа
    // из потомков класса
    static class ViewHolder
    {
        public ImageView imageView;
        public TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
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
        MenuItem item = new MenuItem(position + 1);
        holder.textView.setText(item.getNumber());
        holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
        //holder.textView.setText(names[position].getNumber());
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
