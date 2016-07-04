package com.example.maheshpujala.onlinestorefragment.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.maheshpujala.onlinestorefragment.R;
import com.example.maheshpujala.onlinestorefragment.adapters.ViewPagerAdapter;
import com.example.maheshpujala.onlinestorefragment.adapters.ListActivity;
import com.example.maheshpujala.onlinestorefragment.adapters.NavigationDrawer;
import com.example.maheshpujala.onlinestorefragment.api.NetworkCheck;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by maheshpujala on 13/6/16.
 */
public class SwipeActivity extends NavigationDrawer implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ViewPager viewPager;
    PagerAdapter adapter;
    ImageButton leftNav,rightNav;
    ImageView imageView,testimg;
    Spinner size_selector;
    TextView pname,description,discount,sellingPrice,actualPrice,size_array;
    // String text="There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.";;

    //JSON Node Names
    private static final String TAG_NAME = "name";
    private static final String TAG_DESC = "description";
    private static final String TAG_PRICE = "price";
    private static final String TAG_COST_PRICE = "cost_price";
    private static final String TAG_DISCOUNT = "discount";
    private static final String TAG_MASTER = "master";
    private static final String TAG_IMAGES = "images";
    String descr,name,actual_price,cost_price,discount_tag;
    String [] sizes,product_image_url;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.swipe_view,baseframe);
//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }


        leftNav = (ImageButton) findViewById(R.id.left_nav);
        rightNav = (ImageButton) findViewById(R.id.right_nav);
        FloatingActionButton share = (FloatingActionButton) findViewById(R.id.fab);
        leftNav.setOnClickListener(this);
        rightNav.setOnClickListener(this);
        share.setOnClickListener(this);

        testimg=(ImageView)findViewById(R.id.testimage);
        checkConnection();

        setNavigationHeader();
    }

    private void checkConnection() {
        if(NetworkCheck.isInternetAvailable(SwipeActivity.this))  //if connection available
        {
            sendRequest();
        }
        else{
            Snackbar snackbar =  Snackbar.make(baseframe,"No Internet Connection", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Refresh", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkConnection();
                        }
                    });
            snackbar.setActionTextColor(Color.RED);
            snackbar.show();
        }
    }

    private void sendRequest() {
//        RequestQueue queue = MyVolley.getRequestQueue();
//        GsonRequest<MyClass> myReq = new GsonRequest<MyClass>(Request.Method.GET,
//                "http://JSONURL/",
//                SwipeActivity.class,
//                createMyReqSuccessListener(),
//                createMyReqErrorListener());
//
//        queue.add(myReq);

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.3.48:2490/api/v1/products/1.json?token=f64957cb4af203fcabbddefc170827d313f6ec48e3cc9e5d";
// Request a JsonObject response from the provided URL.
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        getData(response);
                        setSpinner();
                        setViewPager();
                        setTabhost();
                        Log.e("======================",response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
// Add the request to the RequestQueue.
        queue.add(jsObjRequest);
    }

    private void setViewPager() {
        // Locate the ViewPager in viewpager_main.xml
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapter(SwipeActivity.this, product_image_url);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);
    }

    private void setSpinner() {
        // Spinner element
        size_selector =(Spinner) findViewById(R.id.select_size);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter =new  ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sizes);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        size_selector.setAdapter(adapter);
// Spinner click listener
        size_selector.setOnItemSelectedListener(this);
    }
    private void getData(JSONObject json) {

//        // Creating new JSON Parser
//        JSONParser jParser = new JSONParser();
//        // Getting JSON from URL
//        JSONObject json = jParser.getJSONFromUrl();
        try {
            // Storing  JSON item in a Variable
            name = json.getString(TAG_NAME);
            descr = json.getString(TAG_DESC);
            actual_price = json.getString(TAG_PRICE);
            cost_price = json.getString(TAG_COST_PRICE);
            discount_tag = json.getString(TAG_DISCOUNT);
            JSONObject  master = json.getJSONObject(TAG_MASTER);
            JSONArray images = master.getJSONArray(TAG_IMAGES);
            JSONArray sizes_array = json.getJSONArray("variants");


             product_image_url =new String[images.length()];
             sizes =new String[sizes_array.length()];

            for (int i = 0; i < images.length(); i++) {
               JSONObject jsonobject = images.getJSONObject(i);
                 product_image_url[i]= jsonobject.getString("product_url");
            }


            for (int i = 0; i < sizes_array.length(); i++) {
                JSONObject jsonobject = sizes_array.getJSONObject(i);
                sizes[i] = jsonobject.getString("size");

            }

            pname=(TextView)findViewById(R.id.productName);
            pname.setText(name);

            description = (TextView)findViewById(R.id.desc);
            description.setText(descr);

            actualPrice = (TextView)findViewById(R.id.actualPrice);
            actualPrice.setText('\u20B9' + actual_price);
            actualPrice.setPaintFlags(actualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


            sellingPrice = (TextView)findViewById(R.id.sellingPrice);
            sellingPrice.setText('\u20B9' + cost_price);

            discount = (TextView)findViewById(R.id.discount);
            discount.setText(discount_tag);

         //   size_array = (TextView)findViewById(R.id.size_array);


           } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void shareContent(){
        String text = "www.google.com";
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.setType("text/*");
        startActivity(Intent.createChooser(shareIntent, "Share ..."));
    }
    public void setTabhost (){
        TabHost host = (TabHost)findViewById(R.id.tabhost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.description);
        spec.setIndicator("Description");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Features");
        spec.setContent(R.id.features);
        spec.setIndicator("Features");
        host.addTab(spec);

        TextView features = (TextView)findViewById(R.id.feat);


      //  features.setText(text);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.fab:
                shareContent();
                break;
            case R.id.left_nav:  // Images Left navigation

                int tab = viewPager.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    viewPager.setCurrentItem(tab);
                } else if (tab == 0) {
                    viewPager.setCurrentItem(tab);
                }

                break;
            case R.id.right_nav:  // Images Right navigation

                int tabb = viewPager.getCurrentItem();
                tabb++;
                viewPager.setCurrentItem(tabb);
                break;

        }

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



