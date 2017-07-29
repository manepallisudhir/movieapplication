package com.example.manep.movielist;



import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

import static com.example.manep.movielist.R.id.parent;


/**
 * Created by manep on 7/28/2017.
 */

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<URL> url;

    public MovieAdapter(Context context, ArrayList<URL> url) {
        this.context = context;
        this.url = url;
    }


    @Override
    public int getCount() {
        int size=0;
        size=url.size();
        return size;
    }

    @Override
    public Object getItem(int i) {
        return url.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
       if (view == null) {
           view = LayoutInflater.from(context).inflate(R.layout.grid_layout, parent, false);
       }
          ImageView movieImage = (ImageView) view.findViewById(R.id.movie_Poster);
        DisplayMetrics metrics = new DisplayMetrics();

           WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
           wm.getDefaultDisplay().getMetrics(metrics);

           Picasso.with(context).
                   load(MainActivity.listImages.get(i).toString())
                   .resize(metrics.widthPixels/2, 800)

                   .into(movieImage);
           return view;
       }
    }

