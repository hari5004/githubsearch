package com.dailyneeds.user.githubsearch;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by harikrishnan on 10/04/18.
 */

public class ApiCalls extends AsyncTask<String,Void,String> {
    public static final MediaType JSON = MediaType.parse("application/vnd.github.mercy-preview+json;");
    ProgressDialog pDialog;
    OkHttpClient client;
    String json2;
    private Context mcontext;
    private  ListAdapter mAdapter;
    public ApiCalls(Context context)
    {
        mcontext=context;
    }
    @Override
    protected void onPreExecute()

    {  // pDialog.show();
        pDialog=ProgressDialog.show(mcontext, "", "Please Wait", false);
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            client = new OkHttpClient();
            HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.github.com/search/repositories").newBuilder();
            urlBuilder.addQueryParameter("q", "topic:"+params[0]);
            //urlBuilder.addQueryParameter("user", "vogella");
            String url = urlBuilder.build().toString();
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Accept", "application/vnd.github.mercy-preview+json")
                    .build();
            Response response = client.newCall(request).execute();
            json2 = response.body().string();
        }
        catch(Exception e) {
            Toast.makeText(mcontext,"Failed",Toast.LENGTH_LONG).show();
        }
        return json2;
    }
    @Override
    protected void onPostExecute(String result) {
        pDialog.hide();
      try {
          List<Name> listdata = new ArrayList<>();
          listdata.clear();
          String name ="";
          String description= "";
          JSONObject jsonResponse=new JSONObject(result);
          JSONArray responseArray = jsonResponse.getJSONArray("items");
          for (int i=0;i < responseArray.length();i++) {
              JSONObject data = responseArray.getJSONObject(i);
               name += Integer.toString(i)+" "+data.getString("name") + " ";
              description += data.getString(("description")) + " ";
              Name bookie = new Name();
              bookie.name = "name:" + data.getString("name");
              listdata.add(bookie);

          }
          mAdapter = new ListAdapter(listdata);
          MainActivity.mRecyclerView.setAdapter(mAdapter);
          //Toast.makeText(mcontext,name+"  " + description,Toast.LENGTH_LONG).show();
      }
        catch (Exception e){
            Toast.makeText(mcontext,"We are currently unable to process the request",Toast.LENGTH_LONG).show();
        }
    }
}