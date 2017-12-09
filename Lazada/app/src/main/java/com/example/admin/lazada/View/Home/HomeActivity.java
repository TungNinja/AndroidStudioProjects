package com.example.admin.lazada.View.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.example.admin.lazada.Adapter.ExpandAdapter;
import com.example.admin.lazada.Adapter.ViewPagerAdapter;
import com.example.admin.lazada.Common.MenuPressenterInface;
import com.example.admin.lazada.Common.MenuViewInface;
import com.example.admin.lazada.Model.ObjectClass.LoaiSanPham;
import com.example.admin.lazada.Pressenter.Home.Menu.MenuLogicPressenter;
import com.example.admin.lazada.R;
import com.example.admin.lazada.View.Register.Register;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by ADMIN on 11/30/2017.
 */

public class HomeActivity extends AppCompatActivity implements MenuViewInface {

    Toolbar toolbar;

    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    MenuPressenterInface menuPressenter;
    String username = "";
    AccessToken accessToken;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.home_layout);

        init();

        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        menuPressenter = new MenuLogicPressenter(this);
        menuPressenter.loadDataMenu();

    }

    private void init(){

        toolbar = findViewById(R.id.toolBar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        drawerLayout = findViewById(R.id.drawerLayout);
        expandableListView = findViewById(R.id.epMenu);

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        this.menu = menu;

        accessToken = menuPressenter.getAccessToken();
        if (accessToken != null) {

            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {

                    try {

                        username = object.getString("name");
                        MenuItem menuItemLogin = menu.findItem(R.id.idSigin);
                        MenuItem menuItemLogout = menu.findItem(R.id.idLogout);
                        menuItemLogin.setTitle(username);
                        menuItemLogout.setVisible(true);

                        Log.d("username: ", username);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            Bundle parameter = new Bundle();
            parameter.putString("fileds", "name");

            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();

        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        int id = item.getItemId();

        switch (id){
            case R.id.idSigin:
                if (accessToken.getToken() == null || "".equals(accessToken.getToken()) ){
                    Intent intent = new Intent(this, Register.class);
                    startActivity(intent);
                }
                break;
            case R.id.idLogout:
                if (accessToken.getToken() != null || !"".equals(accessToken.getToken()) ){
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                break;

        }
        return true;
    }

    @Override
    public void showListDataMenu(List<LoaiSanPham> data) {

        ExpandAdapter expandAdapter = new ExpandAdapter(this, data);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();

    }
}
