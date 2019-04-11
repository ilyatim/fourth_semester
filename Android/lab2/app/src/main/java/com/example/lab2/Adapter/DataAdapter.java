package com.example.lab2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab2.DataClass;
import com.example.lab2.R;
import com.squareup.picasso.Picasso;



public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>
{
    private LayoutInflater inflater;
    private Context context;
    private static ClickListener clickListener; //установка click listener

    public DataAdapter(Context context)
    {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataAdapter.ViewHolder viewHolder, int i)
    {
        viewHolder.textView.setText(DataClass.get(i).getName());    //установка текста
        /*Glide.with(context)   //попатка использовать библиотеку Glide для загрузки изображения
                .load(DataClass.civilizations.get(i).getGraphic())
                .into(viewHolder.imageView);*/

        Picasso.get()   //загрузка изображения через url
                .load(DataClass.get(i).getGraphic())
                .placeholder(R.drawable.civilizationlogo)
                .into(viewHolder.imageView);

    }

    @Override
    public int getItemCount()
    {
        return DataClass.getSize();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        private final ImageView imageView;
        private final TextView textView;


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) //переопределение клика на элемент recycle view
        {
            clickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ClickListener  //реализация собственного clickListener
    {
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(ClickListener clickListener) //установка собственного clickListener
    {
        DataAdapter.clickListener = clickListener;
    }
}
