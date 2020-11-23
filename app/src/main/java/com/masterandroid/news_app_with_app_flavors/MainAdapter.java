package com.masterandroid.news_app_with_app_flavors;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> implements RecyclerInterfaceClick {

    private ArrayList<NewsData> newsList;
    private Activity activity;
    private RecyclerInterfaceClick interfaceClick;
    public MainAdapter(ArrayList<NewsData> newsList, Activity activity,RecyclerInterfaceClick interfaceClick1) {
        this.newsList = newsList;
        this.activity = activity;
        this.interfaceClick=interfaceClick1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        NewsData data=newsList.get(position);
        Picasso.get().load(data.getNews_image()).into(holder.news_img);
        holder.news_source.setText(data.getNews_source());
        holder.news_title.setText(data.getNews_title());
        holder.news_published.setText(data.getPublishedOn());

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemLongClick(int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView news_img;
        TextView news_title;
        TextView news_source;
        TextView news_published;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            news_img=itemView.findViewById(R.id.newsImage);
            news_title=itemView.findViewById(R.id.newsTitle);
            news_source=itemView.findViewById(R.id.newsSource);
            news_published=itemView.findViewById(R.id.publishDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interfaceClick.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
