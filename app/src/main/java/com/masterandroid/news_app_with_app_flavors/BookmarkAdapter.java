package com.masterandroid.news_app_with_app_flavors;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> implements RecyclerInterfaceClick {

    private List<Bookmark> BookmarkList;
    private Activity activity;
    private RecyclerInterfaceClick interfaceClick;
    private RoomDB database;

    public BookmarkAdapter(List<Bookmark> BookmarkList, Activity activity, RecyclerInterfaceClick interfaceClick1) {
        this.BookmarkList = BookmarkList;
        this.activity = activity;
        this.interfaceClick=interfaceClick1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Bookmark data=BookmarkList.get(position);
        database= RoomDB.getInstance(activity);

        Picasso.get().load(data.getBookmark_image()).into(holder.bookmark_img);
        holder.bookmark_source.setText(data.getBookmark_source());
        holder.bookmark_title.setText(data.getBookmark_title());

    }

    @Override
    public int getItemCount() {
        return BookmarkList.size();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemLongClick(int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView bookmark_img;
        TextView bookmark_title;
        TextView bookmark_source;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookmark_img=itemView.findViewById(R.id.bookmarkImage);
            bookmark_title=itemView.findViewById(R.id.bookmarkTitle);
            bookmark_source=itemView.findViewById(R.id.bookmarkSource);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interfaceClick.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
