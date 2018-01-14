package com.example.ytgv8b.firsttry;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import Kostyly.Kostyl;
import Library.DataHelpers.DataBaseHelper;
import Library.DataHelpers.FileSystemHelper;
import Library.DataHelpers.PreferenceHelper;

public class MainMenu extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceHelper.init(this);
        DataBaseHelper.init(this);
        FileSystemHelper.init(this);

        PreferenceHelper helper = PreferenceHelper.getInstance();
       if (helper.isFirstStart())
        {
            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            startActivity(intent);
        }


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.settings:
                Kostyl.makeToast(this);
                return true;
            case R.id.profil:
                Kostyl.makeToast(this);
                return true;
            case R.id.developers:
                Kostyl.makeToast(this);
                return true;
            case R.id.instruction:
                Kostyl.makeToast(this);
                return true;
            case R.id.sleep:
                Kostyl.makeToast(this);
                return true;
            default:
                Kostyl.makeToast(this);
                return super.onOptionsItemSelected(item);
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RingFragment(), "Будильники");
        adapter.addFragment(new NotificationFragment(), "Уведомления");
        viewPager.setAdapter(adapter);
    }
}
