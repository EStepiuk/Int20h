package com.gunsnrocket.int20h;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;

import com.gunsnrocket.int20h.adapters.ProductAdapter;
import com.gunsnrocket.int20h.dbhelpers.KazpromDBHelper;
import com.gunsnrocket.int20h.models.Product;

import java.util.ArrayList;

/**
 * Created by dnt on 2/28/16.
 */
public class ProductsActivity extends AppCompatActivity {

    private String groupName;
    private int categoryId, groupId;
    private ArrayList<Product> list;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
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

        groupName = getIntent().getExtras().getString(MainActivity.GROUP_NAME);
        groupId = getIntent().getExtras().getInt(MainActivity.GROUP_ID);
        categoryId = getIntent().getExtras().getInt(MainActivity.CATEGORY_ID);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(groupName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_products);

        kazpromDBHelper = KazpromDBHelper.getInstance();

        list = new ArrayList<Product>();
        recyclerView = (RecyclerView) findViewById(R.id.product_list);
        adapter = new ProductAdapter(this, list);
        layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        if (list.isEmpty()) {
            (new AsyncTask<Void, Void, Void>() {

                @Override
                protected Void doInBackground(Void... params) {
                    kazpromDBHelper.getProductList(list, categoryId, groupId);

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
