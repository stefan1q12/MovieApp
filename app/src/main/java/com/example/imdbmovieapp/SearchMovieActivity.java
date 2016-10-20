package com.example.imdbmovieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchMovieActivity extends AppCompatActivity {
   int year;
   ArrayList<Movie> movieList;
   ScrollView listMovies;
    String searchTerm;
    String searchYear;
    String genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        setupActionBar();

        listMovies = (ScrollView)findViewById(R.id.sv_MovieList);

     //   getIntenInfo();
        searchTerm = getIntent().getStringExtra("SEARCH_TERM");
        String newSearchTerm = searchTerm.replaceAll(" ", "%20");
        Log.d("newSeartchTerm", newSearchTerm);

        searchYear = getIntent().getStringExtra("Search_Year");
        String newSearchYear = searchYear;
//        Log.d("newSearchYear", newSearchYear);



        String queryURL = "http://www.omdbapi.com/?type=movie&s=" + newSearchTerm +"&y="+newSearchYear;
        Log.d("omdb query URL=",":"+queryURL);
        new GetInfoAsyncTask(SearchMovieActivity.this).execute(queryURL);
        //http://www.omdbapi.com/?type=movie&s=Batman
        //http://www.omdbapi.com/?t=batman&y=2000&plot=short&r=json


    }//end OnCreate


    public void setUpData(final ArrayList<Movie> movieArrayList){


        for(int i = 0 ; i<movieArrayList.size(); i++)
        Log.d("MovieList from Async","Movie:"+i+"- "+movieArrayList.get(i).toString());
        LinearLayout myLayout = new LinearLayout(this);
        myLayout.setOrientation(LinearLayout.VERTICAL);



        listMovies.addView(myLayout);
        for(int i = 0 ; i<movieArrayList.size(); i++) {
            // TextView bar = new TextView(this);

            searchYear = movieArrayList.get(i).getYear();

                if (searchYear.equals(movieArrayList.get(i).getYear())){
                    Log.d("Search Year", "=" + searchYear);

                }

            EditText bar = new EditText(this);
            bar.setHeight(1);
            bar.setClickable(false);
            bar.setEnabled(false);
            bar.setPadding(0, 0, 0, 0);
            //  bar.setBackgroundColor(R.color.colorRed);
            bar.setClickable(false);
            // bar.setBackgroundColor(R.color.colorPrimaryDark);
            TextView t = new TextView(this);
            // t.setGravity(an);
            t.setAllCaps(false);
            t.setPadding(5, 10, 0, 2);
            t.setText(movieArrayList.get(i).getTitle() + "( " + movieArrayList.get(i).getYear() + " )");
            t.setClickable(true);
            final int index = i;
            t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //  v.setBackgroundColor(R.color.colorAccent);
                        Intent intent = new Intent(SearchMovieActivity.this, MovieDetailsActivity.class);
                        intent.putExtra("SEARCH_TERM", searchTerm);
                        intent.putExtra("CURRENT_MOVIE", (Serializable) movieArrayList.get(index));
                        intent.putParcelableArrayListExtra("MOVIE_LIST", movieArrayList);
                        startActivity(intent);
                        finish();
                    }
                });
                myLayout.addView(t);
                myLayout.addView(bar);

                //

            }
        //listMovies.addView();

    }


//=============================================
//    private void getIntenInfo(){
//
//        movieName = getIntent().getStringExtra("SEARCH TERM");
//        Log.d("SEARCH TERM: ", movieName);
//    }

    private void setupActionBar(){

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setTitle("Search Movies");
        //actionBar.setTitle();

    }//End setupActionBar


}
