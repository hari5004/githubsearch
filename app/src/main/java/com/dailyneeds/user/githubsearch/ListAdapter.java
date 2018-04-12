package com.dailyneeds.user.githubsearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dailyneeds.user.githubsearch.Name;
import com.dailyneeds.user.githubsearch.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    List<Name> list_data= Collections.emptyList();
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_list;

        public MyViewHolder(View view) {
            super(view);
            name_list = (TextView) view.findViewById(R.id.name_list);
        }
    }


    public ListAdapter(  List<Name> vazhipadulist) {

        this.list_data=vazhipadulist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.responselist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Name current=list_data.get(position);
        holder.name_list.setText(current.name);
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }
}
