/*
 * Created by Force Porquillo on 7/4/20 9:17 AM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/4/20 9:17 AM
 */

package com.force.codes.project.app.presentation_layer.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.app.R;
import com.force.codes.project.app.presentation_layer.controller.custom.model.Group;
import com.force.codes.project.app.presentation_layer.controller.custom.utils.ItemDecoration;
import com.force.codes.project.app.presentation_layer.views.viewholders.NewsGroupViewHolder;

import java.util.ArrayList;
import java.util.Arrays;

import kotlin.jvm.JvmStatic;

public class NewsGroupAdapter extends RecyclerView.Adapter<NewsGroupViewHolder>{
    private Context context;
    private HotNewsAdapter hotNewsAdapter;
    private HeaderNewsAdapter headerNewsAdapter;

    @JvmStatic
    private static ArrayList<Group> groups(){
        return new ArrayList<>(Arrays.asList(
                new Group("Recent Tweets", false),
                new Group("Hot Topics", false))
        );
    }

    private static ItemDecoration decoration(Context context){
        return new ItemDecoration(context, ItemDecoration.VERTICAL_LIST, 0);
    }

    public NewsGroupAdapter(HeaderNewsAdapter headerNewsAdapter, HotNewsAdapter hotNewsAdapter){
        this.headerNewsAdapter = headerNewsAdapter;
        this.hotNewsAdapter = hotNewsAdapter;
    }

    @NonNull
    @Override
    public NewsGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        final View view = LayoutInflater.from(this.context = parent.getContext())
                .inflate(R.layout.news_group_layout, parent, false);
        return new NewsGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsGroupViewHolder holder, int position){
        Group group = groups().get(position);
        holder.setGroupTitle(group);
        if(position == 1) holder.setDecorVisibility(true);
        setListOrder(holder.recyclerView, position);
    }

    private void setListOrder(RecyclerView recyclerView, int position){
        if(position == 0){
            setLatestNewsList(recyclerView);
            return;
        }
        setHotNewsList(recyclerView);
    }

    @Override
    public int getItemCount(){
        return !groups().isEmpty() ? groups().size() : 0;
    }

    final void setLatestNewsList(RecyclerView recyclerView){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(headerNewsAdapter);
        recyclerView.setNestedScrollingEnabled(true);
    }


    final void setHotNewsList(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(hotNewsAdapter);
        recyclerView.addItemDecoration(decoration(recyclerView.getContext()));
        recyclerView.setNestedScrollingEnabled(true);
    }
}
