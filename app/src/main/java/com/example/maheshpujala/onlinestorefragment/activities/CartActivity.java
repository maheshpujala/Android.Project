package com.example.maheshpujala.onlinestorefragment.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.maheshpujala.onlinestorefragment.R;
import com.example.maheshpujala.onlinestorefragment.adapters.CartAdapter;
import com.example.maheshpujala.onlinestorefragment.adapters.ListAdapter;

/**
 * Created by maheshpujala on 4/7/16.
 */
public class CartActivity extends AppCompatActivity {
    String cart="mycart";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int item_count = getIntent().getIntExtra("cart_value",0);
       setTitle("MyCart"+"("+item_count+")");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        ListView Products = (ListView) findViewById(R.id.lvProducts);
        // lvProducts.addHeaderView(getLayoutInflater().inflate(R.layout.product_list_header, lvProducts, false));
        Products.setAdapter(new CartAdapter(this,item_count));
        Products.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),"HIiiiiiiiiiiii",Toast.LENGTH_LONG).show();
                //  Product product = Constant.PRODUCT_LIST.get(position - 1);

             /*   Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", product);
                Log.d(TAG, "View product: " + product.getName());
                intent.putExtras(bundle);
                startActivity(intent);*/
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
            Log.e("onBackPressed","___________Entered__________");
        }

        return super.onOptionsItemSelected(item);
    }
}
