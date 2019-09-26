package com.ilustris.motiv;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.ilustris.motiv.adapters.HomePager;

public class MainActivity extends AppCompatActivity {

    private AppBarLayout appbar;
    private Toolbar toolbar;
    private TabLayout tabs;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        appbar = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.toolbar);
        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);
        HomePager homepager = new HomePager(getSupportFragmentManager());
        pager.setAdapter(homepager);
        tabs.setupWithViewPager(pager);
        tabs.getTabAt(0).setText("Home");
        tabs.getTabAt(1).setText("Ícones");
    }
}
