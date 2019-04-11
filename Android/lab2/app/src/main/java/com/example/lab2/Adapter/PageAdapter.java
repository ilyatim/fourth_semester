package com.example.lab2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lab2.DataClass;
import com.example.lab2.R;
import com.squareup.picasso.Picasso;

public class PageAdapter extends PagerAdapter
{
    private LayoutInflater layoutInflater;
    private Context context;

    public PageAdapter(Context context)
    {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount()
    {
        return DataClass.getSize();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o)
    {
        return view == o;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        View view = layoutInflater.inflate(R.layout.pager_item, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.ImageViewPager);
        TextView textView = (TextView) view.findViewById(R.id.textViewPager);

        if(DataClass.get(position).getHelpText() != null)   //если у технологии есть описание, использовать его в качестве текта к pager view
        {
            textView.setText(DataClass.get(position).getHelpText());
        }
        else
        {
            textView.setText("This technology has no description");
        }

        /*Glide.with(context)
                .load(DataClass.civilizations.get(position).getGraphic())
                .into(imageView);*/
        Picasso.get()   //загрузка изображения с помощью библиотеки picasso через url
                .load(DataClass.get(position).getGraphic())
                .placeholder(R.drawable.civilizationlogo)
                .into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        View view = (View) object;
        container.removeView(view);
    }

}
