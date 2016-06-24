package com.example.maheshpujala.onlinestorefragment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TabHost;

import com.example.maheshpujala.onlinestorefragment.R;
import com.example.maheshpujala.onlinestorefragment.adapters.GridAdapter;
import com.example.maheshpujala.onlinestorefragment.adapters.NavigationDrawer;
import com.example.maheshpujala.onlinestorefragment.api.NetworkCheck;

public class HomeActivity extends NavigationDrawer{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_home,baseframe);

        setNavigationHeader();
        checkConnection();
    }

    private void checkConnection() {
        if(NetworkCheck.isInternetAvailable(HomeActivity.this))  //if connection available
        {
            setTabhost();
            setGridView();
        }
        else{
            Snackbar.make(baseframe,"No Internet Connection", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Refresh", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkConnection();
                        }
                    }).show();
        }
    }

    private void setGridView() {
        GridView gridView = (GridView) findViewById(R.id.view_men);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new GridAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent swipe = new Intent(HomeActivity.this,MainSubActivity.class);
                startActivity(swipe);

            }
        });
    }


    public void setTabhost (){
    TabHost host = (TabHost)findViewById(R.id.tabhost);
    host.setup();

    //Tab 1
    TabHost.TabSpec spec = host.newTabSpec("Tab One");
    spec.setContent(R.id.men);
    spec.setIndicator("MEN");
    host.addTab(spec);

    //Tab 2
    spec = host.newTabSpec("Tab Two");
    spec.setContent(R.id.women);
    spec.setIndicator("WOMEN");
    host.addTab(spec);
  //Tab 3
    spec = host.newTabSpec("Tab Three");
    spec.setContent(R.id.kids);
    spec.setIndicator("KIDS");
    host.addTab(spec);


}


    }