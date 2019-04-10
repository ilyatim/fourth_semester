package com.example.lab2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.squareup.picasso.Picasso;



public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>
{
    private static final String TAG = "DataAdapter";
    private LayoutInflater inflater;
    private Context context;

    DataAdapter(Context context)
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
        viewHolder.textView.setText(DataClass.civilizations.get(i).getName());
        /*Glide.with(context)
                .load(DataClass.civilizations.get(i).getGraphic())
                .into(viewHolder.imageView);*/

        Picasso.get()
                .load(DataClass.civilizations.get(i).getGraphic())
                .placeholder(R.drawable.civilizationlogo)
                .into(viewHolder.imageView);

    }

    @Override
    public int getItemCount()
    {
        return DataClass.civilizations.size();
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
        public void onClick(View v)
        {
            clickListener.onItemClick(v, getAdapterPosition());
        }
    }
    private static ClickListener clickListener;

    public interface ClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(ClickListener clickListener) {
        DataAdapter.clickListener = clickListener;
    }
}
