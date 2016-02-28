package com.gunsnrocket.int20h;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.OvershootInterpolator;

import com.gunsnrocket.int20h.adapters.GroupAdapter;
import com.gunsnrocket.int20h.dbhelpers.KazpromDBHelper;
import com.gunsnrocket.int20h.models.Group;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.adapters.SlideInBottomAnimationAdapter;

/**
 * Created by dnt on 2/27/16.
 */
public class GroupsActivity extends AppCompatActivity {

    private String categoryName;
    private int categoryId;
    private RecyclerView recyclerView;
    private ArrayList<Group> list;
    private SlideInBottomAnimationAdapter adapter;
    private StaggeredGridLayoutManager layoutManager;
    private KazpromDBHelper kazpromDBHelper;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryName = getIntent().getExtras().getString(MainActivity.CATEGORY_NAME);
        categoryId = getIntent().getExtras().getInt(MainActivity.CATEGORY_ID);

        setContentView(R.layout.activity_groups);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(categoryName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        list = new ArrayList<Group>();

        kazpromDBHelper = KazpromDBHelper.getInstance();

        recyclerView = (RecyclerView) findViewById(R.id.groups_list);
        adapter = new SlideInBottomAnimationAdapter(new GroupAdapter(this, list));

        adapter.setDuration(500);
        adapter.setInterpolator(new OvershootInterpolator(0.7f));
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if (list.isEmpty()) {
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
}
