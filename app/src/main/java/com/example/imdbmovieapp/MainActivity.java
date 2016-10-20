package com.example.imdbmovieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView movieName;
    Button searchIMDb;
    String spinnerYear = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieName = (TextView) findViewById(R.id.tv_FindMovies);
        searchIMDb = (Button) findViewById(R.id.btn_SearchIMDb);

        setupActionBar();
        searchIMDb.setOnClickListener(sendIntent);

        final ArrayList<String> years = new ArrayList<String>();
        final int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1980; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);

        final Spinner spinYear = (Spinner)findViewById(R.id.spinner);
        spinYear.setAdapter(adapter);
        spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                String spinnerYear = ((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString();
                Log.d("Search Year", "=" + spinnerYear);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

    }//end On Create

//==================================
private void setupActionBar(){

    android.support.v7.app.ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayShowHomeEnabled(true);
    actionBar.setIcon(R.mipmap.ic_launcher);
    actionBar.setTitle("Movies");
    //actionBar.setTitle();

}//End setupActionBar

//==================================


View.OnClickListener sendIntent = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(!movieName.getText().toString().isEmpty())
        {
            Log.d("Search Trem", "=" + movieName.getText().toString());
            Log.d("Search Year", "=" + ((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString());
            Intent intent = new Intent(MainActivity.this,SearchMovieActivity.class);
            intent.putExtra("SEARCH_TERM", movieName.getText().toString());
            intent.putExtra("Search_Year", ((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString());
            startActivity(intent);
            finish();
        }


        else
            Toast.makeText(getApplicationContext(),"Enter a movie name",Toast.LENGTH_LONG).show();
    }
};//end SendIntent

}//end