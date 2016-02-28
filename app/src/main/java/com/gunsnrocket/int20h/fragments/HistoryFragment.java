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

    private ArrayList<Product> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private StaggeredGridLayoutManager layoutManager;
    private LocalDbHelper localDbHelper;
    public HistoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        localDbHelper = new LocalDbHelper(getContext());
        localDbHelper.getListProduct(list);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.list_history);
        adapter = new ProductAdapter(getContext(), list);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        localDbHelper.getListProduct(list);
        adapter.notifyDataSetChanged();
    }
}
