package com.masterandroid.news_app_with_app_flavors;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.BuildConfig;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class News_Detail_Fragment extends Fragment {
    View parentHolder;
    ImageView news_img;
    TextView news_source;
    TextView news_title;
    TextView news_desc;
    TextView news_published;
    ImageButton btnShare;
    ImageButton btnBookMark;
    RoomDB Database;
    List<Bookmark> datalist=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Bundle bundle = this.getArguments();
        final String img= bundle.getString("news_img", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcREbveSBaD5Xx-HWt4mMiLe9K-jl02E0dIHQg&usqp=CAU");
        final String title= bundle.getString("news_title", "title");
        final String desc= bundle.getString("news_desc", "description");
        final String publish= bundle.getString("news_publish", "published");
        final String source= bundle.getString("news_source", "source");



        parentHolder=inflater.inflate(R.layout.fragment_news__detail_, container, false);

        news_source=parentHolder.findViewById(R.id.news_source);
        news_title=parentHolder.findViewById(R.id.news_title);
        news_desc=parentHolder.findViewById(R.id.news_desc);
        news_published=parentHolder.findViewById(R.id.news_published);
        news_img=parentHolder.findViewById(R.id.news_img);
        btnShare=parentHolder.findViewById(R.id.btnShare);
        btnBookMark=parentHolder.findViewById(R.id.btnBookmark);

        Database=RoomDB.getInstance(getContext());
        datalist.addAll(Database.mainDAO().getAllBookmarks());
        try {
            news_title.setText(title);
            news_desc.setText(desc);
            news_published.setText("Published On " + publish);
            news_source.setText(source);
            Picasso.get().load(img).into(news_img);
        }catch (Exception er)
        {

        }
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationInfo api=getActivity().getApplicationContext().getApplicationInfo();
                String apkPath=api.sourceDir;
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("application/vnd.android.package-archive");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkPath)));
                startActivity(Intent.createChooser(intent,"Share To"));
            }
        });


        btnBookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(com.masterandroid.news_app_with_app_flavors.BuildConfig.ALLOW_ACCESS.equals("no"))
                    {
                        Snackbar snackbar = Snackbar
                                .make(parentHolder, "You must upgrade to paid version to use this feature", Snackbar.LENGTH_LONG)
                                .setAction("Upgrade", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view)
                                    {
                                        Toast.makeText(getActivity(), "Clicked Upgrade", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        snackbar.show();
                    }
                    else if(com.masterandroid.news_app_with_app_flavors.BuildConfig.ALLOW_ACCESS.equals("yes"))
                    {
                        Bookmark obj=new Bookmark();
                        obj.setBookmark_title(title);
                        obj.setBookmark_description(desc);
                        obj.setBookmark_publishedOn(publish);
                        obj.setBookmark_isBookmarked(true);
                        obj.setBookmark_image(img);
                        obj.setBookmark_source(source);
                        Toast.makeText(getActivity(), "Successfully Bookmarked !", Toast.LENGTH_SHORT).show();
                        Database.mainDAO().Insert(obj);
                    }
                }catch (Exception er){
                    Log.d("News Detail : ",er.getMessage());
                }


            }
        });

        return parentHolder;
    }
}