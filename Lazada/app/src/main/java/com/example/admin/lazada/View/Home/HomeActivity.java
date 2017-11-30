package com.example.admin.lazada.View.Home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.lazada.Adapter.ViewPagerAdapter;
import com.example.admin.lazada.Common.MenuPressenterInface;
import com.example.admin.lazada.Common.MenuViewInface;
import com.example.admin.lazada.Model.ObjectClass.LoaiSanPham;
import com.example.admin.lazada.Pressenter.Home.Menu.MenuLogicPressenter;
import com.example.admin.lazada.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_layout);

        init();

        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        MenuPressenterInface menu = new MenuLogicPressenter(this);
        menu.loadDataMenu();

    }

    private void init(){
        toolbar = findViewById(R.id.toolBar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        drawerLayout = findViewById(R.id.drawerLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }

    @Override
    public void showListDataMenu(List<LoaiSanPham> data) {

    }
}
