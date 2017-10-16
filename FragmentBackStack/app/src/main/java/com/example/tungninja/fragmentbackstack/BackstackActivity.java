package com.example.tungninja.fragmentbackstack;

import android.os.Bundle;

import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;

import android.view.View;

import android.widget.Button;

/**
 * Created by tungninja on 10/15/17.
 */

public class BackstackActivity extends AppCompatActivity {

    private Button btnFrag;



    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_backstack);

        btnFrag = (Button) findViewById(R.id.btn_replace_fragment);

        btnFrag.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                addFragment(new Fragment2());
                //replaceFragmentContent(new Fragment2());

                Log.e("Replaced fragment", "2");

            }

        });



        initFragment();

    }



    private void initFragment() {

        Fragment1 firstFragment = new Fragment1();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(R.id.container_body, firstFragment);

        ft.commit();

    }



    protected void replaceFragmentContent(Fragment fragment) {

        if (fragment != null) {

            FragmentManager fmgr = getSupportFragmentManager();

            FragmentTransaction ft = fmgr.beginTransaction();

            ft.replace(R.id.container_body, fragment);

            ft.commit();

        }

    }

    protected void addFragment(Fragment fragment) {

        FragmentManager fmgr = getSupportFragmentManager();

        FragmentTransaction ft = fmgr.beginTransaction();

        ft.add(R.id.container_body, fragment);

        ft.addToBackStack(fragment.getClass().getSimpleName());

        ft.commit();

    }

}
