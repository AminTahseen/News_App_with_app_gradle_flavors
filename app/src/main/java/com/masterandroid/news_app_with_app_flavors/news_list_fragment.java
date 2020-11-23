package com.masterandroid.news_app_with_app_flavors;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class news_list_fragment extends Fragment implements RecyclerInterfaceClick{

    View parentHolder;
    NestedScrollView NSV;
    RecyclerView RecyclerList;
    ProgressBar progressBar;
    ArrayList<NewsData> newsList =new ArrayList<>();
    MainAdapter Adapter;
    ImageButton searchbtn;
    EditText searchTag;
    LinearLayout emptyLayout;
    int page=1,limit=10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentHolder= inflater.inflate(R.layout.fragment_news_list_fragment, container, false);

        NSV=parentHolder.findViewById(R.id.NestedScrollview);
        RecyclerList=parentHolder.findViewById(R.id.recyclerView);
        progressBar=parentHolder.findViewById(R.id.progress);
        searchTag=parentHolder.findViewById(R.id.searchTag);
        Adapter=new MainAdapter(newsList,getActivity(),this);
        emptyLayout=parentHolder.findViewById(R.id.emptyLayout);
        RecyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerList.setAdapter(Adapter);
        searchbtn=parentHolder.findViewById(R.id.searchbtn);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search_tag=searchTag.getText().toString();
                if(search_tag.isEmpty()){
                    searchTag.setError("Enter a keyword for news !");
                    searchTag.requestFocus();
                    return;
                }else
                    {
                        progressBar.setVisibility(View.VISIBLE);
                        getData(search_tag,page,limit);
                        RecyclerList.setVisibility(View.VISIBLE);
                        emptyLayout.setVisibility(View.GONE);
                    }

            }
        });

        progressBar.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.VISIBLE);
        RecyclerList.setVisibility(View.GONE);
        NSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY==v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){
                    page++;
                    progressBar.setVisibility(View.VISIBLE);
                    getData("bitcoin",page,limit);
                }
            }
        });
        return parentHolder;
    }

    private void getData(String query,int page, int limit) {


            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("\n" +
                            "http://newsapi.org/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

            MainInterface mainInterface=retrofit.create(MainInterface.class);
            Call<String> call=mainInterface.STRING_CALL(query,page,limit,"YOUR NEWS API KEY");
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    if(response.isSuccessful() && response.body()!=null)
                    {
                        Log.d("MAIN :",response.body());
                        progressBar.setVisibility(View.GONE);
                        try {
                            // JSONArray array=new JSONArray(response.body());
                            JSONObject obj=new JSONObject(response.body());
                            parseResult(obj);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }

    private void parseResult(JSONObject jsonObject) {
        try{
            JSONArray array=jsonObject.getJSONArray("articles");
            for(int i=0;i<array.length();i++)
            {


                JSONObject obj=array.getJSONObject(i);
                NewsData news=new NewsData();
                news.setNews_title(obj.getString("title"));
                news.setNews_description(obj.getString("description"));
                news.setNews_image(obj.getString("urlToImage"));
                JSONObject source=obj.getJSONObject("source");
                news.setNews_source(source.getString("name"));

                news.setPublishedOn(obj.getString("publishedAt"));
                newsList.add(news);


            }
        }catch (Exception er)
        {


        }
        Adapter=new MainAdapter(newsList,getActivity(),this);
        RecyclerList.setAdapter(Adapter);
    }

    @Override
    public void onItemClick(int position) {
        News_Detail_Fragment fragment=new News_Detail_Fragment();
        Bundle args = new Bundle();
        args.putString("news_img", newsList.get(position).getNews_image());
        args.putString("news_title", newsList.get(position).getNews_title());
        args.putString("news_desc", newsList.get(position).getNews_description());
        args.putString("news_publish", newsList.get(position).getPublishedOn());
        args.putString("news_source", newsList.get(position).getNews_source());
        fragment.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment, getString(R.string.app_name)).addToBackStack(null).commit();
    }

    @Override
    public void onItemLongClick(int position) {

    }
}
