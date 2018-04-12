package com.dailyneeds.user.githubsearch;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Context context=this;
    static RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Search(View view) {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_vazhipadu);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(null);
        final EditText searchQuery = findViewById(R.id.searchQuery);
        String searchString = searchQuery.getText().toString();
        Boolean status=isNetworkAvailable();
        if(status) {
            ApiCalls postOperations = new ApiCalls(context);
            postOperations.execute(searchString);
        }
        else
        {
            Toast.makeText(context, "Network Connection not available", Toast.LENGTH_LONG).show();
        }

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}

