package com.example.manep.movielist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import static com.example.manep.movielist.MainActivity.context;

/**
 * Created by manep on 7/28/2017.
 */

public class MovieDetails extends AppCompatActivity {
  TextView txt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Movie");
        setContentView(R.layout.details_layout);
        Bundle bundle =getIntent().getExtras();
        System.out.println("getIntent()"+getIntent());
        String title = getIntent().getStringExtra("mTitle");
        String  movietitle = getIntent().getStringExtra("Title");
        String movierate = getIntent().getStringExtra("Rate");
        String RD = getIntent().getStringExtra("Releasedate");
        String description =getIntent().getStringExtra("Des");
        String imag = getIntent().getStringExtra("poster_path");
        String url = "http://image.tmdb.org/t/p/w185/"+ imag;

        String index= getIntent().getStringExtra("index");
        System.out.println("MoviesDetails.index:"+index);
        System.out.println("MoviesDetails.title:"+title);

        ImageView mimage = (ImageView)findViewById(R.id.Movie_Image);
        TextView mtitle1 =(TextView)findViewById(R.id.Movie_title1);
        TextView mtitle2 =(TextView)findViewById(R.id.Movie_title3);
        TextView mtitle3 =(TextView)findViewById(R.id.Movie_title2);
        TextView mtitle4 =(TextView)findViewById(R.id.Movie_title4);
        Picasso.with(MovieDetails.this).load(url).into(mimage);

        mtitle1.setText(movietitle);
        mtitle2.setText(movierate);
        mtitle3.setText(RD);
       mtitle4.setText(description);









    }
}
