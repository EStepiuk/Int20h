package com.gunsnrocket.int20h.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gunsnrocket.int20h.R;
import com.gunsnrocket.int20h.adapters.CategoryAdapter;
import com.gunsnrocket.int20h.models.Category;

import java.util.List;

/**
 * Created by dnt on 2/27/16.
 */
public class CategoriesFragment extends Fragment {

    private List<Category> list;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private StaggeredGridLayoutManager layoutManager;

    public CategoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_categories, container, false);

            recyclerView = (RecyclerView) rootView.findViewById(R.id.list_categories);
            categoryAdapter = new CategoryAdapter(getContext(), list);
            layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(categoryAdapter);
            return rootView;
    }



}
