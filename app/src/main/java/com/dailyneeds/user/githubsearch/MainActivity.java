package com.dailyneeds.user.githubsearch;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Search(View view) {
        final EditText searchQuery = findViewById(R.id.searchQuery);
        String searchString = searchQuery.getText().toString();
        ApiCalls postOperations = new ApiCalls(context);
        postOperations.execute(searchString);
    }
}
