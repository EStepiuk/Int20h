package com.gunsnrocket.int20h.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gunsnrocket.int20h.GroupsActivity;
import com.gunsnrocket.int20h.MainActivity;
import com.gunsnrocket.int20h.R;
import com.gunsnrocket.int20h.models.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dnt on 2/27/16.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

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
        holder.name.setText(list.get(position).getName());
        Picasso.with(context)
                .load(R.drawable.no_image)
                .resizeDimen(R.dimen.image_target_size, R.dimen.image_target_size)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.category_name);
            imageView = (ImageView) itemView.findViewById(R.id.category_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Category category = list.get(getLayoutPosition());
            context.startActivity(new Intent(context, GroupsActivity.class)
                    .putExtra(MainActivity.CATEGORY_ID, category.getId())
                    .putExtra(MainActivity.CATEGORY_NAME, category.getName()));
        }
    }

}
