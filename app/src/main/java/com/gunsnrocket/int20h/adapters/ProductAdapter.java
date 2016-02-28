package com.gunsnrocket.int20h.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gunsnrocket.int20h.CurrentProductActivity;
import com.gunsnrocket.int20h.MainActivity;
import com.gunsnrocket.int20h.R;
import com.gunsnrocket.int20h.models.Product;

import java.util.List;

/**
 * Created by dnt on 2/28/16.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> list;

    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        Log.d("D", String.valueOf(list.get(position).getId()));
        holder.description.setText(list.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView name;
        TextView description;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.product_name);
            description = (TextView) itemView.findViewById(R.id.product_description);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Product product = list.get(getLayoutPosition());
            context.startActivity(new Intent(context, CurrentProductActivity.class)
                    .putExtra(MainActivity.PRODUCT_NAME, product.getName())
                    .putExtra(MainActivity.PRODUCT_DESCRIPTION, product.getDesc()));
        }
    }
}