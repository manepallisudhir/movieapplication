package com.example.manep.movielist;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    static ArrayList<URL> listImages;

    static ArrayList<String> title = new ArrayList<>();
    static ArrayList<String> rating = new ArrayList<>();
    static ArrayList<String> date = new ArrayList<>();
    static ArrayList<String> des = new ArrayList<>();
    static ArrayList<String> list = new ArrayList<>();

    static Context context;

    MovieAdapter movieAdapter;
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.menu, menu);
        return MainActivity.super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        setTitle("My Movies");

        final Button button = (Button) findViewById(R.id.Click_View);
        button.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.CUPCAKE)
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            public void onClick(View v) {
        setContentView(R.layout.activity_main);
                setTitle("Movies List");

        context = getApplicationContext();
        listImages = new ArrayList<>();
        movieAdapter = new MovieAdapter(MainActivity.this, listImages);
                DownloadMoviesData moviesData = new DownloadMoviesData();
                moviesData.execute();





        final GridView gridView = (GridView) findViewById(R.id.movie_Grid);
        gridView.setAdapter(movieAdapter);
               gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   //     title.get(i)=  jObject.getString;
                       Intent intent =new Intent(MainActivity.this,MovieDetails.class);
                       URL jsonObj = listImages.get(i);
                      // intent.putExtra("Title",.getJSONObject(i).getString("title");
                       intent.putExtra("Title",title.get(i));
                      intent.putExtra("Rate",rating.get(i));
                       intent.putExtra("Releasedate",date.get(i));
                       intent.putExtra("Des",des.get(i));
                       intent.putExtra("poster_path",list.get(i));
                    intent.putExtra("mTitle","sample"+ jsonObj.toString());
                       System.out.println(" jsonObj.toString()"+ jsonObj.toString());
                       intent.putExtra("title","sample"+i);

                       intent.putExtra("index","sample"+i);

                        startActivity(intent);

                    }
                });




    }



    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    class DownloadMoviesData extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            URL url = null;

            HttpURLConnection urlConnection = null;
            try {
                url = new URL("http://api.themoviedb.org/3/movie/popular?api_key= + API Key");

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader buffeRreader = new BufferedReader(
                        new InputStreamReader(in, "utf-8"), 8);
                StringBuilder stringbuilder = new StringBuilder();

                String line = null;
                while ((line = buffeRreader.readLine()) != null) {
                    stringbuilder.append(line + "\n");
                }

                in.close();
                String result = stringbuilder.toString();
                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String rate = null;
            String release = null;
            String thry = null;
            String movietitle = null;
            String image = null;
            JSONObject jObj = null;
            try {
                jObj = new JSONObject(s);
                JSONArray jArray = jObj.getJSONArray("results");
                for (int j = 0; j < jArray.length(); j++) {
                    JSONObject jObject = jArray.getJSONObject(j);
                    try {
                        URL url = new URL("http://image.tmdb.org/t/p/w185/" + jObject.getString("poster_path"));

                        listImages.add(url);
                       image = jObject.getString("poster_path");
                       list.add(image);
                        movietitle = jObject.getString("original_title");
                        title.add(movietitle);
                        rate = jObject.getString("vote_average");
                        rating.add(rate);
                        release = jObject.getString("release_date");
                        date.add(release);
                        thry =jObject.getString("overview");
                        des.add(thry);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    System.out.println(jObject.getString("original_title"));
                    System.out.println(jObject.getString("poster_path"));


                    movieAdapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

        });

    }

}

