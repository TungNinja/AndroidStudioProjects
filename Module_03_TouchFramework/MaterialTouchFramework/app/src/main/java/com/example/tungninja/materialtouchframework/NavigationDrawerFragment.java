package com.example.tungninja.materialtouchframework;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {


    private RecyclerView recyclerView;

    public static final String PREFF_FILE_NAME = "testpreff";

    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";

    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;

    private boolean mUserLearnedDrawer;

    private boolean mFromSaveInstanceSate;

    private View containerView;

    private VivzAdapter adapter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,"false"));

        if (savedInstanceState != null){
            mFromSaveInstanceSate = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        recyclerView = layout.findViewById(R.id.drawerList);

        recyclerView.setHasFixedSize(true);

        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        final List<Information> data = getData();

        adapter = new VivzAdapter(getActivity(), data);

        recyclerView.setLayoutManager(linearLayoutManager);

        adapter.notifyDataSetChanged();

        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == data.size()-1){

                }

            }
        });

        return layout;
    }

    public  static  List<Information> getData(){

        List<Information> data = new ArrayList<>();

        int[] icons = {
                R.drawable.ic_launcher1,
                R.drawable.ic_launcher2,
                R.drawable.ic_launcher3,
                R.drawable.ic_launcher4,
                R.drawable.ic_launcher5,
                R.drawable.ic_launcher6,
                R.drawable.ic_launcher7
        };

        String[] titles = {
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5",
                "Item 6",
                "Item 7",
        };

        for (int i = 0; i<100; i++){

            Information current = new Information();

            current.iconId = icons[i%icons.length];
            current.title = titles[i%titles.length];

            data.add(current);
        }

        return data;
    }

    public void setUp(int fragmentID, DrawerLayout drawerLayout, final Toolbar toolbar) {

        containerView = getActivity().findViewById(fragmentID);

        mDrawerLayout = drawerLayout;

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if (!mUserLearnedDrawer){
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer+"");
                }

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //super.onDrawerSlide(drawerView, slideOffset);
                //Log.d("VIVZ", "offset"+slideOffset);

//                if (slideOffset < 0.6){
//                    toolbar.setAlpha(1 - slideOffset);
//                }
            }
        };

        if (!mUserLearnedDrawer && !mFromSaveInstanceSate){
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public  static void saveToPreferences(Context context, String preferenceName, String preferenceValue){

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFF_FILE_NAME, context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(preferenceName, preferenceValue);

        editor.apply();
    }

    public static String readFromPreferences(Context context, String prefrenceName, String defaultValue){

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFF_FILE_NAME, context.MODE_PRIVATE);

        return sharedPreferences.getString(prefrenceName, defaultValue);
    }

}
