package com.example.maheshpujala.onlinestorefragment.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maheshpujala.onlinestorefragment.R;
import com.example.maheshpujala.onlinestorefragment.adapters.ListActivity;
import com.example.maheshpujala.onlinestorefragment.adapters.NavigationDrawer;
import com.example.maheshpujala.onlinestorefragment.adapters.GridAdapter;
import com.example.maheshpujala.onlinestorefragment.api.NetworkCheck;

public class MainSubActivity extends NavigationDrawer implements View.OnClickListener {
    Button sort,filter;
    LinearLayout popup;
    ListView dialog_ListView;
    TextView results;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main_sub, baseframe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNavigationHeader();
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/FallingSkyCond.otf");

        results= (TextView)findViewById(R.id.results);
        sort =(Button)findViewById(R.id.sort_btn);
        filter =(Button)findViewById(R.id.filter_btn);
        sort.setOnClickListener(this);
        filter.setOnClickListener(this);

        popup = (LinearLayout)findViewById(R.id.popup_layout);
        checkConnection();

        results.setTypeface(custom_font);
        sort.setTypeface(custom_font);
     }

    private void checkConnection() {
        if(NetworkCheck.isInternetAvailable(MainSubActivity.this))  //if connection available
        {
            sort.setEnabled(true);
            filter.setEnabled(true);
            setGridView();
        }
        else{
            sort.setEnabled(false);
            filter.setEnabled(false);
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

    private void setGridView() {
        GridView gridView = (GridView) findViewById(R.id.grid_results);
        // Instance of ImageAdapter Class
        gridView.setAdapter(new GridAdapter(this));
        int count= gridView.getCount();
        results.setText(count+" Results");

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent swipe = new Intent(MainSubActivity.this,SwipeActivity.class);
                startActivity(swipe);

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sort_btn:
               popup();
                break;
            case R.id.filter_btn:
                break;
            }
        }
    public void popup(){
        // custom dialog
        String [] options={"Popular","Price:Lowest First","Price:Highest First","Most Viewed","Most Sold","Discount"};
        String check="sort";
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_popup);
        dialog.setTitle("        Sorting Options");
        dialog.setCancelable(false);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener(){

            @Override
            public void onCancel(DialogInterface dialog) {
// TODO Auto-generated method stub
                Toast.makeText(MainSubActivity.this,
                        "OnCancelListener",
                        Toast.LENGTH_SHORT).show();
            }});

        dialog_ListView = (ListView)dialog.findViewById(R.id.dialog_list);

        dialog_ListView.setAdapter(new ListActivity(this,options,check));

        dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

               dialog.dismiss();

            }
        });

        dialog.show();
    }


}

