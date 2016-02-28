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

import com.gunsnrocket.int20h.R;
import com.gunsnrocket.int20h.adapters.ProductAdapter;
import com.gunsnrocket.int20h.dbhelpers.KazpromDBHelper;
import com.gunsnrocket.int20h.dbhelpers.LocalDbHelper;
import com.gunsnrocket.int20h.models.Product;

import java.util.ArrayList;

/**
 * Created by dnt on 2/28/16.
 */
public class HistoryFragment extends Fragment {

    private ArrayList<Product> list;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private StaggeredGridLayoutManager layoutManager;

    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        list = (new LocalDbHelper(getContext())).getListProduct();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.list_history);
        adapter = new ProductAdapter(getContext(), list);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        return rootView;
    }
    
}
