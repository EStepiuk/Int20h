package com.gunsnrocket.int20h.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gunsnrocket.int20h.R;

import java.util.zip.Inflater;

/**
 * Created by dnt on 2/27/16.
 */
public class CategoriesFragment extends Fragment {

    public CategoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_categories, container, false);
            return rootView;
    }

}
