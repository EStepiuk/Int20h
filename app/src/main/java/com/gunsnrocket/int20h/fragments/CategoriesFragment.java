package com.gunsnrocket.int20h.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gunsnrocket.int20h.R;
import com.gunsnrocket.int20h.adapters.CategoryAdapter;
import com.gunsnrocket.int20h.dbhelpers.KazpromDBHelper;
import com.gunsnrocket.int20h.models.Category;

import java.util.ArrayList;

/**
 * Created by dnt on 2/27/16.
 */
public class CategoriesFragment extends Fragment {

    private ArrayList<Category> list;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private StaggeredGridLayoutManager layoutManager;
    private KazpromDBHelper kazpromDBHelper = KazpromDBHelper.getInstance();

    public CategoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        list = new ArrayList<Category>();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.list_categories);
        categoryAdapter = new CategoryAdapter(getContext(), list);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(categoryAdapter);

        (new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                kazpromDBHelper.connect();
                kazpromDBHelper.getCategoryList(list);

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                categoryAdapter.notifyDataSetChanged();
                Log.d("d", "privet");
            }
        }).execute();

        return rootView;
    }



}
