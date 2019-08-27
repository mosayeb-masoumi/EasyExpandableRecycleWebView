package com.example.testexpandablerecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//https://medium.com/@nikola.jakshic/how-to-expand-collapse-items-in-recyclerview-49a648a403a6
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter adapter;
    List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView =findViewById(R.id.recyclerView);
        adapter=new Adapter(movieList,MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

        sendData();
    }

    private void sendData() {
        movieList.add(new Movie("title1","genre1",1));
        movieList.add(new Movie("title2","genre2",2));
        movieList.add(new Movie("title3","genre3",3));
    }
}
