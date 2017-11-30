package com.example.admin.lazada.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.lazada.View.Home.Fragment.FragmentElectronic;
import com.example.admin.lazada.View.Home.Fragment.FragmentFashion;
import com.example.admin.lazada.View.Home.Fragment.FragmentHighlights;
import com.example.admin.lazada.View.Home.Fragment.FragmentHomeLife;
import com.example.admin.lazada.View.Home.Fragment.FragmentMakeUp;
import com.example.admin.lazada.View.Home.Fragment.FragmentMotherBaby;
import com.example.admin.lazada.View.Home.Fragment.FragmentSale;
import com.example.admin.lazada.View.Home.Fragment.FragmentSportTravel;
import com.example.admin.lazada.View.Home.Fragment.FragmentTrademark;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 11/30/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> listFragment = new ArrayList<Fragment>();
    List<String> listLabel = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        listFragment.add(new FragmentHighlights());
        listFragment.add(new FragmentSale());
        listFragment.add(new FragmentElectronic());
        listFragment.add(new FragmentHomeLife());
        listFragment.add(new FragmentMotherBaby());
        listFragment.add(new FragmentMakeUp());
        listFragment.add(new FragmentFashion());
        listFragment.add(new FragmentSportTravel());
        listFragment.add(new FragmentTrademark());

        listLabel.add("Nổi bật");
        listLabel.add("Chương trình khuyến mãi");
        listLabel.add("Điện tử");
        listLabel.add("Nhà cửa và đời sống");
        listLabel.add("Mẹ và bé");
        listLabel.add("Làm đẹp");
        listLabel.add("Thời trang");
        listLabel.add("Thể thao và du lịch");
        listLabel.add("Thương hiệu");


    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listLabel.get(position);
    }
}
