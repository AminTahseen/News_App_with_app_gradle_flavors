package com.masterandroid.news_app_with_app_flavors;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Bookmarks_fragment extends Fragment implements RecyclerInterfaceClick {
    View parentHolder;
    List<Bookmark> datalist=new ArrayList<>();
    RoomDB db;
    BookmarkAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView bookmarkRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentHolder=inflater.inflate(R.layout.fragment_bookmarks_fragment, container, false);

        db=RoomDB.getInstance(getContext());
        datalist=db.mainDAO().getAllBookmarks();
        linearLayoutManager=new LinearLayoutManager(getContext());
        bookmarkRecycler=parentHolder.findViewById(R.id.bookmarkView);
        bookmarkRecycler.setLayoutManager(linearLayoutManager);
        adapter=new BookmarkAdapter(datalist, getActivity(),this);

        datalist.addAll(db.mainDAO().getAllBookmarks());
        adapter.notifyDataSetChanged();
        bookmarkRecycler.setAdapter(adapter);
        return parentHolder;
    }

    @Override
    public void onItemClick(int position) {
        News_Detail_Fragment fragment=new News_Detail_Fragment();
        Bundle args = new Bundle();
        args.putString("news_img", datalist.get(position).getBookmark_image());
        args.putString("news_title",datalist.get(position).getBookmark_title());
        args.putString("news_desc",datalist.get(position).getBookmark_description());
        args.putString("news_publish", datalist.get(position).getBookmark_publishedOn());
        args.putString("news_source", datalist.get(position).getBookmark_source());
        fragment.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment, getString(R.string.app_name)).addToBackStack(null).commit();
    }

    @Override
    public void onItemLongClick(int position) {

    }
}