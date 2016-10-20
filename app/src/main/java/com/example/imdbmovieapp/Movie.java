package com.example.imdbmovieapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by abc on 2/26/2016.
 */
public class Movie implements Comparable, Parcelable, Serializable {

    String title, year, imdbID, poster, released, genre, director, actors, plot,imdbRating, writer, runtime, awards, country;


    public Movie() {
        this.actors = null;
        this.director = null;
        this.genre = null;
        this.imdbID = null;
        this.imdbRating = null;
        this.plot = null;
        this.poster = null;
        this.released = null;
        this.title = null;
        this.year = null;
        this.writer = null;
        this.runtime = null;
        this.awards = null;
        this.country = null;
    }



    public Movie(String actors, String director, String genre, String imdbID, String imdbRating, String plot, String poster, String released, String title, String year, String writer, String runtime, String awards, String country) {
        this.actors = actors;
        this.director = director;
        this.genre = genre;
        this.imdbID = imdbID;
        this.imdbRating = imdbRating;
        this.plot = plot;
        this.poster = poster;
        this.released = released;
        this.title = title;
        this.year = year;
        this.writer = writer;
        this.runtime = runtime;
        this.awards = awards;
        this.country = country;

    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "\nMovie{" +
                "title='" + title + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", year='" + year + '\'' +
                ", director='" + director + '\'' +
                ", poster='" + poster + '\'' +
                ", released='" + released + '\'' +
                ", genre='" + genre + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", writer='" + writer + '\'' +
                ", runtime='" + runtime+ '\'' +
                ", awards='" + awards+ '\'' +
                ", country='" + country + '\'' +
                '}';
   }

//    @Override
//    public boolean equals(Object o) {
//        if(!(o instanceof Movie))
//            return false;
//        if (o == this)
//            return true;
//        return this.imdbID==((Movie)o).imdbID;
//    }
//
//    @Override
//    public int hashCode() {
//        new HashC
//        return super.hashCode();
//    }

    @Override
    public int compareTo(Object another) {

      //  String anotheryear = ((Movie)another).getYear()

        if(Integer.parseInt(this.getYear())< Integer.parseInt(((Movie)another).getYear()))
            return 1 ;
        else if (Integer.parseInt(this.getYear()) > Integer.parseInt(((Movie)another).getYear()))
            return -1;

        return 0;
    }

    //Parcelable implementation

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


    protected Movie(Parcel in) {

        title = in.readString();
        year = in.readString();
        imdbID = in.readString();
        poster = in.readString();
        released = in.readString();
        genre = in.readString();
        director = in.readString();
        actors = in.readString();
        plot = in.readString();
        imdbRating = in.readString();
        writer = in.readString();
        runtime = in.readString();
        awards = in.readString();
        country = in.readString();
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(imdbID);
        dest.writeString(poster);
        dest.writeString(released);
        dest.writeString(genre);
        dest.writeString(director);
        dest.writeString(actors);
        dest.writeString(plot);
        dest.writeString(imdbRating);
        dest.writeString(writer);
        dest.writeString(runtime);
        dest.writeString(awards);
        dest.writeString(country);

    //title, year, imdbID, poster, released, genre, director, actors, plot,imdbRating;

    }
}
