package com.gunsnrocket.int20h;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by dnt on 2/28/16.
 */
public class CurrentProductActivity extends AppCompatActivity {

    private String productName;
    private String productDescription;

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
        setContentView(R.layout.activity_current_product);

        productName = getIntent().getExtras().getString(MainActivity.PRODUCT_NAME);
        productDescription = getIntent().getExtras().getString(MainActivity.PRODUCT_DESCRIPTION);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        ((TextView) findViewById(R.id.current_product_name)).setText(productName);
        ((TextView) findViewById(R.id.current_product_description)).setText(productDescription);
    }

}
