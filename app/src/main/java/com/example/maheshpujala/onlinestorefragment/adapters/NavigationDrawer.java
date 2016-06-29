package com.example.maheshpujala.onlinestorefragment.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maheshpujala.onlinestorefragment.R;
import com.example.maheshpujala.onlinestorefragment.activities.HomeActivity;
import com.example.maheshpujala.onlinestorefragment.activities.LoginActivity;
import com.example.maheshpujala.onlinestorefragment.activities.MainSubActivity;
import com.example.maheshpujala.onlinestorefragment.api.NetworkCheck;
import com.example.maheshpujala.onlinestorefragment.cache.PrefUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

/**
 * Created by maheshpujala on 14/6/16.
 */
public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    ImageView profilepic,refresh_action;
    TextView name, email;
    JSONObject response, profile_pic_data, profile_pic_url;
    String jsondata;
    private SearchView mSearchView;
    private MenuItem searchMenuItem, mycart;
    protected FrameLayout baseframe;
    public static final String PREFS_LOGIN_USERNAME_KEY = "__USERNAME__" ;
    public static final String PREFS_LOGIN_EMAIL_KEY = "__EMAIL__" ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        baseframe=(FrameLayout)findViewById(R.id.base_frame);
        // toolbar.setLogo(R.mipmap.ic_launcher);

    }
    /*
          Set Navigation header
       */
    public void setNavigationHeader() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView =  navigationView.getHeaderView(0);
        email = (TextView)hView.findViewById(R.id.emailView);
        name = (TextView)hView.findViewById(R.id.nameView);

        // To retrieve values back
        String loggedInUserName = PrefUtils.getFromPrefs(NavigationDrawer.this, PREFS_LOGIN_USERNAME_KEY,PREFS_LOGIN_USERNAME_KEY);
        String loggedInUserEmail= PrefUtils.getFromPrefs(NavigationDrawer.this, PREFS_LOGIN_EMAIL_KEY,PREFS_LOGIN_EMAIL_KEY);
if(loggedInUserName!=null){
    email.setText(loggedInUserEmail);
    name.setText(loggedInUserName);
    Log.e("User DETAILS SET", loggedInUserEmail);
}
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_icons, menu);
        mycart = menu.findItem(R.id.action_cart);
        searchMenuItem = menu.findItem(R.id.action_search);
        // Associate searchable configuration with the SearchView
        mSearchView = (SearchView) searchMenuItem.getActionView();
        mSearchView.setOnQueryTextListener(listener);
        // mSearchView.setSubmitButtonEnabled(true);

        MenuItemCompat.setOnActionExpandListener(searchMenuItem,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem menuItem) {
                        // Return true to allow the action view to expand
                        mycart.setVisible(false);
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                        // When the action view is collapsed, reset the query
                        mycart.setVisible(true);

                        // Return true to allow the action view to collapse
                        return true;
                    }
                });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            mycart.setVisible(false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Toast.makeText(getApplicationContext(), query, Toast.LENGTH_LONG).show();
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            // text entered by user to SearchView

            return false;
        }
    };

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent home = new Intent(this, HomeActivity.class);
            startActivityForResult(home, 2);

        } else if (id == R.id.my_account) {

        } else if (id == R.id.track_order) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.login) {
            Intent login = new Intent(this, LoginActivity.class);
            startActivityForResult(login, 1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.hasExtra("Fb_Details")) {
                    jsondata = data.getStringExtra("Fb_Details");

                    try {
                        response = new JSONObject(jsondata);
                        Log.e("Fb_Details", jsondata);
                        String id = response.get("id").toString();
                        String fb_email=response.get("email").toString();
                        String fb_name=response.get("name").toString();

                        email.setText(fb_email);
                        name.setText(fb_name);
                        //profile_pic_data = new JSONObject(response.get("picture").toString());
                        //  profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
                        // URL image_value = new URL("https://graph.facebook.com/"+id+"/picture" );
                        // Bitmap  profPict = BitmapFactory.decodeStream(image_value.openConnection().getInputStream());
                        //   profilepic.setImageBitmap(profPict);


                        // Saving user credentials on successful login case
                        PrefUtils.saveToPrefs(NavigationDrawer.this, PREFS_LOGIN_USERNAME_KEY, fb_name);
                        PrefUtils.saveToPrefs(NavigationDrawer.this, PREFS_LOGIN_EMAIL_KEY, fb_email);
                        Log.e("User DETAILS get", fb_name);

                        //Picasso.with(this).load("https://graph.facebook.com/" + id + "/picture?type=large").into(profilepic);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (data.hasExtra("pname")){
                    Log.e("pname","google sucess");
                    String g_name=data.getStringExtra("pname");
                    String g_email=data.getStringExtra("pemail");
                    name.setText(g_name);
                    email.setText(g_email);
                  //  Picasso.with(this).load(data.getStringExtra("pphoto")).resize(200, 200).into(profilepic);
                    // Saving user credentials on successful login case
                    PrefUtils.saveToPrefs(NavigationDrawer.this, PREFS_LOGIN_USERNAME_KEY, g_name);
                    PrefUtils.saveToPrefs(NavigationDrawer.this, PREFS_LOGIN_EMAIL_KEY, g_email);
                    Log.e("User DETAILS get", g_name);



                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }//onActivityResult
    }


}
