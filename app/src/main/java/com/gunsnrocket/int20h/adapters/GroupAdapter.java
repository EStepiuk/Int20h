package com.gunsnrocket.int20h.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gunsnrocket.int20h.MainActivity;
import com.gunsnrocket.int20h.ProductsActivity;
import com.gunsnrocket.int20h.R;
import com.gunsnrocket.int20h.dbhelpers.LocalDbHelper;
import com.gunsnrocket.int20h.models.Group;

import java.util.List;

/**
 * Created by dnt on 2/27/16.
 */
public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private Context context;
    private List<Group> list;
    private LocalDbHelper localDbHelper;

    public GroupAdapter(Context context, List<Group> list) {
        this.context = context;
        this.list = list;
        localDbHelper = new LocalDbHelper(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_group, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.group_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Group group = list.get(getLayoutPosition());
            if(!localDbHelper.isGroupExist(group.getId()))
                localDbHelper.addGroup(group);
            context.startActivity(new Intent(context, ProductsActivity.class)
                    .putExtra(MainActivity.GROUP_NAME, group.getName())
                    .putExtra(MainActivity.GROUP_ID, group.getId()));
        }
    }
}
