package com.gunsnrocket.int20h.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.gunsnrocket.int20h.R;
import com.gunsnrocket.int20h.adapters.ProductAdapter;
import com.gunsnrocket.int20h.dbhelpers.KazpromDBHelper;
import com.gunsnrocket.int20h.dbhelpers.LocalDbHelper;
import com.gunsnrocket.int20h.models.Product;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.adapters.SlideInBottomAnimationAdapter;

/**
 * Created by dnt on 2/28/16.
 */
public class HistoryFragment extends Fragment {

    private ArrayList<Product> list;
    private RecyclerView recyclerView;
    private SlideInBottomAnimationAdapter adapter;
    private StaggeredGridLayoutManager layoutManager;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        list = (new LocalDbHelper(getContext())).getListProduct();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.list_history);
        adapter = new SlideInBottomAnimationAdapter(new ProductAdapter(getContext(), list));
        adapter.setDuration(500);
        adapter.setInterpolator(new OvershootInterpolator(0.7f));
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        list = (new LocalDbHelper(getContext())).getListProduct();
        adapter.notifyDataSetChanged();
    }
}
