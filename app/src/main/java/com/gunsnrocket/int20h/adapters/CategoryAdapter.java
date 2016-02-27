package com.gunsnrocket.int20h.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gunsnrocket.int20h.R;
import com.gunsnrocket.int20h.models.Category;

import java.util.List;

/**
 * Created by dnt on 2/27/16.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    private Context context;

    private List<Category> list;

    public CategoryAdapter(Context context, List<Category> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
