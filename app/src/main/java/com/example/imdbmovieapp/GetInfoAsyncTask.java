package com.example.imdbmovieapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by abc on 2/26/2016.
 */
public class GetInfoAsyncTask extends AsyncTask<String, Void, ArrayList<Movie>> {

    SearchMovieActivity searchActivity;
    MovieDetailsActivity movieDetailsActivity;
    ArrayList<Movie> movieList;
    Movie currentmovie;

    ProgressDialog taskProgress;


    public GetInfoAsyncTask(SearchMovieActivity searchActivity) {
        this.searchActivity = searchActivity;
        taskProgress = new ProgressDialog(searchActivity);
    }
    public GetInfoAsyncTask(MovieDetailsActivity detailsActivity, Movie movie, ArrayList<Movie> movies) {
        Log.d("Demo","IN Details Task");
        this.movieDetailsActivity = detailsActivity;
        taskProgress = new ProgressDialog(detailsActivity);
        //currentmovie = new Movie();
        movieList = movies;
        currentmovie = movie;
    }
    @Override
    protected ArrayList<Movie> doInBackground(String... params) {
        Log.d("Calling AsynTAsk", "YES");
        URL url = null;
        try {
            url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK)
            {
                Log.d("Http Ok ", "YES");
                InputStream in = con.getInputStream();
                if(in!= null)
                    Log.d("Received InputStream", "Yes");

                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                String year = reader.readLine();
                while (line!=null){
                    Log.d("LINE has data:", "Yes");
                    Log.d("LINE :", line);
                    sb.append(line);
                    line = reader.readLine();

                }
                Log.d("Print String Builder:", ""+sb.toString());



                Log.d("Print","Search Context"+searchActivity + "Details Context" + movieDetailsActivity);
                if(searchActivity!=null)
                return MovieJSONUtil.movieParserJSON.parseWeatherJSON(sb.toString(),"searchActivity",movieList);
                else
                return MovieJSONUtil.movieParserJSON.parseWeatherJSON(sb.toString(),"movieDetailsActivity",movieList);
            }
            else
                Log.d("Http status code=",":"+statusCode);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        taskProgress.dismiss();

        if(searchActivity!=null)
            searchActivity.setUpData(movies);
        else
            movieDetailsActivity.setUpData(movies);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        taskProgress.setMessage("Searching Movie");
      //  taskProgress.incrementProgressBy(1);
        taskProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        taskProgress.setProgress(0);
        taskProgress.setMax(100);
        taskProgress.setCancelable(false);
        taskProgress.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        taskProgress.incrementProgressBy(1);
    }



}
