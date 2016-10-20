package com.example.imdbmovieapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MovieDetailsActivity extends AppCompatActivity {

    ArrayList<Movie> movieArrayList;
    Movie currentMovie;
    ProgressDialog progressBar;
    ImageView movieImage;
    String searchTerm;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        setupActionBar();

        movieImage = ((ImageView)findViewById(R.id.iv_MovieImage));
        movieArrayList = new ArrayList<>();

        if (getIntent() != null) {
            movieArrayList = getIntent().getParcelableArrayListExtra("MOVIE_LIST");
            currentMovie = (Movie)getIntent().getSerializableExtra("CURRENT_MOVIE");
            searchTerm = getIntent().getStringExtra("SEARCH_TERM");
            Log.d("DEMO", "CURRENT_MOVIE DETAIL: "+currentMovie.toString());
            for(int i=0;i<movieArrayList.size();i++)
                Log.d("DEMO", "CURRENT_MOVIES: "+movieArrayList.get(i).getTitle());
        }

        new GetInfoAsyncTask(MovieDetailsActivity.this,currentMovie,movieArrayList).execute("http://www.omdbapi.com/?i=" + currentMovie.getImdbID());


        findViewById(R.id.bnt_Finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchMovieActivity.class);
                intent.putExtra("SEARCH_TERM", searchTerm);
                startActivity(intent);
                finish();
            }
        });


        //next button
        findViewById(R.id.btn_Next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("imdbID","="+ movieArrayList.get(0).getImdbID());
//                Log.d("imdbID","="+ movieArrayList.get(index + 1).getImdbID());

                Log.d("next index=",":"+index);
                Log.d("next size()=",":"+movieArrayList.size());
                if(index >= movieArrayList.size()-1) {
                    currentMovie = movieArrayList.get(0);
                    new GetInfoAsyncTask(MovieDetailsActivity.this, currentMovie, movieArrayList).execute("http://www.omdbapi.com/?i=" + movieArrayList.get(0).getImdbID());
                }
                else {
                    currentMovie = movieArrayList.get(index + 1);
                    new GetInfoAsyncTask(MovieDetailsActivity.this, currentMovie, movieArrayList).execute("http://www.omdbapi.com/?i=" + movieArrayList.get(index + 1).getImdbID());
                }
            }
        });

        //previous button
        findViewById(R.id.btn_Previous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("imdbID", "=" + movieArrayList.get(movieArrayList.size() - 1).getImdbID());
//                Log.d("imdbID", "=" + movieArrayList.get(index - 1).getImdbID());
                Log.d("prev index=",":"+index);
                if(index <= 0) {
                    currentMovie = movieArrayList.get(movieArrayList.size()-1);
                    new GetInfoAsyncTask(MovieDetailsActivity.this, currentMovie, movieArrayList).execute("http://www.omdbapi.com/?i=" + movieArrayList.get(movieArrayList.size()-1).getImdbID());
                }
                else {
                    currentMovie = movieArrayList.get(index-1);
                    new GetInfoAsyncTask(MovieDetailsActivity.this, currentMovie, movieArrayList).execute("http://www.omdbapi.com/?i=" + movieArrayList.get(index - 1).getImdbID());
                }
            }
        });

    }//end of onCreate


    private void setupActionBar(){

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setTitle("Movie Details");

    }//End setupActionBar



    public void setUpData(final ArrayList<Movie> movieList) {

        movieArrayList = movieList;

        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getImdbID().equalsIgnoreCase(currentMovie.getImdbID())) {
                 ((TextView) findViewById(R.id.tv_MovieTitle)).setText(movieList.get(i).getTitle());

                if(!movieList.get(i).getReleased().equalsIgnoreCase("N/A"))
                ((TextView) findViewById(R.id.tv_releaseDate)).setText(movieList.get(i).getReleased().substring(3, 6)+" "+movieList.get(i).getReleased().substring(0, 2)+" "+movieList.get(i).getReleased().substring(7,11));
                else
                    ((TextView) findViewById(R.id.tv_releaseDate)).setText(movieList.get(i).getReleased());
                ((TextView) findViewById(R.id.tv_runtime)).setText(movieList.get(i).getRuntime());
                ((TextView) findViewById(R.id.tv_Country)).setText(movieList.get(i).getCountry());
                ((TextView) findViewById(R.id.tv_awards)).setText(movieList.get(i).getAwards());
                ((TextView) findViewById(R.id.tv_Genre)).setText(movieList.get(i).getGenre());
                ((TextView) findViewById(R.id.tv_Director)).setText(movieList.get(i).getDirector());
                ((TextView) findViewById(R.id.tv_Writer)).setText(movieList.get(i).getWriter());
                ((TextView) findViewById(R.id.tv_Actor)).setText(movieList.get(i).getActors());
                ((TextView) findViewById(R.id.tv_MoviePlot)).setText(movieList.get(i).getPlot());
                if (!movieList.get(i).getImdbRating().equalsIgnoreCase("N/A")) {
                    float rating = Float.parseFloat(movieList.get(i).getImdbRating()) / 2;
                    Log.d("Rating", ": " + rating);
                    ((RatingBar) findViewById(R.id.rbar_rating)).setRating(rating);
                } else
                    ((RatingBar) findViewById(R.id.rbar_rating)).setRating(0);
                    index = i;
                if(!movieList.get(i).getPoster().equalsIgnoreCase("Poster")) {
                   movieImage.setClickable(false);
//                    movieImage.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(getApplicationContext(),MovieWebView.class);
//                            intent.putExtra("imdbID", movieList.get(index).getImdbID());
//                            startActivity(intent);
//                            finish();
//                        }
//                    });
                    Log.d("Poster URL", movieList.get(i).getPoster());
                    progressBar = new ProgressDialog(MovieDetailsActivity.this);
                    new GetImage().execute(movieList.get(i).getPoster());
               }
            }
        }
    }//end of setupdata


    private class GetImage extends AsyncTask<String,Integer,Bitmap> {

        private int progressIncr=1;

        @Override
        protected void onPreExecute() {
            Log.d("Poster URL","In Preexecute");
            CharSequence message = "Loading Image";
            progressBar.setCancelable(false);
            progressBar.setMessage(message);
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            progressBar.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Log.d("Poster URL","In DOINBACKGROUND");
            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                Bitmap image = BitmapFactory.decodeStream(con.getInputStream());

                return image;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.incrementProgressBy(progressIncr);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            progressBar.dismiss();
            movieImage.setImageBitmap(result);
        }
    }//


}//end
