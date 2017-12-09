package com.example.admin.lazada.View.Register;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.example.admin.lazada.Adapter.ViewPagerRegisterAdapter;
import com.example.admin.lazada.R;

/**
 * Created by ADMIN on 12/1/2017.
 */

public class Register extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        init();

        setSupportActionBar(toolbar);

        ViewPagerRegisterAdapter viewPagerRegisterAdapter = new ViewPagerRegisterAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerRegisterAdapter);
        viewPagerRegisterAdapter.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);

    }

    private void init(){
        tabLayout = findViewById(R.id.tabLayoutRegister);
        viewPager = findViewById(R.id.viewPagerRegister);
        toolbar = findViewById(R.id.toolBarRegister);
    }
}
