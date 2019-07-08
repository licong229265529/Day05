package com.example.day05;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.day05.adapters.AdapterVp;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mMyVp;
    private TabLayout mMyTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initVp();
    }

    private void initVp() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        LoveFragment loveFragment = new LoveFragment();

        fragments.add(homeFragment);
        fragments.add(loveFragment);

        AdapterVp adapterVp = new AdapterVp(getSupportFragmentManager(), fragments);
        mMyVp.setAdapter(adapterVp);
        mMyTab.setupWithViewPager(mMyVp);

    }

    private void initView() {
        mMyVp = (ViewPager) findViewById(R.id.myVp);
        mMyTab = (TabLayout) findViewById(R.id.myTab);
    }
}
