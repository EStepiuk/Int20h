package com.gunsnrocket.int20h;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.gunsnrocket.int20h.adapters.GroupAdapter;
import com.gunsnrocket.int20h.dbhelpers.KazpromDBHelper;
import com.gunsnrocket.int20h.models.Group;

import java.util.ArrayList;

/**
 * Created by dnt on 2/27/16.
 */
public class GroupsActivity extends AppCompatActivity {

    private String categoryName;
    private int categoryId;
    private RecyclerView recyclerView;
    private ArrayList<Group> list;
    private GroupAdapter adapter;
    private StaggeredGridLayoutManager layoutManager;
    private KazpromDBHelper kazpromDBHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryName = getIntent().getExtras().getString(MainActivity.CATEGORY_NAME);
        categoryId = getIntent().getExtras().getInt(MainActivity.CATEGORY_ID);

        setContentView(R.layout.activity_groups);

        getSupportActionBar().setTitle(categoryName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = new ArrayList<Group>();

        kazpromDBHelper = KazpromDBHelper.getInstance();

        recyclerView = (RecyclerView) findViewById(R.id.groups_list);
        adapter = new GroupAdapter(this, list);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        (new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                kazpromDBHelper.getGroupList(list, categoryId);

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                adapter.notifyDataSetChanged();
                Log.d("D", "privet");
            }
        }).execute();


    }
}
