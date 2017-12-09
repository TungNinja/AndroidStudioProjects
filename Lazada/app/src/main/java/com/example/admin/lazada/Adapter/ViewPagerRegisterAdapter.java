package com.example.admin.lazada.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.lazada.View.Register.Fragment.FragmentSign;
import com.example.admin.lazada.View.Register.Fragment.FragmentSignOut;

/**
 * Created by ADMIN on 12/1/2017.
 */

public class ViewPagerRegisterAdapter extends FragmentPagerAdapter {

    public ViewPagerRegisterAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentSign();
            case 1:
                return new FragmentSignOut();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Đăng nhập";
            case 1:
                return "Đăng ký";
        }
        return "";
    }
}
