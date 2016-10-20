package com.example.imdbmovieapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by abc on 2/26/2016.
 */
public class MovieJSONUtil {

    static public class movieParserJSON{

        static ArrayList<Movie> parseWeatherJSON(String in, String context,ArrayList<Movie> movies) throws JSONException {

            if (context.equals("searchActivity"))
            {
                ArrayList<Movie> movieList = new ArrayList<>();
                JSONObject root = new JSONObject(in);
                JSONArray movieJSONArray = root.getJSONArray("Search");
                JSONObject movieJSONObject = new JSONObject();
                for(int i=0; i<movieJSONArray.length();i++)
                {
                    movieJSONObject = movieJSONArray.getJSONObject(i);
                    Movie currentMovie = new Movie();

                    Log.d("PRINT CURRENT MOVIE:", movieJSONObject.getString("Title") + "/" + movieJSONObject.getString("imdbID"));

                    currentMovie.setTitle(movieJSONObject.getString("Title"));
                    currentMovie.setYear(movieJSONObject.getString("Year"));
                    currentMovie.setImdbID(movieJSONObject.getString("imdbID"));
                    currentMovie.setPoster("Poster");

                    Log.d("PRINT CURRENT MOVIE:", currentMovie.toString());

                    movieList.add(currentMovie);
                }
                Collections.sort(movieList);
                Log.d("PRINT MOVIE LIST:", movieList.toString());
                return movieList;
            }
            else if (context.equals("movieDetailsActivity"))
            {

                JSONObject movieJSONObject = new JSONObject(in);
                for(int i =0; i<movies.size();i++)
                    Log.d("Movie for update", movies.get(i).toString());

               for(int i =0; i<movies.size();i++)
               {
                if(movies.get(i).getImdbID().equalsIgnoreCase(movieJSONObject.getString("imdbID")))
                {
                            movies.get(i).setReleased(movieJSONObject.getString("Released"));
                            movies.get(i).setGenre(movieJSONObject.getString("Genre"));
                            movies.get(i).setDirector(movieJSONObject.getString("Director"));
                            movies.get(i).setActors(movieJSONObject.getString("Actors"));
                            movies.get(i).setPlot(movieJSONObject.getString("Plot"));
                            movies.get(i).setImdbRating(movieJSONObject.getString("imdbRating"));
                            movies.get(i).setPoster(movieJSONObject.getString("Poster"));
                            movies.get(i).setWriter(movieJSONObject.getString("Writer"));
                            movies.get(i).setRuntime(movieJSONObject.getString("Runtime"));
                            movies.get(i).setAwards(movieJSONObject.getString("Awards"));
                            movies.get(i).setCountry(movieJSONObject.getString("Country"));

                }

               }
                for(int i =0; i<movies.size();i++)
                    Log.d("Return Movie", movies.get(i).toString());
                return movies;
            }

            return null;

    }

}

        // logic to sort the elements

}
